package com.example.kancatani.Chat

import com.example.kancatani.Model.ChatModel
import com.example.kancatani.R
import com.squareup.picasso.Picasso
import com.xwray.groupie.Item
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.chat_list_item.view.*

class UserItemChat(val user: ChatModel): Item<ViewHolder>() {
    override fun getLayout(): Int {
        return R.layout.chat_list_item
    }

    override fun bind(viewHolder: ViewHolder, position: Int) {
        viewHolder.itemView.namauser.text = user.username
        Picasso.get().load(user.foto).into(viewHolder.itemView.foto_p)
        viewHolder.itemView.latestchat.text = user.text
        viewHolder.itemView.jam_lastchat.text = user.jam
    }
}