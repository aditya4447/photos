package com.example.photos.data.profile

import com.example.photos.model.profile.Image

interface ImagesRemoteRepository {
    suspend fun getImages(page: Int): List<Image>
}
