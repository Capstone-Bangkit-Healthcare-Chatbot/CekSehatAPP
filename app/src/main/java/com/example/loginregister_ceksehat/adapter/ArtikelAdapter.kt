package com.example.loginregister_ceksehat.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.loginregister_ceksehat.data.response.ArtikelResponse
import com.example.loginregister_ceksehat.databinding.ItemArtikelBinding

class ArtikelAdapter(
    private var listArtikel: List<ArtikelResponse>,
    private val onItemClick: (ArtikelResponse) -> Unit
) : RecyclerView.Adapter<ArtikelAdapter.ViewHolder>() {

    fun setList(newList: List<ArtikelResponse>) {
        listArtikel = newList
        notifyDataSetChanged()
    }

    class ViewHolder(private val binding: ItemArtikelBinding, private val onItemClick: (ArtikelResponse) -> Unit) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(artikel: ArtikelResponse) {
            binding.articleTitle.text = artikel.title
            binding.articleDescription.text = artikel.content
            itemView.setOnClickListener { onItemClick(artikel) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemArtikelBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding, onItemClick)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listArtikel[position])
    }

    override fun getItemCount(): Int = listArtikel.size
}
