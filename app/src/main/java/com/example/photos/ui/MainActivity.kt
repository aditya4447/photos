package com.example.photos.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.photos.R
import com.example.photos.databinding.ActivityMainBinding
import com.example.photos.ui.profile.ProfileFragment
import com.example.photos.util.bind

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = bind(R.layout.activity_main)
        setSupportActionBar(binding.toolbar)

        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .replace(binding.fragmentMain.id, ProfileFragment.newInstance())
                .commit()
        }
    }
}
