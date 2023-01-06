package com.example.photos.util

import android.content.Context
import com.bumptech.glide.Glide
import com.bumptech.glide.Registry
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.integration.okhttp3.OkHttpUrlLoader
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.module.AppGlideModule
import com.chuckerteam.chucker.api.ChuckerInterceptor
import okhttp3.OkHttpClient
import java.io.InputStream

/**
 * Added this module to log image loading urls using chucker
 */
@GlideModule
class PhotosGlideModule : AppGlideModule() {
  override fun registerComponents(context: Context, glide: Glide, registry: Registry) {
    super.registerComponents(context, glide, registry)
    registry.replace(GlideUrl::class.java, InputStream::class.java, OkHttpUrlLoader
      .Factory(OkHttpClient.Builder()
        .addInterceptor(ChuckerInterceptor.Builder(context).build()).build()))
  }
}
