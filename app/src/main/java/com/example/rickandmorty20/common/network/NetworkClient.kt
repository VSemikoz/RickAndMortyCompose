package com.example.rickandmorty20.common.network

import com.example.rickandmorty20.common.CustomDateAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient.Builder
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.Date


object NetworkClient {
    fun getClient(): Retrofit {
        val moshi: Moshi = Moshi.Builder().add(KotlinJsonAdapterFactory())
            .add(Date::class.java, CustomDateAdapter()).build()
        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)

        val okHttpClient = Builder().addInterceptor(interceptor).build()

        val client: Retrofit by lazy {
            Retrofit.Builder().addConverterFactory(MoshiConverterFactory.create(moshi))
                .baseUrl("https://rickandmortyapi.com/api/").client(okHttpClient).build();
        }

        return client
    }


}
