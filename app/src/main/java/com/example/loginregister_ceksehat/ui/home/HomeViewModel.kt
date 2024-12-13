package com.example.loginregister_ceksehat.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.loginregister_ceksehat.data.response.ArtikelResponse
import com.example.loginregister_ceksehat.data.retrofit.ApiConfig
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.await

class HomeViewModel : ViewModel() {

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _newsList = MutableLiveData<List<ArtikelResponse>>()
    val newsList: LiveData<List<ArtikelResponse>> = _newsList

    init {
        fetchNews()
    }

    private fun fetchNews() {
        _isLoading.value = true
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = ApiConfig.getArtikelApiService().getArticles().await()
                _newsList.postValue(response)
            } catch (e: Exception) {
                e.printStackTrace()
                _newsList.postValue(emptyList())
            } finally {
                _isLoading.postValue(false)
            }
        }
    }
}
