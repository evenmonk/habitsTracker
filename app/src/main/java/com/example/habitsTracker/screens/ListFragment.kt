package com.example.habitsTracker.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import com.example.habitsTracker.R
import com.example.habitsTracker.pattern.HabitType
import com.example.habitsTracker.pattern.Repository
import com.example.habitsTracker.recycler.Adapter
import kotlinx.android.synthetic.main.fragment_list.*

class ListFragment : Fragment() {
    private var adapter : Adapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val decoration =
            DividerItemDecoration(activity, DividerItemDecoration.VERTICAL)
        recycler.addItemDecoration(decoration)

        val habitType = arguments?.get(ARGS_TYPE) as HabitType

        adapter = Adapter(Repository.habits, habitType)


        recycler.adapter = adapter
    }

    override fun onResume() {
        super.onResume()
        Repository.habits.sortBy { it.priority }
        adapter?.setItems()
        adapter?.notifyDataSetChanged()
    }

    companion object {
        private const val ARGS_NAME = "args_name"
        private const val ARGS_TYPE = "args_type"

        fun newInstance(name : String, habitType: HabitType) : ListFragment{
            val fragment = ListFragment()
            val bundle = Bundle()
            bundle.putString(ARGS_NAME, name)
            bundle.putSerializable(ARGS_TYPE, habitType)
            fragment.arguments = bundle
            return fragment
        }
    }
}