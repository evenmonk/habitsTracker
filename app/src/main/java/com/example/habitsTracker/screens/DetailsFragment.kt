package com.example.habitsTracker.screens

import android.app.Activity
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Toast
import androidx.core.content.res.ResourcesCompat
import androidx.core.graphics.drawable.toBitmap
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.habitsTracker.R
import com.example.habitsTracker.model.*
import kotlinx.android.synthetic.main.fragment_details.*

class DetailsFragment : Fragment(), View.OnClickListener {

    private lateinit var viewModel: DetailsViewModel
    private lateinit var habit : Habit
    private var selectedColor = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this, object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return DetailsViewModel() as T
            }
        }).get(DetailsViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? =
        inflater.inflate(R.layout.fragment_details, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        save.setOnClickListener(this)
        habit = viewModel.getHabit(arguments?.getInt(ARGS_HABIT_ID) ?: -1)
        addImages()

        setValues()
    }

    private fun addImages(){
        val lp = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.WRAP_CONTENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )

        val squareSize = resources.getDimension(R.dimen.square_size).toInt()

        val marginSide = resources.getDimension(R.dimen.margin_side).toInt()
        val marginTop = resources.getDimension(R.dimen.padding_vertical).toInt()

        val spectrumHeight = resources.getDimension(R.dimen.height).toInt()
        val spectrumWidth = (resources.getDimension(R.dimen.square_size).toInt() + 2 * marginSide) * 16

        val bitmap = ResourcesCompat
            .getDrawable(resources, R.drawable.hue2, null)!!
            .toBitmap(width = spectrumWidth, height = spectrumHeight)

        lp.width = squareSize
        lp.height = squareSize
        lp.marginEnd = marginSide
        lp.marginStart = marginSide

        val halfSize = squareSize / 2
        val doubleMarginSide = marginSide * 2

        val a = FloatArray(3)

        for(i in 0..15){

            val imageView = ImageView(activity)
            imageView.setImageResource(R.drawable.square)
            imageView.layoutParams = lp
            imageView.setBackgroundResource(R.drawable.border_black)

            linear_layout.addView(imageView)

            val pixel = bitmap.getPixel(
                (doubleMarginSide + squareSize) * i + marginSide + halfSize,
                marginTop + halfSize
            )

            val r = Color.red(pixel)
            val g = Color.green(pixel)
            val b = Color.blue(pixel)

            val color = Color.rgb(r, g, b)

            imageView.setColorFilter(color)

            imageView.setOnClickListener {
                selected_color.colorFilter = imageView.colorFilter
                selectedColor = color
                Color.colorToHSV(color, a)
                rgb.text = resources.getString(R.string.rgb_formatted, r, g, b)
                hsv.text = resources.getString(R.string.hsv_formatted, a[0], a[1], a[2])
            }
        }
    }

    private fun setValues(){
        when (habit.type) {
            HabitType.GOOD -> good_radio.isChecked = true
            HabitType.BAD -> bad_radio.isChecked = true
            else -> return
        }
        name_edit.setText(habit.name)
        description_edit.setText(habit.description)
        priority_spinner.setSelection(habit.priority - 1)
        quantity_edit.setText(habit.quantity.toString())
        period_edit.setText(habit.period.toString())
        selected_color.setColorFilter(habit.color)
        selectedColor = habit.color
    }

    override fun onClick(v: View?) {
        if(name_edit.text.isEmpty()
            || selectedColor == -1
            || quantity_edit.text.isEmpty()
            || period_edit.text.isEmpty()
            || description_edit.text.isEmpty()
            || (!bad_radio.isChecked && !good_radio.isChecked)){
            Toast
                .makeText(activity, "Заполните все поля", Toast.LENGTH_SHORT)
                .show()
            return
        }

        viewModel.resolveHabit(
            habit,
            name_edit.text.toString(),
            description_edit.text.toString(),
            priority_spinner.selectedItem.toString().toInt(),
            when {
                bad_radio.isChecked -> HabitType.BAD
                good_radio.isChecked -> HabitType.GOOD
                else -> throw IllegalStateException("No type selected")
            },
            period_edit.text.toString().toInt(),
            quantity_edit.text.toString().toInt(),
            selectedColor
        )

        Navigation.findNavController(activity as Activity, R.id.nav_host_fragment).popBackStack()
    }

    companion object {
        const val ARGS_HABIT_ID = "args_habit_id"
    }
}