package com.example.loginregister_ceksehat.ui.artikel

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.loginregister_ceksehat.R
import com.example.loginregister_ceksehat.adapter.ArtikelAdapter
import com.example.loginregister_ceksehat.databinding.FragmentArtikelBinding

class ArtikelFragment : Fragment() {

    private lateinit var binding: FragmentArtikelBinding
    private val artikelViewModel: ArtikelViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentArtikelBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        artikelViewModel.getArticles()

        artikelViewModel.articles.observe(viewLifecycleOwner) { articles ->
            binding.recyclerViewArtikel.apply {
                layoutManager = LinearLayoutManager(context)
                adapter = ArtikelAdapter(articles) { artikel ->
                    val fragment = ArtikelDetailFragment()
                    val bundle = Bundle().apply {
                        putParcelable("artikel", artikel)
                    }
                    fragment.arguments = bundle

                    requireActivity().supportFragmentManager.beginTransaction()
                        .replace(R.id.nav_host_fragment_activity_main, fragment)
                        .addToBackStack(null)
                        .commit()
                }
            }
        }
    }
}
