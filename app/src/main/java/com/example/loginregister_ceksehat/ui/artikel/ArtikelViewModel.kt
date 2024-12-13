package com.example.loginregister_ceksehat.ui.artikel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.loginregister_ceksehat.data.response.ArtikelResponse
import com.example.loginregister_ceksehat.data.retrofit.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ArtikelViewModel : ViewModel() {
    private val _articles = MutableLiveData<List<ArtikelResponse>>()
    val articles: LiveData<List<ArtikelResponse>> = _articles

    fun getArticles() {
        val client = ApiConfig.getArtikelApiService().getArticles()
        client.enqueue(object : Callback<List<ArtikelResponse>> {
            override fun onResponse(
                call: Call<List<ArtikelResponse>>,
                response: Response<List<ArtikelResponse>>
            ) {
                if (response.isSuccessful) {
                    _articles.value = response.body()
                }
            }

            override fun onFailure(call: Call<List<ArtikelResponse>>, t: Throwable) {
            }
        })
    }
}
