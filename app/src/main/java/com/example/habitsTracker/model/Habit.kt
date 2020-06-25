package com.example.habitsTracker.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
data class Habit (
    @ColumnInfo var count: Int,
    @ColumnInfo var color: Int,
    @ColumnInfo var date: Int,
    @ColumnInfo var description: String?,
    @ColumnInfo var frequency: Int,
    @ColumnInfo var priority: Int,
    @ColumnInfo var title: String?,
    @ColumnInfo var type: Int,
    @PrimaryKey @ColumnInfo var uid: String
    ) : Serializable {

    fun addUid(uid: HabitUID) {
        this.uid = uid.uid
    }

    fun toSimpleHabit(): SimpleHabit {
        return SimpleHabit(
            count,
            color,
            date,
            description,
            frequency,
            priority,
            title,
            type
        )
    }

    companion object {
        fun create() = Habit(
                    -1,
                    -1,
                    -1,
            null,
                    -1,
            -1,
            null,
            HabitType.UNDEFINED.value,
            "foo"
        )
    }
}