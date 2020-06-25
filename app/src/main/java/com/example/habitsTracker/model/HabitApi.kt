package com.example.habitsTracker.model

import retrofit2.http.*

interface HabitApi {
    @GET("habit")
    suspend fun getHabits(): List<Habit>

    @PUT("habit")
    suspend fun updateHabit(@Body habit: Habit): HabitUID

    @PUT("habit")
    suspend fun addHabit(@Body habit: SimpleHabit): HabitUID

    @HTTP(method = "DELETE", path = "habit", hasBody = true)
    suspend fun deleteHabit(@Body uid: HabitUID)
}