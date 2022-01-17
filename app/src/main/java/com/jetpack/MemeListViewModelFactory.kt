package com.jetpack

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class MemeListViewModelFactory(private val memeDataBase: MemeDataBase) : ViewModelProvider.Factory {


    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MemeListViewModel::class.java)) {
            return MemeListViewModel(this.memeDataBase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}