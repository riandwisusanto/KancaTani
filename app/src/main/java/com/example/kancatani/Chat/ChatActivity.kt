package com.example.kancatani.Chat

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import com.example.kancatani.Pembeli.HomePembeli
import com.example.kancatani.Penjual.PenjualActivity
import com.example.kancatani.R
import com.example.kancatani.SharePreference.Sharepreference
import com.example.kancatani.Model.ChatModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.activity_chat.*

class ChatActivity : AppCompatActivity() {

    lateinit var SP: Sharepreference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)
        SP = Sharepreference()
        fetchUsers()
        btn_back.setOnClickListener{
            var intent = Intent()
            if(SP.loadSP(this, "st") == "pembeli"){
                intent = Intent(this, HomePembeli::class.java)
            }
            else{
                intent = Intent(this, PenjualActivity::class.java)
            }
            startActivity(intent)
            finish()
        }
    }

    private fun fetchUsers() {
        val ref = FirebaseDatabase.getInstance().getReference("latest-pesan").child(SP.loadSP(this, "id"))
        val adapter = GroupAdapter<ViewHolder>()
        ref.addValueEventListener(object: ValueEventListener {

            override fun onDataChange(p0: DataSnapshot) {
                p0.children.forEach {
                    val user = it.getValue(ChatModel::class.java)
                    if (user != null) {
                        adapter.add(
                            UserItemChat(user)
                        )
                    }
                }

                adapter.setOnItemClickListener { item, view ->

                    val userItem = item as UserItemChat

                    val intent = Intent(view.context, MessageActivity::class.java)
                    intent.putExtra("id", userItem.user.toId)
                    intent.putExtra("username", userItem.user.username)
                    intent.putExtra("foto", userItem.user.foto)
                    startActivity(intent)
                }

                chatlist.adapter = adapter
            }

            override fun onCancelled(p0: DatabaseError) {

            }
        })
    }

    override fun onBackPressed(){
        var intent = Intent()
        if(SP.loadSP(this, "st") == "pembeli"){
            intent = Intent(this, HomePembeli::class.java)
        }
        else{
            intent = Intent(this, PenjualActivity::class.java)
        }
        startActivity(intent)
        finish()
    }
}
