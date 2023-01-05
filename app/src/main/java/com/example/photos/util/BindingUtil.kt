package com.example.photos.util

import android.app.Activity
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment


fun <T: ViewDataBinding> Activity.bind(@LayoutRes layout: Int): T {
    return DataBindingUtil.setContentView(this, layout)
}

fun <T: ViewDataBinding> Fragment.bind(@LayoutRes layout: Int, parent: ViewGroup?): T {
    return DataBindingUtil.inflate(this.layoutInflater, layout, parent, false)
}
