package com.example.movieapp.presentation.util.extension

import android.widget.ImageView
import com.bumptech.glide.Glide

fun ImageView.loadFromWeb(url: String) {
    Glide.with(this)
        .load(url)
        .into(this)
}