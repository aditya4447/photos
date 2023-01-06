package com.example.photos.util

import android.app.Activity
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.annotation.LayoutRes
import androidx.appcompat.widget.TooltipCompat
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.load.model.LazyHeaders
import com.example.photos.model.profile.Image


fun <T: ViewDataBinding> Activity.bind(@LayoutRes layout: Int): T {
    return DataBindingUtil.setContentView(this, layout)
}

fun <T: ViewDataBinding> Fragment.bind(@LayoutRes layout: Int, parent: ViewGroup?): T {
    return DataBindingUtil.inflate(this.layoutInflater, layout, parent, false)
}

fun <T: ViewDataBinding> bind(@LayoutRes layout: Int, parent: ViewGroup?): T {
    return DataBindingUtil.inflate(LayoutInflater.from(parent?.context), layout, parent, false)
}

@BindingAdapter("toolTipTextCompat")
fun setTooltipText(view: ImageView, text: String?) {
    TooltipCompat.setTooltipText(view, text)
}

@BindingAdapter("imageUrl")
fun loadImage(view: ImageView, image: Image?) {
    if (image != null) {
        Glide
            .with(view)
            .load(GlideUrl(image.url, LazyHeaders.Builder()
                .setHeader(
                    "User-Agent",
                    "Mozilla/5.0 (Android 4.4; Mobile; rv:41.0) Gecko/41.0 Firefox/41.0"
                )
                .build()))
            .thumbnail(Glide.with(view).load(image.thumbnailUrl))
            .into(view)
    }
}
