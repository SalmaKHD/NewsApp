package com.example.newsapp.di

import com.example.newsapp.data.remote.NewsRemoteDataSource
import com.example.newsapp.data.remote.NewsRemoteDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class BinderModule {
    @Binds
    abstract fun bindNewsRemoteDataSource(newsRemoteDataSourceImpl: NewsRemoteDataSourceImpl): NewsRemoteDataSource
}