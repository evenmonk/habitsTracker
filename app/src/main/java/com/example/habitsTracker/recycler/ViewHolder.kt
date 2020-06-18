package com.example.habitsTracker.recycler

import android.app.Activity
import android.os.Bundle
import android.view.View
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.habitsTracker.R
import com.example.habitsTracker.model.Habit
import com.example.habitsTracker.model.HabitType
import com.example.habitsTracker.screens.DetailsFragment
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.list_item.view.*
import java.lang.IllegalStateException

class ViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView), LayoutContainer {

    fun bind(habit: Habit) {
        containerView.name.text = habit.name
        containerView.description.text = habit.description

        val resId = when (habit.type) {
            HabitType.GOOD -> R.drawable.like
            HabitType.BAD -> R.drawable.dislike
            else -> throw IllegalStateException("Недопустимый тип")
        }

        containerView.type.setImageResource(resId)
        containerView.type.setColorFilter(habit.color)

        containerView.period.text = habit.period.toString()

        containerView.priority.text = habit.priority.toString()

        containerView.quantity.text = habit.quantity.toString()

        containerView.setOnClickListener {
            val bundle = Bundle()
            bundle.putSerializable(DetailsFragment.ARGS_HABIT, habit)
            Navigation.findNavController(containerView.context as Activity, R.id.nav_host_fragment)
                .navigate(R.id.detailsFragment, bundle)
        }
    }
}

