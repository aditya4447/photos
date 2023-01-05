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
fun loadImage(view: ImageView, text: String?) {
    TooltipCompat.setTooltipText(view, text)
}
