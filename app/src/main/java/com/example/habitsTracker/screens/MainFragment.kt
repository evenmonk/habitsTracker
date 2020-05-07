package com.example.habitsTracker.screens

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.habitsTracker.R
import com.example.habitsTracker.pattern.HabitType
import com.example.habitsTracker.recycler.PagerAdapter
import com.example.habitsTracker.screen_changer.FragmentChanger
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.fragment_main.*


class MainFragment : Fragment() {

    private var callback : FragmentChanger? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        callback = activity as FragmentChanger
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.fragment_main, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        activity?.let {
            view_pager.adapter = PagerAdapter(it as AppCompatActivity)
            TabLayoutMediator(tab_layout, view_pager){ tab, position ->
                val type = when(position) {
                    0 -> "Good habits"
                    1 -> "Bad habits"
                    else -> throw IllegalStateException("Illegal position")
                }
                tab.text = type
            }.attach()
        }

        fab.setOnClickListener {
            callback?.startDetailsScreen(null)
        }
    }

    companion object {
        private const val ARGS_NAME = "args_name"
        private const val ARGS_TYPE = "args_type"

        fun newInstance(name : String, habitType: HabitType) : MainFragment{
            val fragment =  MainFragment()
            val bundle = Bundle()
            bundle.putString(ARGS_NAME, name)
            bundle.putSerializable(ARGS_TYPE, habitType)
            fragment.arguments = bundle
            return fragment
        }
    }
}