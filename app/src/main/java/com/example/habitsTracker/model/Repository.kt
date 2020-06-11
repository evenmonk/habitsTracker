package com.example.habitsTracker.model

import androidx.lifecycle.LiveData
import com.example.habitsTracker.App


class Repository(private val habitDao: HabitDao) {
    fun getHabitsLiveData(): LiveData<List<Habit>> {
        return habitDao.getHabits()
    }

    fun getHabit(id: Int?): Habit {
        if (id == null || id == -1)
            return Habit.create()
        return habitDao.getHabit(id)
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
        val isNewHabit = habit.type == HabitType.UNDEFINED

        habit.name = name
        habit.description = description
        habit.priority = priority
        habit.type = type
        habit.period = period
        habit.quantity = quantity
        habit.color = color

        if (isNewHabit) {
            habitDao.addHabit(habit)
        } else {
            habitDao.updateHabit(habit)
        }
    }

    companion object {
        fun get(): Repository {
            return App.repository
        }
    }
}
