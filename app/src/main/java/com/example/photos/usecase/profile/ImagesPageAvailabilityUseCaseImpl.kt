package com.example.photos.usecase.profile

import com.example.photos.data.profile.ImagesRepository
import javax.inject.Inject

class ImagesPageAvailabilityUseCaseImpl @Inject constructor(
    private val imagesRepository: ImagesRepository,
): ImagesPageAvailabilityUseCase {
    override fun isNextPageAvailable() = imagesRepository.isNextPageAvailable()
}
