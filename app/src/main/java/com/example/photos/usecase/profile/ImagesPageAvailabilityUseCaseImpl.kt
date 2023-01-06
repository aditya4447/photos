package com.example.photos.usecase.profile

import com.example.photos.data.profile.ImagesRepository

class ImagesPageAvailabilityUseCaseImpl(private val imagesRepository: ImagesRepository):
    ImagesPageAvailabilityUseCase {

    override fun isNextPageAvailable() = imagesRepository.isNextPageAvailable()
}
