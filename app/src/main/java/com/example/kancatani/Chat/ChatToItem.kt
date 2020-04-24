package com.example.oguru.Class

import com.example.kancatani.Model.ChatModel
import com.example.kancatani.R
import com.xwray.groupie.Item
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.message_list_item.view.*

class ChatToItem(val chat: ChatModel): Item<ViewHolder>() {
    override fun bind(viewHolder: ViewHolder, position: Int) {
        viewHolder.itemView.isi_pesan.text = chat.text
        viewHolder.itemView.jam_pesan.text = chat.jam
    }

    override fun getLayout(): Int {
        return R.layout.message_list_item
    }
}