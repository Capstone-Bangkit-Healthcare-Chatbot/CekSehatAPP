package com.example.loginregister_ceksehat.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.loginregister_ceksehat.R
import com.example.loginregister_ceksehat.data.ChatMessage

class ChatAdapter(private val chatList: List<ChatMessage>) :
    RecyclerView.Adapter<ChatAdapter.ChatViewHolder>() {

    class ChatViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val chatTextView: TextView = itemView.findViewById(R.id.chatTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_chat_message, parent, false)
        return ChatViewHolder(view)
    }

    override fun onBindViewHolder(holder: ChatViewHolder, position: Int) {
        val chatMessage = chatList[position]
        holder.chatTextView.text = chatMessage.message

        // Set background berbeda untuk pesan pengguna dan chatbot
        holder.chatTextView.setBackgroundResource(
            if (chatMessage.isUser) R.drawable.bg_user_message else R.drawable.bg_bot_message
        )

        Log.d("ChatAdapter", "Binding message: ${chatMessage.message}, isUser: ${chatMessage.isUser}")
    }


    override fun getItemCount(): Int {
        Log.d("ChatAdapter", "getItemCount: ${chatList.size}")
        return chatList.size
    }

}

