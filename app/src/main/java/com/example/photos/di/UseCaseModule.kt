package com.example.photos.di

import com.example.photos.usecase.profile.ImagesPageAvailabilityUseCase
import com.example.photos.usecase.profile.ImagesPageAvailabilityUseCaseImpl
import com.example.photos.usecase.profile.ImagesPageUseCase
import com.example.photos.usecase.profile.ImagesPageUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class UseCaseModule {

    @Binds
    abstract fun bindImagesPageAvailabilityUseCase(
        imagesPageAvailabilityUseCaseImpl: ImagesPageAvailabilityUseCaseImpl,
    ): ImagesPageAvailabilityUseCase

    @Binds
    abstract fun bindImagesPageUseCase(
        imagesPageUseCaseImpl: ImagesPageUseCaseImpl,
    ): ImagesPageUseCase
}
