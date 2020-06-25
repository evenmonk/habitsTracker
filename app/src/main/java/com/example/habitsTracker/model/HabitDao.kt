package com.example.habitsTracker.model

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.habitsTracker.model.Habit

@Dao
interface HabitDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addHabit(habit: Habit)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addHabits(habits: List<Habit>)

    @Query("select * from habit")
    fun getHabits(): LiveData<List<Habit>>

    @Update
    suspend fun updateHabit(habit: Habit)

    @Delete
    suspend fun deleteHabit(habit: Habit)
}