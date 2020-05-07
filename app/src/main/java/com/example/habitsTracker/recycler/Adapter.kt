package com.example.habitsTracker.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.habitsTracker.R
import com.example.habitsTracker.pattern.Habit
import com.example.habitsTracker.pattern.HabitType
import com.example.habitsTracker.pattern.Repository

class Adapter(private val habits: List<Habit>, private val habitType: HabitType) : RecyclerView.Adapter<ViewHolder>() {

    private var myHabits : List<Habit> = Repository.habits.filter { it.type == habitType }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ViewHolder(
            inflater.inflate(
                R.layout.list_item,
                parent,
                false
            )
        )
    }
    override fun getItemCount(): Int {
        return myHabits.size
    }
    override fun onBindViewHolder(holder: ViewHolder, pos: Int) {
        holder.bind(myHabits[pos])
    }

    fun setItems(){
        myHabits = Repository.habits.filter {
            it.type == habitType
        }
    }
}
