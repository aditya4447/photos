package com.example.photos.di

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.example.photos.BuildConfig
import com.example.photos.api.profile.ImagesService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ApiModule {
    @Provides
    @Singleton
    fun provideRetrofitClient(@ApplicationContext context: Context): Retrofit =
        Retrofit.Builder()
            .client(
                OkHttpClient.Builder()
                    .addInterceptor(ChuckerInterceptor.Builder(context).build())
                    .build()
            )
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BuildConfig.API_URL)
            .build()

    @Singleton
    @Provides
    fun provideImagesService(retrofit: Retrofit): ImagesService =
        retrofit.create(ImagesService::class.java)
}
