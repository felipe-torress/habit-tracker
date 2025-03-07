package com.example.habittrackernew.ui.screens.habits.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.model.Habit
import com.example.data.repositories.HabitsRepository
import com.example.habittrackernew.ui.screens.habits.model.toHabitUIData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HabitsListViewModel @Inject constructor(private val habitsRepository: HabitsRepository) : ViewModel() {
    //region --- Event ---
    val uiEvent: MutableSharedFlow<HabitsListEvent> = MutableSharedFlow()

    sealed class HabitsListEvent {
        data object NavigateToAddHabit : HabitsListEvent()

        data class NavigateToHabitDetails(val habitId: String) : HabitsListEvent()
    }
    //endregion

    private val refreshTrigger = MutableSharedFlow<Unit>(replay = 1)

    @OptIn(ExperimentalCoroutinesApi::class)
    val uiState: StateFlow<HabitsListUIState> = refreshTrigger.flatMapLatest {
        habitsRepository.getAllHabits().map { habits ->
            return@map if (habits.isEmpty()) {
                HabitsListUIState.NoHabits
            } else {
                HabitsListUIState.Success(habits = habits.map(Habit::toHabitUIData))
            }
        }.onStart { emit(HabitsListUIState.Loading) }
    }
        .flowOn(Dispatchers.Default)
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000L),
            initialValue = HabitsListUIState.Loading,
        )

    init {
        refresh()
    }

    fun refresh() {
        viewModelScope.launch {
            refreshTrigger.emit(Unit)
        }
    }

    fun onAddHabitClick() {
        viewModelScope.launch {
            uiEvent.emit(HabitsListEvent.NavigateToAddHabit)
        }
    }

    fun onHabitClick(habitId: String) {
        viewModelScope.launch {
            uiEvent.emit(HabitsListEvent.NavigateToHabitDetails(habitId))
        }
    }
}
