package com.example.habittrackernew.ui.screens.habits.add.task

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.repositories.temporary.TemporaryHabitRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.DayOfWeek
import java.time.LocalTime
import javax.inject.Inject

@HiltViewModel
class AddTaskViewModel @Inject constructor(
    private val temporaryHabitRepository: TemporaryHabitRepository
) : ViewModel() {

    //region --- Private ---
    private val _name = MutableStateFlow("")
    private val _daysOfWeek = MutableStateFlow<List<DayOfWeek>>(emptyList())
    private val _time = MutableStateFlow<LocalTime?>(null)
    private val _isTimePickerVisible = MutableStateFlow(false)
    //endregion

    //region --- UI ---
    val name: StateFlow<String> get() = _name.asStateFlow()
    val daysOfWeek: StateFlow<List<DayOfWeek>> get() = _daysOfWeek.asStateFlow()
    val time: StateFlow<LocalTime?> get() = _time.asStateFlow()
    val isTimePickerVisible: StateFlow<Boolean> get() = _isTimePickerVisible.asStateFlow()

    val isConfirmEnabled: StateFlow<Boolean> = combine(
        name,
        daysOfWeek,
        time
    ) { name, daysOfWeek, time ->
        name.isNotBlank() && daysOfWeek.isNotEmpty() && time != null
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000L),
        initialValue = false
    )
    //endregion

    //region --- Events ---
    val uiEvent: MutableSharedFlow<AddTaskEvent> = MutableSharedFlow()

    sealed class AddTaskEvent {
        data object NavigateBack : AddTaskEvent()
    }
    //endregion

    //region --- Callbacks ---
    fun updateName(name: String) = _name.update { name }

    fun onDayOfWeekClick(day: DayOfWeek) {
        _daysOfWeek.update { daysOfWeek ->
            if (daysOfWeek.contains(day)) {
                daysOfWeek - day
            } else {
                daysOfWeek + day
            }
        }
    }

    fun onTimeClick() = _isTimePickerVisible.update { true }

    fun onTimePickerDismiss() = _isTimePickerVisible.update { false }

    fun onTimePickerConfirm(time: LocalTime?) {
        _time.update { time }
        _isTimePickerVisible.update { false }
    }

    fun addTask() {
        viewModelScope.launch {
            time.value?.let { time ->
                if (name.value.isBlank() || daysOfWeek.value.isEmpty()) return@launch
                else temporaryHabitRepository.addTask(
                    name = name.value,
                    daysOfWeek = daysOfWeek.value,
                    time = time
                )
            }

            uiEvent.emit(AddTaskEvent.NavigateBack)
        }
    }
    //endregion
}
