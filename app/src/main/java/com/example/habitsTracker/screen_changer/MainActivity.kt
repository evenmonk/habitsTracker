package com.example.habitsTracker.screen_changer

import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import androidx.navigation.Navigation
import com.example.habitsTracker.R
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(),
    NavigationView.OnNavigationItemSelectedListener {

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navigation_drawer.setNavigationItemSelectedListener(this)

        navController = Navigation.findNavController(this, R.id.nav_host_fragment)
        navController.navigate(R.id.mainFragment)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        drawer_layout.closeDrawers()
        return when (item.itemId) {
            R.id.main -> {
                navController.navigate(R.id.mainFragment, null, popUpBehaviour)
                true
            }
            R.id.about -> {
                navController.navigate(R.id.aboutFragment, null, popUpBehaviour)
                true
            }
            else -> {
                    Toast.makeText(this, "Hello there", Toast.LENGTH_SHORT).show()
                    false
            }
        }
    }
    companion object {
        val popUpBehaviour = NavOptions.Builder().setPopUpTo(R.id.main_graph, true).build()
    }
}