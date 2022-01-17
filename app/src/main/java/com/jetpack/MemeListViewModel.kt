package com.jetpack

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jetpack.entity.MemeModel
import kotlinx.coroutines.launch

class MemeListViewModel(val memeDataBase: MemeDataBase) : ViewModel() {


    var memeList: MutableLiveData<List<MemeModel>> = MutableLiveData()

    fun callGetAllMemes() {
        var resultList: List<MemeModel> = ArrayList()
        viewModelScope.launch {

            resultList = memeDataBase.memeDao().getAllMemes()
            memeList.value = resultList
        }
    }
}