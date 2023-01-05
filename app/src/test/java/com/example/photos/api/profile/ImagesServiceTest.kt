package com.example.photos.api.profile

import com.example.photos.model.profile.Image
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer

import org.junit.After
import org.junit.Before
import org.junit.Test
import retrofit2.HttpException
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ImagesServiceTest {

    private lateinit var mockWebServer: MockWebServer

    private lateinit var imagesService: ImagesService

    @Before
    fun setUp() {
        mockWebServer = MockWebServer()
        val url = mockWebServer.url("/photos/")
        imagesService = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(url)
            .build()
            .create(ImagesService::class.java)
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }

    @Test
    fun callingGetImages_ReturnsImageList() {
        runBlocking {
            mockWebServer.enqueue(MockResponse()
                .setResponseCode(200)
                .setHeader("Content-Type", "application/json")
                .setBody(TEST_RESPONSE_JSON))

            val response = imagesService.getImages(1)

            assertThat(response).containsExactly(
                Image(
                    1,
                    1,
                    "https://via.placeholder.com/150/92c952",
                    "accusamus beatae ad facilis cum similique qui sunt",
                    "https://via.placeholder.com/600/92c952",
                ),
                Image(
                    1,
                    2,
                    "https://via.placeholder.com/150/771796",
                    "reprehenderit est deserunt velit ipsam",
                    "https://via.placeholder.com/600/771796",
                ),
            )
        }
    }

    @Test
    fun gettingEmptyJSONArray_returnsEmptyResponseList() {
        runBlocking {

            mockWebServer.enqueue(MockResponse()
                .setResponseCode(200)
                .setHeader("Content-Type", "application/json")
                .setBody("[]"))

            val response = imagesService.getImages(1)

            assertThat(response).hasSize(0)
        }
    }

    @Test(expected = HttpException::class)
    fun gettingErrorResponseCode_throwsException() {
        runBlocking {

            mockWebServer.enqueue(MockResponse()
                .setResponseCode(404)
                .setBody(""))

            imagesService.getImages(1)
        }
    }
}

private const val TEST_RESPONSE_JSON =
    """[{
    "albumId": 1,
    "id": 1,
    "title": "accusamus beatae ad facilis cum similique qui sunt",
    "url": "https://via.placeholder.com/600/92c952",
    "thumbnailUrl": "https://via.placeholder.com/150/92c952"
  },
  {
    "albumId": 1,
    "id": 2,
    "title": "reprehenderit est deserunt velit ipsam",
    "url": "https://via.placeholder.com/600/771796",
    "thumbnailUrl": "https://via.placeholder.com/150/771796"
  }
]"""
