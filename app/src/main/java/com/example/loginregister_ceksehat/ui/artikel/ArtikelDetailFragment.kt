package com.example.loginregister_ceksehat.ui.artikel

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.loginregister_ceksehat.data.response.ArtikelResponse
import com.example.loginregister_ceksehat.databinding.FragmentArtikelDetailBinding

class ArtikelDetailFragment : Fragment() {

    private lateinit var binding: FragmentArtikelDetailBinding
    private val artikelViewModel: ArtikelViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentArtikelDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val artikel = arguments?.getParcelable<ArtikelResponse>("artikel")
        artikel?.let {
            binding.textViewTitle.text = it.title
            binding.textViewContent.text = it.content
        }
    }
}
