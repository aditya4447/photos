package com.example.photos.util

import android.view.View

fun goneUnless(condition: Boolean) = if (condition) View.VISIBLE else View.GONE

fun visibleUnless(condition: Boolean) = if (condition) View.GONE else View.VISIBLE
