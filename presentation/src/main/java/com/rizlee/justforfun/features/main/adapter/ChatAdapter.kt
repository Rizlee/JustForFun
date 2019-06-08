package com.rizlee.justforfun.features.main.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rizlee.justforfun.R
import kotlinx.android.synthetic.main.item_my_message.view.*
import kotlinx.android.synthetic.main.item_recieved_message.view.*

private const val SENT_TYPE = 0
private const val RECEIVED_TYPE = 1

class ChatAdapter : RecyclerView.Adapter<ChatAdapter.ChatHolder>() {

    private var messages = mutableListOf<Message>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            ChatHolder(if (viewType == SENT_TYPE)
                LayoutInflater.from(parent.context).inflate(R.layout.item_my_message, parent, false)
            else LayoutInflater.from(parent.context).inflate(R.layout.item_recieved_message, parent, false))

    override fun onBindViewHolder(holder: ChatHolder, position: Int) {
        with(holder) {
            when (getItemViewType(position)) {
                SENT_TYPE -> itemView.messageSent.text = messages[position].message
                RECEIVED_TYPE -> itemView.messageReceived.text = messages[position].message
            }
        }
    }

    override fun getItemCount() = messages.size

    fun addMessage(message: Message) {
        messages.add(message)
        notifyItemInserted(messages.lastIndex)

    }

    override fun getItemViewType(position: Int) =
            if (messages[position].isReceived) RECEIVED_TYPE else SENT_TYPE

    class ChatHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    data class Message(var message: String, var isReceived: Boolean)
}
