package com.example.habittrackernew.ui.screens.habits.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.model.Habit
import com.example.data.repositories.HabitsRepository
import com.example.habittrackernew.ui.screens.habits.model.toHabitUIData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HabitsListViewModel @Inject constructor(private val habitsRepository: HabitsRepository) : ViewModel() {
    private val refreshTrigger = MutableSharedFlow<Unit>(replay = 1)

    @OptIn(ExperimentalCoroutinesApi::class)
    val uiState: StateFlow<HabitsListUIState> = refreshTrigger.flatMapLatest {
        println("[Felipe] Loading Habits")
        habitsRepository.getAllHabits().map { habits ->
            return@map if (habits.isEmpty()) {
                println("[Felipe] No Habits")
                HabitsListUIState.NoHabits
            } else {
                println("[Felipe] Habits Success")
                HabitsListUIState.Success(
                    habits = habits
                        .map(Habit::toHabitUIData)
                        .also {
                            println("[Felipe] Habits: $it")
                        },
                )
            }
        }.onStart { emit(HabitsListUIState.Loading) }
    }.stateIn(
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
}
