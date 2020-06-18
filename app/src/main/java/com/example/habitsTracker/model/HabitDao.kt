package com.example.habitsTracker.model

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface HabitDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addHabit(habit: Habit)

    @Query("select * from habit")
    fun getHabits(): LiveData<List<Habit>>

    @Update
    suspend fun updateHabit(habit: Habit)

    @Delete
    suspend fun deleteHabit(habit: Habit)
}