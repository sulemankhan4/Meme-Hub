package com.jetpack

import androidx.lifecycle.*
import com.jetpack.entity.MemeModel
import com.jetpack.utils.ImageUtils
import kotlinx.coroutines.launch

class MemeListViewModel(val memeDataBase: MemeDataBase) : ViewModel() {


    var memesFromLocalDb: MutableLiveData<List<MemeModel>> = MutableLiveData()
    var memesFromMemeGen: MutableLiveData<List<MemeModel>> = MutableLiveData()

    var mediatorLiveData = MediatorLiveData<List<MemeModel>>()

    init {
        callGetAllMemes()
        callGetMemeFromMemeGen()
        mediatorLiveData.addSource(memesFromLocalDb, Observer {
            combineData(it ?: ArrayList(), memesFromMemeGen.value)
        })
        mediatorLiveData.addSource(memesFromMemeGen, Observer {
            combineData(it ?: ArrayList(), memesFromLocalDb.value)

        })
    }

    private fun combineData(memesFromLocalDb: List<MemeModel>, memesFromMemeGen: List<MemeModel>?) {
        /**
         * Showing memes from 2 different data source i.e local db and remote api.
         */
        val list: ArrayList<MemeModel> = ArrayList()
        list.addAll(memesFromMemeGen ?: ArrayList())
        list.addAll(memesFromLocalDb)
        mediatorLiveData.value = list
    }


    fun callGetAllMemes() {
        viewModelScope.launch {
            memesFromLocalDb.value = MemeRepository.getAllMemes(memeDataBase)
        }
    }

    fun callGetMemeFromMemeGen() {
        viewModelScope.launch {
            memesFromMemeGen.value = MemeRepository.callGetMemeFromMemeGen()
        }
    }

}