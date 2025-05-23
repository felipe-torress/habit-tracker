package com.example.habittracker.ui.screens.habits.add.task

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.repositories.HabitTasksRepository
import com.example.data.repositories.temporary.TemporaryHabitRepository
import com.example.habittracker.ui.composables.dialogs.DialogCallbacks
import com.example.habittracker.ui.screens.habits.model.TaskEntryUIData
import com.example.habittracker.ui.screens.habits.model.toTaskEntryUIData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import timber.log.Timber
import java.time.DayOfWeek
import java.time.LocalTime
import javax.inject.Inject

@HiltViewModel
class TaskEntryViewModel @Inject constructor(
    private val temporaryHabitRepository: TemporaryHabitRepository,
    private val habitTasksRepository: HabitTasksRepository,
) : ViewModel() {
    //region --- UI ---
    private val initialTaskEntryData = MutableStateFlow(TaskEntryUIData())
    private val flow = MutableStateFlow<TaskEntryFlow>(TaskEntryFlow.TemporaryTask.Add)
    private var habitId: String? = null
    private var taskId: String? = null

    private val _taskEntryData = MutableStateFlow(TaskEntryUIData())
    private val _isTimePickerVisible = MutableStateFlow(false)
    val taskEntryData: StateFlow<TaskEntryUIData> get() = _taskEntryData.asStateFlow()
    val isTimePickerVisible: StateFlow<Boolean> get() = _isTimePickerVisible.asStateFlow()

    val isEditFlow: StateFlow<Boolean> = flow.map { flow ->
        flow is TaskEntryFlow.SavedTask.Edit || flow is TaskEntryFlow.TemporaryTask.Edit
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000L),
        initialValue = false,
    )

    val isConfirmEnabled: StateFlow<Boolean> = combine(
        taskEntryData,
        initialTaskEntryData,
    ) { taskEntryData, initialTaskEntryData ->
        taskEntryData != initialTaskEntryData && taskEntryData.isValidToSave()
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000L),
        initialValue = false,
    )
    //endregion

    //region --- Events ---
    val uiEvent: MutableSharedFlow<AddTaskEvent> = MutableSharedFlow()

    sealed class AddTaskEvent {
        data object NavigateBack : AddTaskEvent()
    }

    private fun navigateBack() {
        viewModelScope.launch {
            uiEvent.emit(AddTaskEvent.NavigateBack)
        }
    }
    //endregion

    //region --- Callbacks ---
    fun updateName(name: String) = _taskEntryData.update { it.updateName(name) }

    fun onDayOfWeekClick(day: DayOfWeek) {
        _taskEntryData.update { taskEntryData ->
            val daysOfWeek = taskEntryData.daysOfWeek.toMutableList()
            if (daysOfWeek.contains(day)) {
                taskEntryData.removeDayOfWeek(day)
            } else {
                taskEntryData.addDayOfWeek(day)
            }
        }
    }

    fun onTimeClick() = _isTimePickerVisible.update { true }

    fun onTimePickerDismiss() = _isTimePickerVisible.update { false }

    fun onTimePickerConfirm(time: LocalTime?) {
        _taskEntryData.update { it.updateTime(time) }
        _isTimePickerVisible.update { false }
    }

    fun loadTask(taskEntryFlow: TaskEntryFlow) {
        flow.update { taskEntryFlow }
        when (taskEntryFlow) {
            is TaskEntryFlow.TemporaryTask.Edit -> loadTemporaryTask(taskEntryFlow.taskId)
            is TaskEntryFlow.SavedTask.Edit -> loadSavedTask(taskEntryFlow.taskId)
            is TaskEntryFlow.SavedTask.Add -> habitId = taskEntryFlow.habitId
            is TaskEntryFlow.TemporaryTask.Add -> Unit
        }
    }

    fun onConfirmTaskEntryClick() {
        when (flow.value) {
            is TaskEntryFlow.SavedTask.Add -> addSavedTask()
            is TaskEntryFlow.SavedTask.Edit -> updateSavedTask()
            is TaskEntryFlow.TemporaryTask.Add -> addTemporaryTask()
            is TaskEntryFlow.TemporaryTask.Edit -> editTemporaryTask()
        }
    }

    fun onDeleteTaskClick() = showDialog(TaskEntryDialogType.Confirmation.Delete)
    //endregion

    private fun deleteTask() {
        when (flow.value) {
            is TaskEntryFlow.SavedTask.Edit -> deleteSavedTask()
            is TaskEntryFlow.TemporaryTask.Edit -> deleteTemporaryTask()
            else -> Unit
        }
    }

    //region --- Saved Task ---
    private fun loadSavedTask(taskId: String) {
        viewModelScope.launch(Dispatchers.IO) {
            habitTasksRepository.getHabitTaskById(taskId).first()?.let { task ->
                this@TaskEntryViewModel.taskId = task.id
                val taskEntryUIData = task.toTaskEntryUIData()
                initialTaskEntryData.update { taskEntryUIData }
                _taskEntryData.update { taskEntryUIData }
                Timber.i("Saved task loaded")
            } ?: Timber.w("Saved Task not found")
        }
    }

    private fun updateSavedTask() {
        viewModelScope.launch(Dispatchers.IO) {
            val taskId = taskId ?: return@launch
            _taskEntryData.value.getValidDataAndPerformAction { name, daysOfWeek, time ->
                habitTasksRepository.updateHabitTask(
                    habitTaskId = taskId,
                    name = name,
                    daysOfWeek = daysOfWeek,
                    time = time,
                )
                navigateBack()
            }
        }
    }

    private fun addSavedTask() {
        viewModelScope.launch(Dispatchers.IO) {
            _taskEntryData.value.getValidDataAndPerformAction { name, daysOfWeek, time ->
                habitId?.let { habitId ->
                    habitTasksRepository.createHabitTask(
                        habitId = habitId,
                        name = name,
                        daysOfWeek = daysOfWeek,
                        time = time,
                    )
                    navigateBack()
                } ?: Timber.e("Cannot add new task: Habit ID is null")
            }
        }
    }

    private fun deleteSavedTask() {
        viewModelScope.launch(Dispatchers.IO) {
            taskId?.let { taskId ->
                habitTasksRepository.deleteHabitTask(taskId)
                navigateBack()
            } ?: Timber.e("Cannot delete task: Task ID is null")
        }
    }
    //endregion

    //region --- Temporary Task ---
    private fun loadTemporaryTask(taskId: String) {
        temporaryHabitRepository.temporaryTasks.value.find {
            it.id == taskId
        }?.let { task ->
            this.taskId = task.id
            val taskEntryUIData = task.toTaskEntryUIData()
            initialTaskEntryData.update { taskEntryUIData }
            _taskEntryData.update { taskEntryUIData }
            Timber.i("Temporary task loaded")
        } ?: Timber.w("Temporary Task not found")
    }

    private fun editTemporaryTask() {
        viewModelScope.launch(Dispatchers.IO) {
            _taskEntryData.value.getValidDataAndPerformAction { name, daysOfWeek, time ->
                taskId?.let { taskId ->
                    temporaryHabitRepository.editTask(
                        taskId = taskId,
                        name = name,
                        daysOfWeek = daysOfWeek,
                        time = time,
                    )

                    navigateBack()
                }
            }
        }
    }

    private fun addTemporaryTask() {
        viewModelScope.launch(Dispatchers.IO) {
            _taskEntryData.value.getValidDataAndPerformAction { name, daysOfWeek, time ->
                temporaryHabitRepository.createTask(
                    name = name,
                    daysOfWeek = daysOfWeek,
                    time = time,
                )
                navigateBack()
            }
        }
    }

    private fun deleteTemporaryTask() {
        viewModelScope.launch(Dispatchers.IO) {
            taskId?.let { taskId ->
                temporaryHabitRepository.deleteTask(taskId)
                navigateBack()
            } ?: Timber.e("Cannot delete task: Task ID is null")
        }
    }
    //endregion

    //region --- Dialog ---
    private val _isDialogVisible = MutableStateFlow(false)
    private val _dialogType = MutableStateFlow<TaskEntryDialogType>(TaskEntryDialogType.Confirmation.Delete)
    private val _dialogCallbacks = MutableStateFlow<DialogCallbacks>(DialogCallbacks.BasicDialog())
    val isDialogVisible: StateFlow<Boolean> get() = _isDialogVisible.asStateFlow()
    val dialogType: StateFlow<TaskEntryDialogType> get() = _dialogType.asStateFlow()
    val dialogCallbacks: StateFlow<DialogCallbacks> get() = _dialogCallbacks.asStateFlow()

    fun dismissDialog() = _isDialogVisible.update { false }

    private fun showDialog(dialogType: TaskEntryDialogType) {
        _dialogType.update { dialogType }
        _dialogCallbacks.update { getDialogCallbacks(dialogType) }
        _isDialogVisible.update { true }
    }

    private fun getDialogCallbacks(dialogType: TaskEntryDialogType): DialogCallbacks =
        when (dialogType) {
            TaskEntryDialogType.Confirmation.Delete -> DialogCallbacks.BasicDialog(
                onPositiveAction = ::deleteTask,
                onNegativeAction = ::dismissDialog,
                onDismiss = ::dismissDialog,
            )
        }
    //endregion
}
