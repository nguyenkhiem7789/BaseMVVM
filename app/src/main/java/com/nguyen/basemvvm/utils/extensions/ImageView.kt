package com.nguyen.basemvvm.utils.extensions

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.nguyen.basemvvm.R


fun ImageView.loadImageFromUrl(url: String) {
    Glide
        .with(context!!)
        .load(url)
        .centerCrop()
        .placeholder(R.drawable.empty)
        .into(this)
}

fun ImageView.loadPlaceHolder() {
    Glide
        .with(context!!)
        .load(R.drawable.empty)
        .centerCrop()
        .placeholder(R.drawable.empty)
        .into(this)
}