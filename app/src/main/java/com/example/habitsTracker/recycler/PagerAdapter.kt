package com.example.habitsTracker.recycler

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.habitsTracker.model.HabitType
import com.example.habitsTracker.screens.ListFragment
import java.lang.IllegalStateException

class PagerAdapter(activity : AppCompatActivity) : FragmentStateAdapter(activity) {
    override fun getItemCount() = 2

    override fun createFragment(position: Int): Fragment {
        return when(position) {
            0 -> ListFragment.newInstance("good fragment", HabitType.GOOD)
            1 -> ListFragment.newInstance("bad fragment", HabitType.BAD)
            else -> throw IllegalStateException("Illegal position")
        }
    }
}
