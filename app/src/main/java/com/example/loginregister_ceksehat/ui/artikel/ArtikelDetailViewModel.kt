package com.example.loginregister_ceksehat.ui.artikel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.loginregister_ceksehat.data.response.ArtikelResponse

class ArtikelDetailViewModel : ViewModel() {

    private val _artikelDetail = MutableLiveData<ArtikelResponse>()
    val artikelDetail: LiveData<ArtikelResponse> get() = _artikelDetail

    fun getArtikelDetail(id: String) {
        val detail = ArtikelResponse(id, "Detail of Artikel #$id", "Complete content for artikel #$id", "2024-12-12")
        _artikelDetail.value = detail
    }
}
