package com.example.photos.data.profile

import com.example.photos.api.profile.ImagesService
import com.example.photos.model.profile.Image
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class ImagesRemoteRepositoryImplTest {

    @Mock
    private lateinit var imagesService: ImagesService

    private lateinit var imagesRemoteRepository: ImagesRemoteRepository

    @Before
    fun setUp() {
        imagesRemoteRepository = ImagesRemoteRepositoryImpl(imagesService)
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

            `when`(imagesService.getImages(1)).thenReturn(listOf(
                image1.copy(),
                image2.copy(),
            ))

            val images = imagesRemoteRepository.getImages(1)

            assertThat(images).containsExactly(image1, image2)

        }
    }

    @Test
    fun gettingImages_returnsEmptyListIfResponseEmpty() {

        runBlocking {

            `when`(imagesService.getImages(1)).thenReturn(listOf())

            val images = imagesRemoteRepository.getImages(1)

            assertThat(images).isEmpty()

        }
    }

    @Test(expected = TestException::class)
    fun gettingImages_throwsExceptionIfServiceThrowsException() {

        runBlocking {

            `when`(imagesService.getImages(1)).thenThrow(TestException())

            imagesRemoteRepository.getImages(1)

        }
    }
}
