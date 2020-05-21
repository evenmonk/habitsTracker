package com.example.habitsTracker.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.habitsTracker.R
import com.example.habitsTracker.pattern.Habit

class Adapter: RecyclerView.Adapter<ViewHolder>() {

    private lateinit var myHabits : List<Habit>
    var straight = true

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
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val pos =
            if (straight)
                position
            else
                itemCount - position - 1

        holder.bind(myHabits[pos])
    }

    fun setItems(list: List<Habit>){
        myHabits = list
    }
}
