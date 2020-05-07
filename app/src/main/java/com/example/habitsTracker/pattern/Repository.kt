package com.example.habitsTracker.pattern

import android.graphics.Color

class Repository {
    companion object {
        val habits = mutableListOf(
            Habit(
                "Бег",
                "Бегать по утрам",
                1,
                HabitType.GOOD,
                1,
                3,
                Color.GREEN
            ),
            Habit(
                "Курение",
                "Курить кальян",
                2,
                HabitType.BAD,
                2,
                1,
                Color.BLACK
            )
        )

        fun addHabit(habit: Habit){
            habits.add(habit)
        }

        fun getHabit(name: String?) : Habit? {
            if(name == null)
                return null
            habits.forEach {
                if(it.name == name)
                    return it
            }
            return null
        }
    }
}