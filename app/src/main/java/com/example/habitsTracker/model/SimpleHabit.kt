package com.example.habitsTracker.model

data class SimpleHabit(
    var count: Int,
    var color: Int,
    var date: Int,
    var description: String?,
    var frequency: Int,
    var priority: Int,
    var title: String?,
    var type: Int
)
