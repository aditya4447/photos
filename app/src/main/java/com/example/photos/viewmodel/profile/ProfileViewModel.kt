package com.example.photos.viewmodel.profile

import android.graphics.Color
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ProfileViewModel: ViewModel() {
    private val _username = MutableLiveData("john.doe")
    val username: LiveData<String> = _username

    val dashboardEnabled = MutableLiveData(true)

    private val _followers = MutableLiveData("2.3K")
    val followers: LiveData<String> = _followers
    private val _artworks = MutableLiveData("50")
    val artworks: LiveData<String> = _artworks
    private val _exhibitions = MutableLiveData("21")
    val exhibitions: LiveData<String> = _exhibitions

    private val _likes = MutableLiveData("120")
    val likes: LiveData<String> = _likes
    private val _comments = MutableLiveData("43K")
    val comments: LiveData<String> = _comments
    private val _shares = MutableLiveData("2.3K")
    val shares: LiveData<String> = _shares


    private val _palette1 = MutableLiveData(Color.parseColor("#571845"))
    val palette1: LiveData<Int> = _palette1
    private val _palette2 = MutableLiveData(Color.parseColor("#900C3E"))
    val palette2: LiveData<Int> = _palette2
    private val _palette3 = MutableLiveData(Color.parseColor("#C70039"))
    val palette3: LiveData<Int> = _palette3
    private val _palette4 = MutableLiveData(Color.parseColor("#FF5733"))
    val palette4: LiveData<Int> = _palette4
    private val _palette5 = MutableLiveData(Color.parseColor("#FFC300"))
    val palette5: LiveData<Int> = _palette5

}
