package com.example.habitsTracker.screens

import android.content.Context
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
import com.example.habitsTracker.R
import com.example.habitsTracker.pattern.Habit
import com.example.habitsTracker.pattern.HabitType
import com.example.habitsTracker.pattern.Repository
import com.example.habitsTracker.screen_changer.FragmentChanger
import kotlinx.android.synthetic.main.fragment_details.*

class DetailsFragment : Fragment(), View.OnClickListener {

    private var habit : Habit? = null
    private var selectedColor = -1

    private var callback : FragmentChanger? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        callback = activity as FragmentChanger
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.fragment_details, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        save.setOnClickListener(this)

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

        for(i in 0..15){

            val imageView = ImageView(activity)
            imageView.setImageResource(R.drawable.square)
            imageView.layoutParams = lp
            imageView.setBackgroundResource(R.drawable.border_black)

            linear_layout.addView(imageView)

            val xPosition = (marginSide * 2 + squareSize) * i + marginSide + halfSize
            val yPosition = marginTop + halfSize

            val pixel = bitmap.getPixel(xPosition, yPosition)

            val r = Color.red(pixel)
            val g = Color.green(pixel)
            val b = Color.blue(pixel)

            val color = Color.rgb(r, g, b)

            imageView.setColorFilter(color)

            imageView.setOnClickListener {
                selected_color.colorFilter = imageView.colorFilter
                selectedColor = color
                val a = FloatArray(3)
                Color.colorToHSV(color, a)
                rgb.text = resources.getString(R.string.rgb_formatted, r, g, b)
                hsv.text = resources.getString(R.string.hsv_formatted, a[0], a[1], a[2])
            }
        }
    }

    private fun setValues(){
        habit = Repository.getHabit(arguments?.getString(ARGS_HABIT_NAME))

        if(habit != null){
            name_edit.setText(habit?.name)
            description_edit.setText(habit?.description)
            priority_spinner.setSelection(habit?.priority!! - 1)
            when (habit?.type) {
                HabitType.GOOD -> good_radio.isChecked = true
                HabitType.BAD -> bad_radio.isChecked = true
            }
            quantity_edit.setText(habit?.quantity!!.toString())
            period_edit.setText(habit?.period!!.toString())
            selected_color.setColorFilter(habit?.color!!)
            selectedColor = habit?.color!!
        }
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

        val type = when {
            bad_radio.isChecked -> HabitType.BAD
            good_radio.isChecked -> HabitType.GOOD
            else -> throw IllegalStateException("No type selected")
        }

        if(habit == null){
            val habit = Habit(
                name_edit.text.toString(),
                description_edit.text.toString(),
                priority_spinner.selectedItem.toString().toInt(),
                type,
                period_edit.text.toString().toInt(),
                quantity_edit.text.toString().toInt(),
                selectedColor
            )

            Repository.addHabit(habit)
        } else {
            habit?.name = name_edit.text.toString()
            habit?.description = description_edit.text.toString()
            habit?.priority = priority_spinner.selectedItem.toString().toInt()
            habit?.type = type
            habit?.period =  period_edit.text.toString().toInt()
            habit?.quantity = quantity_edit.text.toString().toInt()
            habit?.color = selectedColor
        }

        activity?.supportFragmentManager?.popBackStack()
    }

    companion object {
        private const val ARGS_NAME = "args_name"
        private const val ARGS_HABIT_NAME = "args_habit_name"

        fun newInstance(name : String, habitName : String?) : DetailsFragment{
            val fragment =  DetailsFragment()
            val bundle = Bundle()
            bundle.putString(ARGS_NAME, name)
            bundle.putString(ARGS_HABIT_NAME, habitName)
            fragment.arguments = bundle
            return fragment
        }
    }
}