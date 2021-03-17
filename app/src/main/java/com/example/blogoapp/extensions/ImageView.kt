package com.example.blogoapp.extensions

import android.widget.ImageView
import com.bumptech.glide.Glide

fun ImageView.loadImage(url:String,crop:Boolean=false){
    if (crop)
    Glide.with(this).load(url).circleCrop().into(this)
    else
        Glide.with(this).load(url).into(this)

}