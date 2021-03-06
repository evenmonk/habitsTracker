package com.example.habitsTracker.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ListViewModel: ViewModel() {

    private val mutableStraightOrder = MutableLiveData<Boolean>()
    val straightOrder: LiveData<Boolean> = mutableStraightOrder

    private val mutableFilterString = MutableLiveData<String>()
    val filterString: LiveData<String> = mutableFilterString

    fun filter(string: String?) {
        mutableFilterString.value = string
    }

    fun changeOrder(straight: Boolean?) {
        mutableStraightOrder.value = straight
    }

    fun getHabits(): LiveData<List<Habit>> {
        return Repository.get().getHabitsLiveData()
    }

    fun deleteHabit(habit: Habit) {
        viewModelScope.launch(Dispatchers.IO) {
            Repository.get().deleteHabit(habit)
        }
    }

    init {
        viewModelScope.launch(Dispatchers.IO) {
            Repository.get().loadHabits()
        }
    }
}
