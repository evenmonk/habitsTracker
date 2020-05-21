package com.example.habitsTracker.pattern

import androidx.lifecycle.ViewModel

class DetailsViewModel: ViewModel() {
    fun getHabit(id: Int?): Habit {
        return Repository.getHabit(id)
    }

    fun resolveHabit(
        habit: Habit,
        name: String?,
        description: String?,
        priority: Int,
        type: HabitType,
        period: Int,
        quantity: Int,
        color: Int
    ) {
        Repository.resolveHabit(
            habit,
            name,
            description,
            priority,
            type,
            period,
            quantity,
            color
        )
    }
}
