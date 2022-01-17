package com.jetpack

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.jetpack.adapter.MemeAdapter
import com.jetpack.entity.MemeModel
import kotlinx.android.synthetic.main.activity_meme_list.*

class MemeListActivity : AppCompatActivity() {

    lateinit var memeViewModel: MemeListViewModel

    lateinit var memeAdapter: MemeAdapter

    var memeList: ArrayList<MemeModel> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_meme_list)
        val memeDatabase = MemeDataBase.getInstance(this)
        memeViewModel = ViewModelProvider(
            this,
            MemeListViewModelFactory(memeDatabase)
        )[MemeListViewModel::class.java]
        memeViewModel.callGetAllMemes()
        setUpObserver()
        setUpAdapter()

    }

    private fun setUpAdapter() {
        memeAdapter = MemeAdapter(this, memeList)
        recycler_meme_list.adapter = memeAdapter
    }

    private fun setUpObserver() {
        memeViewModel.memeList.observe(this, Observer { list ->
            list?.let {
                memeAdapter.updateList(ArrayList(it))
            }
        })

    }
}