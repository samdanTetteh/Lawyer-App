package com.ijikod.lawyer_app.ui

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("imageUrl")
fun loadImage(imageView: ImageView, imgUrl: String){
    Glide.with(imageView.context).load(imgUrl).into(imageView)
}