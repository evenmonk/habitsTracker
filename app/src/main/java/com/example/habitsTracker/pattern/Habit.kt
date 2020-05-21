package com.example.habitsTracker.pattern

data class Habit (
    val id: Int,
    var name: String?,
    var description: String?,
    var priority: Int,
    var type: HabitType,
    var period: Int,
    var quantity : Int,
    var color: Int) {
    companion object {
        fun create(): Habit {
            return Habit(
                -1,
                null,
                null,
                -1,
                HabitType.UNDEFINED,
                -1,
                -1,
                -1
            )
        }
    }
}