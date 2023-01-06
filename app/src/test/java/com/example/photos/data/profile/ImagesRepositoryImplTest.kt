package com.example.photos.data.profile

import com.example.photos.model.profile.Image
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.runBlocking

import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class ImagesRepositoryImplTest {

    private val image1 = Image(
        1,
        1,
        "https://via.placeholder.com/150/92c952",
        "accusamus beatae ad facilis cum similique qui sunt",
        "https://via.placeholder.com/600/92c952",
    )

    private val image2 = Image(
        1,
        2,
        "https://via.placeholder.com/150/771796",
        "reprehenderit est deserunt velit ipsam",
        "https://via.placeholder.com/600/771796",
    )

    @Mock
    private lateinit var imagesRemoteRepository: ImagesRemoteRepository

    private lateinit var imagesRepository: ImagesRepository

    @Before
    fun setUp() {
        imagesRepository = ImagesRepositoryImpl(imagesRemoteRepository)
    }

    @Test
    fun gettingNextPageForFirstTime_callsFirstPageOfImages() {
        runBlocking {
            `when`(imagesRemoteRepository.getImages(1))
                .thenReturn(listOf(image1.copy(), image2.copy()))
            imagesRepository.getNextImagesPage()

            verify(imagesRemoteRepository).getImages(1)
        }
    }

    @Test
    fun gettingNextPageForFirstTime_returnsFirstPageOfImages() {
        runBlocking {
            `when`(imagesRemoteRepository.getImages(1))
                .thenReturn(listOf(image1.copy(), image2.copy()))

            val result = imagesRepository.getNextImagesPage()

            assertThat(result).containsExactly(image1.copy(), image2.copy())
        }
    }

    @Test
    fun gettingNextPageForFifthTime_callsFifthPageOfImages() {
        runBlocking {
            `when`(imagesRemoteRepository.getImages(1))
                .thenReturn(listOf(image1.copy(), image2.copy()))
            `when`(imagesRemoteRepository.getImages(2))
                .thenReturn(listOf(image1.copy(), image2.copy()))
            `when`(imagesRemoteRepository.getImages(3))
                .thenReturn(listOf(image1.copy(), image2.copy()))
            `when`(imagesRemoteRepository.getImages(4))
                .thenReturn(listOf(image1.copy(), image2.copy()))
            `when`(imagesRemoteRepository.getImages(5)).thenReturn(
                listOf(image1.copy(id = 8), image2.copy(id = 9))
            )
            imagesRepository.getNextImagesPage()
            imagesRepository.getNextImagesPage()
            imagesRepository.getNextImagesPage()
            imagesRepository.getNextImagesPage()
            imagesRepository.getNextImagesPage()

            verify(imagesRemoteRepository).getImages(5)
        }
    }

    @Test
    fun gettingNextPageForFifthTime_returnsFifthPageOfImages() {
        runBlocking {
            `when`(imagesRemoteRepository.getImages(1))
                .thenReturn(listOf(image1.copy(), image2.copy()))
            `when`(imagesRemoteRepository.getImages(2))
                .thenReturn(listOf(image1.copy(), image2.copy()))
            `when`(imagesRemoteRepository.getImages(3))
                .thenReturn(listOf(image1.copy(), image2.copy()))
            `when`(imagesRemoteRepository.getImages(4))
                .thenReturn(listOf(image1.copy(), image2.copy()))
            `when`(imagesRemoteRepository.getImages(5)).thenReturn(
                listOf(image1.copy(id = 8), image2.copy(id = 9))
            )

            imagesRepository.getNextImagesPage()
            imagesRepository.getNextImagesPage()
            imagesRepository.getNextImagesPage()
            imagesRepository.getNextImagesPage()
            val result = imagesRepository.getNextImagesPage()

            assertThat(result).containsExactly(image1.copy(id = 8), image2.copy(id = 9))
        }
    }

    @Test
    fun checkingNextPageAvailabilityForFirstTime_returnsTrue() {
        val nextPageAvailable = imagesRepository.isNextPageAvailable()
        assertThat(nextPageAvailable).isTrue()
    }

    @Test
    fun checkingNextPageAvailabilityForSecondTime_returnsTrueIfListNonEmptyFirstTime() {
        runBlocking {
            `when`(imagesRemoteRepository.getImages(1))
                .thenReturn(listOf(image1.copy(), image2.copy()))
            imagesRepository.getNextImagesPage()
            val nextPageAvailable = imagesRepository.isNextPageAvailable()
            assertThat(nextPageAvailable).isTrue()
        }
    }

    @Test
    fun checkingNextPageAvailabilityForSecondTime_returnsFalseIfListEmptyFirstTime() {
        runBlocking {
            `when`(imagesRemoteRepository.getImages(1)).thenReturn(emptyList())
            imagesRepository.getNextImagesPage()
            val nextPageAvailable = imagesRepository.isNextPageAvailable()
            assertThat(nextPageAvailable).isFalse()
        }
    }

    @Test(expected = TestException::class)
    fun callingNextPageForFirstTime_forwardsException() {
        runBlocking {
            `when`(imagesRemoteRepository.getImages(1)).thenThrow(TestException())
            imagesRepository.getNextImagesPage()
        }
    }
}
