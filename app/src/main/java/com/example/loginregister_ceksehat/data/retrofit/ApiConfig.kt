package com.example.loginregister_ceksehat.data.retrofit

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiConfig {
    companion object {
        private const val BASE_URL_ARTICLE = "https://article-api-258958241249.us-central1.run.app/"
        private const val BASE_URL_CHATBOT = "https://node-service2-image-333011856334.asia-southeast2.run.app/"

        private fun getClient(): OkHttpClient {
            val loggingInterceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
            return OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .build()
        }

        fun getArtikelApiService(): ApiService {
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL_ARTICLE)
                .addConverterFactory(GsonConverterFactory.create())
                .client(getClient())
                .build()
            return retrofit.create(ApiService::class.java)

        }

        fun getChatBotApiService(): ApiService {
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL_CHATBOT)
                .addConverterFactory(GsonConverterFactory.create())
                .client(getClient())
                .build()
            return retrofit.create(ApiService::class.java)
        }
    }
}