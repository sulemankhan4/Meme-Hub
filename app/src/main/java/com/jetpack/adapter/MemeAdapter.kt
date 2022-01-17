package com.jetpack.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jetpack.R
import com.jetpack.base.BaseViewHolder
import com.jetpack.entity.MemeModel
import com.jetpack.utils.ImageUtils
import kotlinx.android.synthetic.main.item_meme.view.*

class MemeAdapter(
    val context: Context,
    var list: ArrayList<MemeModel>
) : RecyclerView.Adapter<BaseViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        val view = LayoutInflater.from(context)
            .inflate(R.layout.item_meme, parent, false)
        return BaseViewHolder(view)
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        val data = list[position]
        holder.itemView?.apply {
            ImageUtils.loadImage(data.url, iv_meme)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun updateList(list: ArrayList<MemeModel>) {
        this.list = list
        notifyDataSetChanged()
    }
}