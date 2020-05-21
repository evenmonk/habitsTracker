package com.example.habitsTracker.screens

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.habitsTracker.R
import com.example.habitsTracker.recycler.PagerAdapter
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.fragment_main.*


class MainFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? =
        inflater.inflate(R.layout.fragment_main, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        activity?.let {
            view_pager.adapter = PagerAdapter(it as AppCompatActivity)
            TabLayoutMediator(tab_layout, view_pager) { tab, position ->
                val type = when (position) {
                    0 -> "Good habits"
                    1 -> "Bad habits"
                    else -> throw IllegalStateException("Illegal position")
                }
                tab.text = type
            }.attach()
        }
    }
}