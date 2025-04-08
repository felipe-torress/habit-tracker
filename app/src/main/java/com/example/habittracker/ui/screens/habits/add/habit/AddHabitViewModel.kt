package com.example.habittracker.ui.screens.habits.add.habit

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.model.HabitColor
import com.example.data.model.HabitTask
import com.example.data.repositories.temporary.TemporaryHabitRepository
import com.example.habittracker.ui.screens.habits.add.task.TaskEntryFlow
import com.example.habittracker.ui.screens.habits.model.ColorUI
import com.example.habittracker.ui.screens.habits.model.HabitTaskUIData
import com.example.habittracker.ui.screens.habits.model.toColorUI
import com.example.habittracker.ui.screens.habits.model.toHabitColor
import com.example.habittracker.ui.screens.habits.model.toHabitTaskUIData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddHabitViewModel @Inject constructor(
    private val temporaryHabitRepository: TemporaryHabitRepository,
) : ViewModel() {
    //region --- Private ---
    private val _name = MutableStateFlow("")
    private val _color = MutableStateFlow<HabitColor?>(null)
    //endregion

    //region --- Events ---
    val uiEvent: MutableSharedFlow<AddHabitEvent> = MutableSharedFlow()

    sealed class AddHabitEvent {
        data object NavigateBack : AddHabitEvent()
        data class NavigateToTaskEntry(val taskEntryFlow: TaskEntryFlow.TemporaryTask) : AddHabitEvent()
    }
    //endregion

    //region --- UI ---
    val name: StateFlow<String> get() = _name.asStateFlow()

    val color: StateFlow<ColorUI?> = _color.map { color ->
        color?.toColorUI()
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000L),
        initialValue = null,
    )

    val tasks: StateFlow<List<HabitTaskUIData>> = temporaryHabitRepository.temporaryTasks.map { tasks ->
        tasks.map(HabitTask::toHabitTaskUIData)
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000L),
        initialValue = emptyList(),
    )

    val isAddHabitEnabled: StateFlow<Boolean> = combine(
        name,
        color,
        tasks,
    ) { name, color, tasks ->
        name.isNotBlank() && color != null && tasks.isNotEmpty()
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000L),
        initialValue = false,
    )
    //endregion

    //region --- Callbacks ---
    fun updateHabitName(name: String) = _name.update { name }

    fun onColorClick(color: ColorUI) = _color.update { color.toHabitColor() }

    fun onAddHabitClick() {
        viewModelScope.launch {
            temporaryHabitRepository.saveTemporaryHabit(
                name = name.value,
                color = _color.value ?: HabitColor.BLUE,
            )

            uiEvent.emit(AddHabitEvent.NavigateBack)
        }
    }

    fun onCloseClick() {
        viewModelScope.launch {
            temporaryHabitRepository.discardTemporaryHabit()

            uiEvent.emit(AddHabitEvent.NavigateBack)
        }
    }

    fun onAddTaskClick() {
        viewModelScope.launch {
            uiEvent.emit(AddHabitEvent.NavigateToTaskEntry(TaskEntryFlow.TemporaryTask.Add))
        }
    }

    fun onEditTaskClick(taskId: String) {
        viewModelScope.launch {
            uiEvent.emit(AddHabitEvent.NavigateToTaskEntry(TaskEntryFlow.TemporaryTask.Edit(taskId)))
        }
    }
    //endregion
}
