package com.example.habitsTracker.model

import androidx.lifecycle.ViewModel

class DetailsViewModel: ViewModel() {
    fun getHabit(id: Int?): Habit {
        return Repository.get().getHabit(id)
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
        Repository.get().resolveHabit(
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

