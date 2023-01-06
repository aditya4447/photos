package com.example.photos.usecase.profile

import com.example.photos.data.profile.ImagesRepository
import com.google.common.truth.Truth
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class ImagesPageAvailabilityUseCaseImplTest {

    @Mock
    private lateinit var imagesRepository: ImagesRepository

    private lateinit var imagesPageAvailabilityUseCase: ImagesPageAvailabilityUseCase


    @Before
    fun setUp() {
        imagesPageAvailabilityUseCase = ImagesPageAvailabilityUseCaseImpl(imagesRepository)
    }

    @Test
    fun checkingNextPageAvailability_returnsTrueIfRepoReturnsTrue() {
        `when`(imagesRepository.isNextPageAvailable()).thenReturn(true)
        val nextPageAvailable = imagesPageAvailabilityUseCase.isNextPageAvailable()
        Truth.assertThat(nextPageAvailable).isTrue()
    }

    @Test
    fun checkingNextPageAvailability_returnsFalseIfRepoReturnsFalse() {
        `when`(imagesRepository.isNextPageAvailable()).thenReturn(false)
        val nextPageAvailable = imagesPageAvailabilityUseCase.isNextPageAvailable()
        Truth.assertThat(nextPageAvailable).isFalse()
    }

}
