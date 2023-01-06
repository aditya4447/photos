package com.example.photos.usecase.profile

import com.example.photos.model.profile.Image

interface ImagesPageUseCase {
    suspend fun getNextImagesPage(): List<Image>
}
