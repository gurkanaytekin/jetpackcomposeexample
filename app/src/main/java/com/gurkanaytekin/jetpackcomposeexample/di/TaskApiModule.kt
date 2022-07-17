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
import okhttp3.Request
import okio.Buffer
import okio.IOException
import okio.Timeout
import org.json.JSONObject
import org.json.JSONTokener
import retrofit2.*
import retrofit2.converter.moshi.MoshiConverterFactory
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type
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

class ResultCall<T>(val delegate: Call<T>) :
    Call<Result<T>> {

    override fun enqueue(callback: Callback<Result<T>>) {
        delegate.enqueue(
            object : Callback<T> {
                override fun onResponse(call: Call<T>, response: Response<T>) {
                    if (response.isSuccessful) {
                        callback.onResponse(
                            this@ResultCall,
                            Response.success(
                                response.code(),
                                Result.success(response.body()!!)
                            )
                        )
                    } else {
                        callback.onResponse(
                            this@ResultCall,
                            Response.success(
                                Result.failure(
                                    HttpException(response)
                                )
                            )
                        )
                    }
                }

                override fun onFailure(call: Call<T>, t: Throwable) {
                    val errorMessage = when (t) {
                        is IOException -> "No internet connection"
                        is HttpException -> "Something went wrong!"
                        else -> t.localizedMessage
                    }
                    callback.onResponse(
                        this@ResultCall,
                        Response.success(Result.failure(RuntimeException(errorMessage, t)))
                    )
                }
            }
        )
    }

    override fun isExecuted(): Boolean {
        return delegate.isExecuted
    }

    override fun execute(): Response<Result<T>> {
        return Response.success(Result.success(delegate.execute().body()!!))
    }

    override fun cancel() {
        delegate.cancel()
    }

    override fun isCanceled(): Boolean {
        return delegate.isCanceled
    }

    override fun clone(): Call<Result<T>> {
        return ResultCall(delegate.clone())
    }

    override fun request(): Request {
        return delegate.request()
    }

    override fun timeout(): Timeout {
        return delegate.timeout()
    }
}


class ResultCallAdapterFactory : CallAdapter.Factory() {

    override fun get(
        returnType: Type,
        annotations: Array<out Annotation>,
        retrofit: Retrofit
    ): CallAdapter<*, *>? {
        if (getRawType(returnType) != Call::class.java || returnType !is ParameterizedType) {
            return null
        }
        val upperBound = getParameterUpperBound(0, returnType)

        return if (upperBound is ParameterizedType && upperBound.rawType == Result::class.java) {
            object : CallAdapter<Any, Call<Result<*>>> {
                override fun responseType(): Type = getParameterUpperBound(0, upperBound)

                override fun adapt(call: Call<Any>): Call<Result<*>> =
                    ResultCall(call) as Call<Result<*>>
            }
        } else {
            null
        }
    }
}

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
            .addCallAdapterFactory(ResultCallAdapterFactory())
    }
}