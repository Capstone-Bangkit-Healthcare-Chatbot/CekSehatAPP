package com.example.loginregister_ceksehat.ui.chatbot

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.loginregister_ceksehat.adapter.ChatAdapter
import com.example.loginregister_ceksehat.data.ChatHistoryResponse
import com.example.loginregister_ceksehat.data.ChatMessage
import com.example.loginregister_ceksehat.data.response.ChatBotResponse
import com.example.loginregister_ceksehat.data.retrofit.ApiConfig
import com.example.loginregister_ceksehat.databinding.FragmentChatbotBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ChatBotFragment : Fragment() {

    private lateinit var binding: FragmentChatbotBinding
    private val chatList = mutableListOf<ChatMessage>()
    private lateinit var chatAdapter: ChatAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentChatbotBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Log.d("ChatBotFragment", "onViewCreated called")

        chatAdapter = ChatAdapter(chatList)
        binding.recyclerViewChat.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerViewChat.adapter = chatAdapter

        binding.sendButton.setOnClickListener {
            val message = binding.messageInput.text.toString()
            if (message.isNotEmpty()) {
                addMessageToChat("User: $message", true)
                sendChatMessage(message)
                binding.messageInput.text?.clear()

                hideWelcomeTexts()
            }
        }
    }

    private fun sendChatMessage(message: String) {
        val input = mapOf("input" to message)

        val client = ApiConfig.getChatBotApiService().getChatResponse(input)
        client.enqueue(object : Callback<ChatBotResponse> {
            override fun onResponse(
                call: Call<ChatBotResponse>,
                response: Response<ChatBotResponse>
            ) {
                if (response.isSuccessful) {
                    val chatResponse = response.body()?.response
                    chatResponse?.let {
                        addMessageToChat("Bot: $it", false)
                        // Setelah mendapatkan respon dari bot, kita panggil API untuk chat history
                        loadChatHistory()  // Panggil history hanya setelah pesan dikirim dan diterima respons
                    }
                }
            }

            override fun onFailure(call: Call<ChatBotResponse>, t: Throwable) {
                Log.e("ChatBotFragment", "Error sending chat message: ${t.message}")
            }
        })
    }

    private fun loadChatHistory() {
        val client = ApiConfig.getChatBotApiService().getChatHistory()
        client.enqueue(object : Callback<List<ChatHistoryResponse>> {
            override fun onResponse(
                call: Call<List<ChatHistoryResponse>>,
                response: Response<List<ChatHistoryResponse>>
            ) {
                if (response.isSuccessful) {
                    val chatHistory = response.body()

                    chatHistory?.forEach { history ->
                        chatList.add(ChatMessage("User: ${history.input}", true))
                        chatList.add(ChatMessage("Bot: ${history.response}", false))
                    }

                    chatAdapter.notifyDataSetChanged()
                    binding.recyclerViewChat.visibility = View.VISIBLE
                } else {
                    addMessageToChat("Error: ${response.message()}", false)
                }
            }

            override fun onFailure(call: Call<List<ChatHistoryResponse>>, t: Throwable) {
                addMessageToChat("Error: ${t.message}", false)
            }
        })
    }

    private fun addMessageToChat(message: String, isUser: Boolean) {
        chatList.add(ChatMessage(message, isUser))
        chatAdapter.notifyItemInserted(chatList.size - 1)
        binding.recyclerViewChat.scrollToPosition(chatList.size - 1)
    }

    private fun hideWelcomeTexts() {
        binding.textWelcome1.visibility = View.GONE
        binding.textWelcome2.visibility = View.GONE
        binding.textWelcome3.visibility = View.GONE
    }
}