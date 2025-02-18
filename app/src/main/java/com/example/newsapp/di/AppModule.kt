package com.example.newsapp.di

import com.example.newsapp.data.remote.NewsApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    // retrofit
    @Provides
    @Singleton
    fun providesRetrofit(): Retrofit {
        val logging = HttpLoggingInterceptor().apply {
//            if (BuildConfig.DEBUG)
            setLevel(HttpLoggingInterceptor.Level.BODY)
        }

        val okHttp = OkHttpClient.Builder()
            .connectTimeout(10, TimeUnit.SECONDS)
            .writeTimeout(120, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .addInterceptor(logging)

        return Retrofit.Builder()
            .baseUrl("https://newsapi.org/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttp.build())
            .build()
    }

    @Provides
    fun providesApi(
        retrofit: Retrofit
    ): NewsApi = retrofit.create(NewsApi::class.java)
}
