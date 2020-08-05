package com.ublipi.questionAnswer.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ublipi.questionAnswer.R
import com.ublipi.questionAnswer.data.model.Answer
import kotlinx.android.synthetic.main.item_message_incoming.view.*

private const val VIEW_TYPE_MY_MESSAGE = 1
private const val VIEW_TYPE_OTHER_MESSAGE = 2

class ChatAdapter (private val context: Context) : RecyclerView.Adapter<ChatViewHolder>() {
    private val answers: ArrayList<Answer> = ArrayList()

    fun addAnswers(answer: Answer){
        answers.add(answer)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return answers.size
    }

    override fun getItemViewType(position: Int): Int {
        val answer = answers.get(position)

        return if(answer.user == 1) {
            VIEW_TYPE_MY_MESSAGE
        }
        else {
            VIEW_TYPE_OTHER_MESSAGE
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatViewHolder {
        return if(viewType == VIEW_TYPE_MY_MESSAGE) {
            MyChatViewHolder(LayoutInflater.from(context).inflate(R.layout.item_message_outgoing, parent, false))
        } else {
            OtherChatViewHolder(LayoutInflater.from(context).inflate(R.layout.item_message_incoming, parent, false))
        }
    }

    override fun onBindViewHolder(holder: ChatViewHolder, position: Int) {
        val message = answers[position]

        holder.bind(message)
    }

    inner class MyChatViewHolder (view: View) : ChatViewHolder(view) {
        private var messageText: TextView = view.tvMessage

        override fun bind(answer: Answer) {
            messageText.text = answer.answer
        }
    }

    inner class OtherChatViewHolder (view: View) : ChatViewHolder(view) {
        private var messageText: TextView = view.tvMessage

        override fun bind(answer: Answer) {
            messageText.text = answer.answer
        }
    }
}

open class ChatViewHolder (view: View) : RecyclerView.ViewHolder(view) {
    open fun bind(answer:Answer) {}
}