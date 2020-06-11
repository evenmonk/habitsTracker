package com.example.habitsTracker.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Habit (
    @ColumnInfo var name: String?,
    @ColumnInfo var description: String?,
    @ColumnInfo var priority: Int,
    @ColumnInfo var type: HabitType,
    @ColumnInfo var period: Int,
    @ColumnInfo var quantity : Int,
    @ColumnInfo var color: Int) {

    @PrimaryKey(autoGenerate = true) var id: Int? = null

    companion object {
        fun create(): Habit {
                return Habit(
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