package com.example.habitsTracker.pattern

import android.graphics.Color
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class Repository {
    companion object {
        private val mutableHasChanges: MutableLiveData<Boolean> = MutableLiveData()
        val hasChanges: MutableLiveData<Boolean> = mutableHasChanges

        private val mutableHabits = mutableListOf(
            Habit(
                0,
                "Бег",
                "Бегать по утрам",
                1,
                HabitType.GOOD,
                1,
                3,
                Color.GREEN
            ),
            Habit(
                1,
                "Курение",
                "Курить кальян",
                2,
                HabitType.BAD,
                2,
                1,
                Color.BLACK
            )
        )

        private val mutableGoodHabits =
            mutableHabits.filter { it.type == HabitType.GOOD }.toMutableList()
        private val mutableBadHabits =
            mutableHabits.filter { it.type == HabitType.BAD }.toMutableList()

        val goodHabits: List<Habit> = mutableGoodHabits
        val badHabits: List<Habit> = mutableBadHabits

        fun getHabit(id: Int?) : Habit {
            if (id == null || id == -1)
                return Habit.create()
            mutableHabits.forEach {
                if(it.id == id)
                    return it
            }
            return Habit.create()
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
            val changed = habit.type != type

            habit.name = name
            habit.description = description
            habit.priority = priority
            habit.type = type
            habit.period = period
            habit.quantity = quantity
            habit.color = color

            if (isNewHabit) {
                habit.id = mutableHabits.size
                mutableHabits.add(habit)

                (if (type == HabitType.GOOD) mutableGoodHabits else mutableBadHabits).add(habit)

            } else if (changed) {
                if (type == HabitType.GOOD) {
                    mutableBadHabits.remove(habit)
                    mutableGoodHabits.add(habit)
                } else if (type == HabitType.BAD) {
                    mutableGoodHabits.remove(habit)
                    mutableBadHabits.add(habit)
                }
            }

            mutableGoodHabits.sortBy { it.priority }
            mutableBadHabits.sortBy { it.priority }
            mutableHasChanges.value = true
        }
    }
}