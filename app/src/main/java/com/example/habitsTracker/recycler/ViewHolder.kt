package com.example.habitsTracker.recycler

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.habitsTracker.R
import com.example.habitsTracker.pattern.Habit
import com.example.habitsTracker.pattern.HabitType
import com.example.habitsTracker.screen_changer.FragmentChanger
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.list_item.view.*

class ViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView), LayoutContainer {

    fun bind(habit: Habit) {
        containerView.name.text = habit.name
        containerView.description.text = habit.description

        val resId = when (habit.type) {
            HabitType.GOOD -> R.drawable.like
            HabitType.BAD -> R.drawable.dislike
        }

        containerView.type.setImageResource(resId)
        containerView.type.setColorFilter(habit.color)

        containerView.period.text = habit.period.toString()

        containerView.priority.text = habit.priority.toString()

        containerView.quantity.text = habit.quantity.toString()

        containerView.setOnClickListener {
            (containerView.context as FragmentChanger).startDetailsScreen(habit.name)
        }
    }
}
