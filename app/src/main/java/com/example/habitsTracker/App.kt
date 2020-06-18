package com.example.habitsTracker

import android.app.Application
import androidx.room.Room
import com.example.habitsTracker.model.Database
import com.example.habitsTracker.model.HabitDao
import com.example.habitsTracker.model.Repository

class App : Application() {
    override fun onCreate() {
        super.onCreate()

        val db = Room.databaseBuilder(
            this,
            Database::class.java, "db"
        ).fallbackToDestructiveMigration().build()

        val habitDao = db.habitDao()

        repository = Repository(habitDao)
    }

    companion object {
        lateinit var repository: Repository
    }
}
