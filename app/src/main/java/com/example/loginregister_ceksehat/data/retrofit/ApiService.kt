package com.example.loginregister_ceksehat.data.retrofit

import com.example.loginregister_ceksehat.data.ChatHistoryResponse
import com.example.loginregister_ceksehat.data.response.ArtikelResponse
import com.example.loginregister_ceksehat.data.response.ChatBotResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {

    @GET("api/articles")
    fun getArticles(): Call<List<ArtikelResponse>>

    @POST("/chat")
    fun getChatResponse(@Body input: Map<String, String>): Call<ChatBotResponse>

    @GET("/history")
    fun getChatHistory(): Call<List<ChatHistoryResponse>>

}



