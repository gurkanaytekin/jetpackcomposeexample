package com.gurkanaytekin.jetpackcomposeexample.di

import android.util.Log
import com.gurkanaytekin.jetpackcomposeexample.data.api.ApiConstants
import com.gurkanaytekin.jetpackcomposeexample.data.api.TodoApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okio.Buffer
import org.json.JSONObject
import org.json.JSONTokener
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton


class OAuthInterceptor(): Interceptor {
    private var token: String = ""
    override fun intercept(chain: Interceptor.Chain): okhttp3.Response {
        var request = chain.request()
        request = request.newBuilder().header("Authorization", "Bearer ${this.token}").build()
        var response = chain.proceed(request)
        val body = response.peekBody(4096).string()

        if(body.contains("token")) {
            val token = JSONObject(body).getString("token")
            this.token = token
        }
        return response
    }
}

/*class OAuthInterceptorResponse(private val chain: Interceptor.Chain): Interceptor {
    private var token: String = ""
    override fun intercept(chain: Interceptor.Chain): okhttp3.Response {
        var request = chain.request()
        request = request.newBuilder().header("Authorization", "$tokenType $token").build()
        return chain.proceed(request)
    }
}*/

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
        val client = OkHttpClient.Builder()
            .addInterceptor(OAuthInterceptor())
            .build()

        return Retrofit.Builder()
            .baseUrl(ApiConstants.BASE_URL)
            .client(client)
            .addConverterFactory(MoshiConverterFactory.create())
    }
}