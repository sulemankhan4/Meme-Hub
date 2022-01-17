package com.jetpack.utils

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.jetpack.R

object ImageUtils {

    fun preloadImage(context: Context, url: String) {
        //Preloading Images to reduce download time.
        Glide.with(context)
            .load(url)
            .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
            .preload()
    }

    fun loadImage(url: String?, imgView: ImageView) {
        Glide.with(imgView.context)
            .setDefaultRequestOptions(
                RequestOptions().placeholder(R.drawable.loading_bg).dontAnimate().fitCenter()
                    .error(R.drawable.loading_bg)
            )
            .load(url)
            .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
            .into(imgView)

    }
}