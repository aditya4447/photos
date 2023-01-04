package com.example.photos.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.photos.R
import com.example.photos.databinding.ActivityMainBinding
import com.example.photos.util.bind

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind<ActivityMainBinding>(R.layout.activity_main)
    }
}
