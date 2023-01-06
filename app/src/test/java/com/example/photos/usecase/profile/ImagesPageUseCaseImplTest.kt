package com.example.photos.usecase.profile

import com.example.photos.data.profile.ImagesRepository
import com.example.photos.data.profile.TestException
import com.example.photos.model.profile.Image
import com.google.common.truth.Truth
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class ImagesPageUseCaseImplTest {

    @Mock
    private lateinit var imagesRepository: ImagesRepository

    private lateinit var imagesPageUseCase: ImagesPageUseCase

    @Before
    fun setUp() {
        imagesPageUseCase = ImagesPageUseCaseImpl(imagesRepository)
    }


    @Test
    fun gettingImages_returnsExactImageList() {

        runBlocking {

            val image1 = Image(
                1,
                1,
                "https://via.placeholder.com/150/92c952",
                "accusamus beatae ad facilis cum similique qui sunt",
                "https://via.placeholder.com/600/92c952",
            )

            val image2 = Image(
                1,
                2,
                "https://via.placeholder.com/150/771796",
                "reprehenderit est deserunt velit ipsam",
                "https://via.placeholder.com/600/771796",
            )

            `when`(imagesRepository.getNextImagesPage()).thenReturn(listOf(
                image1.copy(),
                image2.copy(),
            ))

            val images = imagesPageUseCase.getNextImagesPage()

            Truth.assertThat(images).containsExactly(image1, image2)

        }
    }

    @Test
    fun gettingImages_returnsEmptyListIfResponseEmpty() {

        runBlocking {

            `when`(imagesRepository.getNextImagesPage()).thenReturn(listOf())

            val images = imagesPageUseCase.getNextImagesPage()

            Truth.assertThat(images).isEmpty()

        }
    }

    @Test(expected = TestException::class)
    fun gettingImages_throwsExceptionIfServiceThrowsException() {

        runBlocking {

            `when`(imagesRepository.getNextImagesPage()).thenThrow(TestException())

            imagesPageUseCase.getNextImagesPage()

        }
    }
}
