package com.example.photos.data.profile

import com.example.photos.model.profile.Image

class ImagesRepositoryImpl(private val remoteRepository: ImagesRemoteRepository) : ImagesRepository {

    private var page = 0
    private var nextPageAvailable = true

    override suspend fun getNextImagesPage(): List<Image> =
        remoteRepository.getImages(page + 1).also {
            nextPageAvailable = it.isNotEmpty()
            page++
        }

    override fun isNextPageAvailable() = nextPageAvailable
}
