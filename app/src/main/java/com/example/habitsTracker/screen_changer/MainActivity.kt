package com.example.habitsTracker.screen_changer

import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import com.example.habitsTracker.R
import com.example.habitsTracker.pattern.HabitType
import com.example.habitsTracker.screens.AboutFragment
import com.example.habitsTracker.screens.DetailsFragment
import com.example.habitsTracker.screens.MainFragment
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(),
    FragmentChanger, NavigationView.OnNavigationItemSelectedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val drawerToggle = ActionBarDrawerToggle(this, drawer_layout, R.string.HSV, R.string.RGB)

        drawer_layout.addDrawerListener(drawerToggle)

        navigation_drawer.setNavigationItemSelectedListener(this)

        val fragment =
            MainFragment.newInstance(
                "name",
                HabitType.BAD
            )

        val transaction = supportFragmentManager.beginTransaction()

        transaction
            .add(R.id.main_container, fragment, "main_fragment")
            .commit()
    }

    override fun startDetailsScreen(habitName : String?) {
        val fragment =
            DetailsFragment.newInstance(
                "another name",
                habitName
            )
        supportFragmentManager.beginTransaction()
            .replace(R.id.main_container, fragment, "details_fragment")
            .addToBackStack("backstack")
            .commit()
    }

    override fun startMainScreen() {
        val fragment = MainFragment.newInstance("name", HabitType.BAD)

        supportFragmentManager.popBackStackImmediate()

        supportFragmentManager.beginTransaction()
            .replace(R.id.main_container, fragment, "list_fragment")
            .commit()
    }

    override fun startAboutScreen() {
        val fragment = AboutFragment.newInstance("name")

        supportFragmentManager.popBackStackImmediate()

        supportFragmentManager.beginTransaction()
            .replace(R.id.main_container, fragment, "about_fragment")
            .commit()
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.main -> {
                startMainScreen()
                true
            }
            R.id.about -> {
                startAboutScreen()
                true
            }
            else -> {
                Toast.makeText(this, "Hello there", Toast.LENGTH_SHORT).show()
                false
            }
        }
    }


}