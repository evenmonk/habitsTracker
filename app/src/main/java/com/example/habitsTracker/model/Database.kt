package com.example.habitsTracker.model

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [Habit::class], version = 3)
@TypeConverters(TypeConverter::class)
abstract class Database : RoomDatabase(){
    abstract fun habitDao(): HabitDao
}
