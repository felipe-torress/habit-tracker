package com.example.habittracker.ui.screens.habits.add.task

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.repositories.HabitTasksRepository
import com.example.data.repositories.temporary.TemporaryHabitRepository
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
    private val _initialTaskEntryData = MutableStateFlow(TaskEntryUIData())

    private val _taskEntryData = MutableStateFlow(TaskEntryUIData())
    private val _isTimePickerVisible = MutableStateFlow(false)
    val taskEntryData: StateFlow<TaskEntryUIData> get() = _taskEntryData.asStateFlow()
    val isTimePickerVisible: StateFlow<Boolean> get() = _isTimePickerVisible.asStateFlow()

    private var _taskId: String? = null
    private var _isSavedTask: Boolean = false

    val isConfirmEnabled: StateFlow<Boolean> = combine(
        taskEntryData,
        _initialTaskEntryData,
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

    fun loadTask(taskId: String, isSavedTask: Boolean = false) {
        _isSavedTask = isSavedTask
        if (isSavedTask) loadSavedTask(taskId) else loadTemporaryTask(taskId)
    }

    fun onConfirmTaskEntryClick() {
        if (_isSavedTask) updateSavedTask() else addTemporaryTask()
    }
    //endregion

    //region --- Saved Task ---
    private fun loadSavedTask(taskId: String) {
        viewModelScope.launch(Dispatchers.IO) {
            habitTasksRepository.getHabitTaskById(taskId).first()?.let { task ->
                _taskId = task.id
                val taskEntryUIData = task.toTaskEntryUIData()
                _initialTaskEntryData.update { taskEntryUIData }
                _taskEntryData.update { taskEntryUIData }
                Timber.i("Saved task loaded")
            } ?: Timber.w("Saved Task not found")
        }
    }

    private fun updateSavedTask() {
        viewModelScope.launch(Dispatchers.IO) {
            val taskId = _taskId ?: return@launch
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
    //endregion

    //region --- Temporary Task ---
    private fun loadTemporaryTask(taskId: String) {
        temporaryHabitRepository.temporaryTasks.value.find {
            it.id == taskId
        }?.let { task ->
            _taskId = task.id
            val taskEntryUIData = task.toTaskEntryUIData()
            _initialTaskEntryData.update { taskEntryUIData }
            _taskEntryData.update { taskEntryUIData }
            Timber.i("Temporary task loaded")
        } ?: Timber.w("Temporary Task not found")
    }

    private fun addTemporaryTask() {
        viewModelScope.launch(Dispatchers.IO) {
            _taskEntryData.value.getValidDataAndPerformAction { name, daysOfWeek, time ->
                temporaryHabitRepository.addTask(
                    taskId = _taskId,
                    name = name,
                    daysOfWeek = daysOfWeek,
                    time = time,
                )
                _taskEntryData.update { TaskEntryUIData() }
                navigateBack()
            }
        }
    }
    //endregion
}
