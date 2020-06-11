package com.example.habitsTracker.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.habitsTracker.R
import com.example.habitsTracker.model.Habit

class Adapter: RecyclerView.Adapter<ViewHolder>() {
    private var filterString = ""
    private val straightOrder = true

    private var myHabits: List<Habit> = listOf()
    private var currentItems: List<Habit> = myHabits

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
        return currentItems.size
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(currentItems[position])
    }

    fun setItems(list: List<Habit>){
        myHabits = list
        currentItems = myHabits
        notifyDataSetChanged()
    }

    fun actualizeItems(prefix: String) {
        currentItems = myHabits.filter { it.name?.startsWith(prefix) ?: false }
        notifyDataSetChanged()
    }

    fun actualizeItems(order: Boolean) {
        currentItems =
            if (order)
                currentItems.sortedBy { it.priority }
            else
                currentItems.sortedByDescending { it.priority }

        notifyDataSetChanged()
    }
}
