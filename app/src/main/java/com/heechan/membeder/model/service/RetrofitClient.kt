package com.heechan.membeder.model.service

import com.heechan.membeder.BuildConfig
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    class AppInterceptor(val token: String) : Interceptor {
        override fun intercept(chain: Interceptor.Chain): Response {
            val newRequest = chain.request().newBuilder()
                .addHeader("Authentication", token)
                .build()

            return chain.proceed(newRequest)
        }
    }

    private val loggingInterceptor = HttpLoggingInterceptor()
        .setLevel(HttpLoggingInterceptor.Level.BODY)

    private val clientBuilder = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)

    private val retrofitBuilder: Retrofit.Builder = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BuildConfig.API_BASE_URL)

    fun getRetrofit(): Retrofit {
        retrofitBuilder.client(clientBuilder.build())
        return retrofitBuilder.build()
    }

    fun getRetrofit(token: String): Retrofit {
        clientBuilder.addInterceptor(AppInterceptor(token))
        retrofitBuilder.client(clientBuilder.build())

        return retrofitBuilder.build()
    }
}