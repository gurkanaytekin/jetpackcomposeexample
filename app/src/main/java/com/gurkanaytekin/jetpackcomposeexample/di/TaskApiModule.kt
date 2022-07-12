package com.gurkanaytekin.jetpackcomposeexample.di

import com.gurkanaytekin.jetpackcomposeexample.data.api.ApiConstants
import com.gurkanaytekin.jetpackcomposeexample.data.api.TodoApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object TaskApiModule {
    @Provides
    @Singleton
    fun provideApi(builder: Retrofit.Builder): TodoApi {
        return builder
            .build()
            .create(TodoApi::class.java)
    }

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit.Builder {
        return Retrofit.Builder()
            .baseUrl(ApiConstants.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
    }
}