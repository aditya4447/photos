package com.example.photos.data.profile

import com.example.photos.model.profile.Image

interface ImagesRepository {
    suspend fun getNextImagesPage(): List<Image>
    fun isNextPageAvailable(): Boolean
}
