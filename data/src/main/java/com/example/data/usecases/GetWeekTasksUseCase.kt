package com.example.data.usecases

import com.example.data.mappers.asWeekTask
import com.example.data.model.WeekTask
import com.example.data.repositories.HabitsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map

/**
 * This UseCase is intended to retrieve the week tasks.
 */
class GetWeekTasksUseCase(private val habitsRepository: HabitsRepository) {

    operator fun invoke(): Flow<List<WeekTask>> {
        return habitsRepository.getAllHabits().map { habits ->
            // Flatten the list of habits into a list of tasks
            habits.flatMap { habit ->
                // Map each day of week of the task to a WeekTask
                habit.tasks.flatMap { task ->
                    task.daysOfWeek.map { dayOfWeek ->
                        task.asWeekTask(
                            dayOfWeek = dayOfWeek,
                            habitId = habit.id,
                            habitName = habit.name,
                            habitColor = habit.color,
                        )
                    }
                }
            }
        }.flowOn(Dispatchers.Default) // Default dispatcher for heavy data transformations
    }
}
