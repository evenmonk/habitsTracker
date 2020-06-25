package com.example.habitsTracker.model

import androidx.lifecycle.LiveData
import com.example.habitsTracker.App
import com.example.habitsTracker.model.HabitDao
import java.util.*

class Repository(private val habitDao: HabitDao, private val api: HabitApi) {

    fun getHabitsLiveData(): LiveData<List<Habit>> {
        return habitDao.getHabits()
    }

    suspend fun loadHabits() {
        val habits = api.getHabits()
        habitDao.addHabits(habits)
    }

    suspend fun deleteHabit(habit: Habit) {
        api.deleteHabit(HabitUID(habit.uid))
        habitDao.deleteHabit(habit)
    }

    suspend fun resolveHabit(
        habit: Habit,
        name: String?,
        description: String?,
        priority: Int,
        type: HabitType,
        period: Int,
        quantity: Int,
        color: Int
    ) {
        val isNewHabit = habit.type == HabitType.UNDEFINED.value

        habit.title = name
        habit.description = description
        habit.priority = priority
        habit.type = type.value
        habit.frequency = period
        habit.count = quantity
        habit.color = color
        habit.date = (Date().time / 1000).toInt()

        val uid = if (isNewHabit) {
            api.addHabit(habit.toSimpleHabit())
        } else {
            api.updateHabit(habit)
        }

        habit.addUid(uid)

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
