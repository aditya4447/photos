package com.example.photos.di

import com.example.photos.data.profile.ImagesRemoteRepository
import com.example.photos.data.profile.ImagesRemoteRepositoryImpl
import com.example.photos.data.profile.ImagesRepository
import com.example.photos.data.profile.ImagesRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class DataModule {

    @Binds
    abstract fun bindImagesRepository(imagesRepositoryImpl: ImagesRepositoryImpl): ImagesRepository

    @Binds
    abstract fun bindImagesRemoteRepository(
        imagesRemoteRepositoryImpl: ImagesRemoteRepositoryImpl,
    ): ImagesRemoteRepository
}
