package com.example.photos.util

import android.app.Activity
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding


fun <T: ViewDataBinding> Activity.bind(@LayoutRes layout: Int): T {
    return DataBindingUtil.setContentView(this, layout)
}
