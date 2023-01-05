package com.example.photos.api.profile

import com.example.photos.model.profile.Image
import retrofit2.http.GET
import retrofit2.http.Query

const val IMAGES_PER_PAGE = 10

interface ImagesService {

    @GET("/photos?_limit=$IMAGES_PER_PAGE")
    suspend fun getImages(@Query("_page") page: Int): List<Image>
}
