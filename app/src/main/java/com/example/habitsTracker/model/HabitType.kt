package com.example.habitsTracker.model

enum class HabitType(val value: Int) {
    GOOD(0),
    BAD(1),
    UNDEFINED(2);

    companion object {
        private val map = HashMap<Int, HabitType>()

        fun valueOf(int: Int): HabitType {
            return map[int] as HabitType
        }

        init {
            for (habitType in values()) {
                map[habitType.value] = habitType
            }
        }
    }
}
