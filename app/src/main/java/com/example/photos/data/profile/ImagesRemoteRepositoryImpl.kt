package com.example.photos.data.profile

import com.example.photos.api.profile.ImagesService

class ImagesRemoteRepositoryImpl(private val imagesService: ImagesService) :
    ImagesRemoteRepository {
    override suspend fun getImages(page: Int) = imagesService.getImages(page)
}
