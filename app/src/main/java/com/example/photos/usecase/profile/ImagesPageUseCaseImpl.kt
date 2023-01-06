package com.example.photos.usecase.profile

import com.example.photos.data.profile.ImagesRepository
import javax.inject.Inject

class ImagesPageUseCaseImpl @Inject constructor(
    private val imagesRepository: ImagesRepository,
) : ImagesPageUseCase {
    override suspend fun getNextImagesPage() = imagesRepository.getNextImagesPage()
}
