package com.example.habitsTracker.model

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface HabitDao {
    @Insert
    fun addHabit(habit: Habit)

    @Query("select * from habit")
    fun getHabits(): LiveData<List<Habit>>

    @Query("select * from habit where id=:id")
    fun getHabit(id: Int): Habit

    @Update
    fun updateHabit(habit: Habit)
}