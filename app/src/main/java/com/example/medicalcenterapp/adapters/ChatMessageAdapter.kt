package com.example.medicalcenterapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.medicalcenterapp.R
import com.example.medicalcenterapp.utilities.AppController
import com.pusher.chatkit.messages.Message


class ChatMessageAdapter: RecyclerView.Adapter<ChatMessageAdapter.ViewHolder>() {

    private var list = ArrayList<Message>()

    fun addMessage(e: Message){
        list.add(e)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
                                 .inflate(R.layout.custom_chat_row, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
       holder.userName.text = list[position].userId
        holder.messageBody.text = list[position].text
        holder.bind(list[position])
    }




    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var userName: TextView = itemView.findViewById(R.id.text_user_name)
        var messageBody: TextView = itemView.findViewById(R.id.chat_message)
        private val cardView : CardView = itemView.findViewById(R.id.card_viewChat)

        fun bind(message: Message) =with(itemView){
            val params = cardView.layoutParams as RelativeLayout.LayoutParams
            if(message.userId == AppController.currentUser.id){
                params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT)

            }
        }
    }
}