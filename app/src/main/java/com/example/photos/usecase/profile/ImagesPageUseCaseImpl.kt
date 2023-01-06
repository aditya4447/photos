package com.example.photos.usecase.profile

import com.example.photos.data.profile.ImagesRepository

class ImagesPageUseCaseImpl(private val imagesRepository: ImagesRepository) : ImagesPageUseCase {
    override suspend fun getNextImagesPage() = imagesRepository.getNextImagesPage()
}
