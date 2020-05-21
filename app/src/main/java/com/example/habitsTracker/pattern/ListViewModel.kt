package com.example.habitsTracker.pattern

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel

class ListViewModel: ViewModel() {
    val hasChanges: LiveData<Boolean> = Transformations.map(Repository.hasChanges) {
        if (straight) {
            mutableGoodHabits.value = Repository.goodHabits.filter { it.name!!.startsWith(filterString) }
                .sortedBy { it.priority }
            mutableBadHabits.value = Repository.badHabits.filter { it.name!!.startsWith(filterString) }
                .sortedBy { it.priority }
        } else {
            mutableGoodHabits.value = Repository.goodHabits.filter { it.name!!.startsWith(filterString) }
                .sortedByDescending { it.priority }
            mutableBadHabits.value = Repository.badHabits.filter { it.name!!.startsWith(filterString) }
                .sortedByDescending { it.priority }
        }
        true
    }
    var straight = true
    var filterString = ""

    private val mutableGoodHabits = MutableLiveData<List<Habit>>()
    private val mutableBadHabits = MutableLiveData<List<Habit>>()

    val goodHabits: LiveData<List<Habit>> = mutableGoodHabits
    val badHabits: LiveData<List<Habit>> = mutableBadHabits

    fun filter(string: String) {
        filterString = string
        if (straight) {
            mutableGoodHabits.value = Repository.goodHabits.filter { it.name!!.startsWith(string) }
                .sortedBy { it.priority }
            mutableBadHabits.value = Repository.badHabits.filter { it.name!!.startsWith(string) }
                .sortedBy { it.priority }
        } else {
            mutableGoodHabits.value = Repository.goodHabits.filter { it.name!!.startsWith(string) }
                .sortedByDescending { it.priority }
            mutableBadHabits.value = Repository.badHabits.filter { it.name!!.startsWith(string) }
                .sortedByDescending { it.priority }
        }
    }

    fun changeOrder(straight: Boolean) {
        this.straight = straight
        if (straight) {
            mutableGoodHabits.value = mutableGoodHabits.value!!.sortedBy { it.priority }
            mutableBadHabits.value = mutableBadHabits.value!!.sortedBy { it.priority }
        } else {
            mutableGoodHabits.value = mutableGoodHabits.value!!.sortedByDescending { it.priority }
            mutableBadHabits.value = mutableBadHabits.value!!.sortedByDescending { it.priority }
        }
    }

    init {
        mutableGoodHabits.value = Repository.goodHabits
        mutableBadHabits.value = Repository.badHabits
    }
}
