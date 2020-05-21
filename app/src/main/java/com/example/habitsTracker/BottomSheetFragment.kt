package com.example.habitsTracker

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.habitsTracker.pattern.ListViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.bottom_sheet.*

class BottomSheetFragment: BottomSheetDialogFragment() {

    private lateinit var viewModel: ListViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(requireActivity(), object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return ListViewModel() as T
            }
        }).get(ListViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.bottom_sheet, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sheet_search.setText(viewModel.filterString)

        sheet_search.addTextChangedListener {editable ->
            val input = editable.toString()
            viewModel.filter(input)
        }

        sheet_straight.setOnCheckedChangeListener { _, isChecked ->
            if(isChecked){
                viewModel.changeOrder(true)
            }
        }

        sheet_reverse.setOnCheckedChangeListener { _, isChecked ->
            if(isChecked){
                viewModel.changeOrder(false)
            }
        }

        (if(viewModel.straight)
            sheet_straight
        else
            sheet_reverse).isChecked = true
    }
}