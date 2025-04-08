package com.example.habittracker.ui.screens.habits.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.model.Habit
import com.example.data.repositories.HabitsRepository
import com.example.habittracker.ui.composables.dialogs.DialogCallbacks
import com.example.habittracker.ui.screens.habits.add.task.TaskEntryFlow
import com.example.habittracker.ui.screens.habits.model.toHabitUIData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class HabitDetailsViewModel @Inject constructor(
    private val habitsRepository: HabitsRepository,
) : ViewModel() {
    //region --- UI Events ---
    val uiEvent: MutableSharedFlow<HabitDetailsEvent> = MutableSharedFlow()

    sealed class HabitDetailsEvent {
        data object NavigateBack : HabitDetailsEvent()

        data class NavigateToTaskEntry(val taskEntryFlow: TaskEntryFlow) : HabitDetailsEvent()
    }
    //endregion

    private val habit = MutableStateFlow<Habit?>(null)
    private var isDeleting: Boolean = false

    private val _temporaryHabitName = MutableStateFlow("")
    val temporaryHabitName: StateFlow<String> get() = _temporaryHabitName.asStateFlow()

    private val habitRefreshTrigger = MutableSharedFlow<String>(replay = 1)

    @OptIn(ExperimentalCoroutinesApi::class)
    val habitUIState: StateFlow<HabitDetailsUIState> = habitRefreshTrigger.flatMapLatest { habitId ->
        habitsRepository.getHabitById(habitId).map { rawHabit ->
            if (rawHabit == null) {
                if (!isDeleting) {
                    showDialog(HabitDetailsDialogType.BasicDialog.LoadHabitFailed)
                    return@map HabitDetailsUIState.Failure
                } else {
                    return@map HabitDetailsUIState.Loading
                }
            }

            habit.update { rawHabit }
            HabitDetailsUIState.Success(rawHabit.toHabitUIData())
        }.onStart { emit(HabitDetailsUIState.Loading) }
    }
        .flowOn(Dispatchers.IO)
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000L),
            initialValue = HabitDetailsUIState.Loading,
        )

    //region --- Callbacks ---
    fun onCloseClick() = navigateBack()

    fun onDeleteClick() = showDialog(HabitDetailsDialogType.BasicDialog.DeleteHabit)

    fun onEditTaskClick(taskId: String) {
        viewModelScope.launch {
            uiEvent.emit(HabitDetailsEvent.NavigateToTaskEntry(taskEntryFlow = TaskEntryFlow.SavedTask.Edit(taskId)))
        }
    }

    fun onEditNameClick() {
        _temporaryHabitName.update { habit.value?.name.orEmpty() }
        showDialog(HabitDetailsDialogType.InputDialog.RenameHabit)
    }

    fun refreshHabit(habitId: String) {
        viewModelScope.launch {
            habitRefreshTrigger.emit(habitId)
        }
    }

    fun onAddTask() {
        habit.value?.let { habit ->
            viewModelScope.launch {
                uiEvent.emit(HabitDetailsEvent.NavigateToTaskEntry(taskEntryFlow = TaskEntryFlow.SavedTask.Add(habitId = habit.id)))
            }
        } ?: Timber.w("Not able to add task: habit is null")
    }
    //endregion

    private fun deleteHabit() {
        viewModelScope.launch {
            val habitUIState = habitUIState.value
            if (habitUIState is HabitDetailsUIState.Success) {
                isDeleting = true
                habitsRepository.deleteHabit(habitUIState.habit.id)
                navigateBack()
            }
        }
    }

    private fun navigateBack() {
        viewModelScope.launch {
            uiEvent.emit(HabitDetailsEvent.NavigateBack)
        }
    }

    private fun renameHabit() {
        viewModelScope.launch(Dispatchers.IO) {
            val habitUIState = habitUIState.value
            val habit = habit.value
            if (habitUIState is HabitDetailsUIState.Success && habit != null) {
                habitsRepository.upsertHabit(habit.copy(name = _temporaryHabitName.value))
            }
        }
        dismissDialog()
    }

    //region --- Dialog ---
    private val _isDialogVisible = MutableStateFlow(false)
    private val _dialogType = MutableStateFlow<HabitDetailsDialogType>(HabitDetailsDialogType.BasicDialog.Generic)
    private val _dialogCallbacks = MutableStateFlow<DialogCallbacks>(DialogCallbacks.BasicDialog())
    val isDialogVisible: StateFlow<Boolean> get() = _isDialogVisible.asStateFlow()
    val dialogType: StateFlow<HabitDetailsDialogType> get() = _dialogType.asStateFlow()
    val dialogCallbacks: StateFlow<DialogCallbacks> get() = _dialogCallbacks.asStateFlow()

    fun dismissDialog() = _isDialogVisible.update { false }

    private fun showDialog(dialogType: HabitDetailsDialogType) {
        _dialogType.update { dialogType }
        _dialogCallbacks.update { getDialogCallbacks(dialogType) }
        _isDialogVisible.update { true }
    }

    private fun getDialogCallbacks(dialogType: HabitDetailsDialogType): DialogCallbacks =
        when (dialogType) {
            HabitDetailsDialogType.BasicDialog.DeleteHabit -> DialogCallbacks.BasicDialog(
                onPositiveAction = ::deleteHabit,
                onNegativeAction = ::dismissDialog,
                onDismiss = ::dismissDialog,
            )

            HabitDetailsDialogType.BasicDialog.LoadHabitFailed -> DialogCallbacks.BasicDialog(
                onPositiveAction = ::navigateBack,
                onDismiss = ::navigateBack,
            )

            HabitDetailsDialogType.BasicDialog.Generic -> DialogCallbacks.BasicDialog(
                onPositiveAction = ::navigateBack,
                onDismiss = ::navigateBack,
            )

            HabitDetailsDialogType.InputDialog.RenameHabit -> DialogCallbacks.InputDialog(
                onPositiveAction = ::renameHabit,
                onNegativeAction = ::dismissDialog,
                onDismiss = ::onDismissRenameHabitDialog,
                onTextValueChange = ::onRenameHabitTextValueChange,
            )
        }

    private fun onRenameHabitTextValueChange(newName: String) {
        _temporaryHabitName.update { newName }
    }

    private fun onDismissRenameHabitDialog() {
        _temporaryHabitName.update { "" }
        dismissDialog()
    }
    //endregion
}
