package com.bintang.CRUD1.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class NetworkClient {
    companion object{
        fun getClient(): OkHttpClient {
            val logging = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

            val client = OkHttpClient.Builder()
                    .addInterceptor(logging)
                    .build()
            return client
        }

        fun getRetrofit(): Retrofit {
            val retrofit = Retrofit.Builder()
                    .baseUrl("http://192.168.10.13:8000/api/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(getClient())
                    .build()

            return retrofit
        }

        fun service(): RestApi {
//            var service = retrofit.create<RestApi>(RestApi::class.java)

            return getRetrofit().create(RestApi::class.java)
        }

    }

}