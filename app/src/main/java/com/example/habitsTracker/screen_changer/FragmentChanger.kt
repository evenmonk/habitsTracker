package com.example.habitsTracker.screen_changer

interface FragmentChanger {
    fun startDetailsScreen(habitName : String?)
    fun startMainScreen()
    fun startAboutScreen()
}