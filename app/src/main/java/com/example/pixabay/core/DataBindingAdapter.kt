package com.example.pixabay.core

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.example.pixabay.R

object DataBindingAdapter {

    @JvmStatic
    @BindingAdapter("imageUrl")
    fun setImage(imageView: ImageView, url: String?) {
        Glide.with(imageView.context).load(url).centerCrop()
            .placeholder(R.drawable.placeholder)
            .into(imageView)
    }
}