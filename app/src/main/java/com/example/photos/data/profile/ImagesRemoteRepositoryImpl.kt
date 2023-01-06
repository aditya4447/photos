package com.example.photos.data.profile

import com.example.photos.api.profile.ImagesService
import javax.inject.Inject

class ImagesRemoteRepositoryImpl @Inject constructor(private val imagesService: ImagesService) :
    ImagesRemoteRepository {
    override suspend fun getImages(page: Int) = imagesService.getImages(page)
}
