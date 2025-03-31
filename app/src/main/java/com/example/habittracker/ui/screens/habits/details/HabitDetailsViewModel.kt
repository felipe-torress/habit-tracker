package com.example.habittracker.ui.screens.habits.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.repositories.HabitsRepository
import com.example.habittracker.ui.composables.dialogs.GenericDialogCallbacks
import com.example.habittracker.ui.screens.habits.model.toHabitUIData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HabitDetailsViewModel @Inject constructor(
    private val habitsRepository: HabitsRepository,
) : ViewModel() {
    //region --- UI Events ---
    val uiEvent: MutableSharedFlow<HabitDetailsEvent> = MutableSharedFlow()

    sealed class HabitDetailsEvent {
        data object NavigateBack : HabitDetailsEvent()

        data class NavigateToEditTask(val taskId: String) : HabitDetailsEvent()
    }
    //endregion

    private val habitRefreshTrigger = MutableSharedFlow<String>(replay = 1)

    @OptIn(ExperimentalCoroutinesApi::class)
    val habitUIState: StateFlow<HabitDetailsUIState> = habitRefreshTrigger.flatMapLatest { habitId ->
        habitsRepository.getHabitById(habitId).map { habit ->
            if (habit == null) {
                showDialog(HabitDetailsDialogType.LoadHabitFailed)
                return@map HabitDetailsUIState.Failure
            }

            HabitDetailsUIState.Success(habit.toHabitUIData())
        }.onStart { emit(HabitDetailsUIState.Loading) }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000L),
        initialValue = HabitDetailsUIState.Loading,
    )

    fun onCloseClick() = navigateBack()

    fun onDeleteClick() = showDialog(HabitDetailsDialogType.DeleteHabit)

    fun onEditTaskClick(taskId: String) {
        viewModelScope.launch {
            uiEvent.emit(HabitDetailsEvent.NavigateToEditTask(taskId))
        }
    }

    fun onEditNameClick() {
    }

    fun refreshHabit(habitId: String) {
        viewModelScope.launch {
            habitRefreshTrigger.emit(habitId)
        }
    }

    private fun deleteHabit() {
        viewModelScope.launch {
            val habitUIState = habitUIState.value
            if (habitUIState is HabitDetailsUIState.Success) {
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

    //region --- Dialog ---
    private val _isDialogVisible = MutableStateFlow(false)
    private val _dialogType = MutableStateFlow<HabitDetailsDialogType>(HabitDetailsDialogType.Generic)
    private val _dialogCallbacks = MutableStateFlow(GenericDialogCallbacks())
    val isDialogVisible: StateFlow<Boolean> get() = _isDialogVisible.asStateFlow()
    val dialogType: StateFlow<HabitDetailsDialogType> get() = _dialogType.asStateFlow()
    val dialogCallbacks: StateFlow<GenericDialogCallbacks> get() = _dialogCallbacks.asStateFlow()

    fun dismissDialog() = _isDialogVisible.update { false }

    private fun showDialog(dialogType: HabitDetailsDialogType) {
        _dialogType.update { dialogType }
        _dialogCallbacks.update { getDialogCallbacks(dialogType) }
        _isDialogVisible.update { true }
    }

    private fun getDialogCallbacks(dialogType: HabitDetailsDialogType): GenericDialogCallbacks =
        when (dialogType) {
            HabitDetailsDialogType.DeleteHabit -> GenericDialogCallbacks(
                onPositiveAction = ::deleteHabit,
                onNegativeAction = ::dismissDialog,
                onDismiss = ::dismissDialog,
            )

            HabitDetailsDialogType.LoadHabitFailed -> GenericDialogCallbacks(
                onPositiveAction = ::navigateBack,
                onDismiss = ::dismissDialog,
            )

            HabitDetailsDialogType.Generic -> GenericDialogCallbacks(
                onPositiveAction = ::dismissDialog,
                onDismiss = ::dismissDialog,
            )
        }
    //endregion
}
