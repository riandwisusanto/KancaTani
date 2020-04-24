package com.example.kancatani.Chat

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.EditText
import android.widget.Toast
import com.example.kancatani.Model.ChatModel
import com.example.kancatani.Model.ReportModel
import com.example.kancatani.R
import com.example.kancatani.SharePreference.Sharepreference
import com.example.oguru.Class.ChatFromItem
import com.example.oguru.Class.ChatToItem
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.squareup.picasso.Picasso
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.activity_message.*
import java.text.SimpleDateFormat
import java.util.*

class MessageActivity : AppCompatActivity() {

    val adapter = GroupAdapter<ViewHolder>()
    lateinit var SP: Sharepreference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_message)
        SP = Sharepreference()

        chatlog.adapter = adapter

        nama_pengguna.setText(intent.getStringExtra("username"))
        if(intent.getStringExtra("foto") != "x" || intent.getStringExtra("foto") != null){
            Picasso.get().load(intent.getStringExtra("foto")).into(foto_pengguna)
        }

        listenMessage()
        send.setOnClickListener{
            performSendMessage()
        }

        btn_back.setOnClickListener{
            val intent = Intent(this, ChatActivity::class.java)
            startActivity(intent)
        }

        report.setOnClickListener{
            report()
        }
    }

    private fun listenMessage(){
        val ref = FirebaseDatabase.getInstance().getReference("pesan")
            .child(SP.loadSP(this, "id"))
            .child(intent.getStringExtra("id"))

        ref.addChildEventListener(object: ChildEventListener {
            override fun onChildAdded(p0: DataSnapshot, p1: String?) {
                val chatMessage = p0.getValue(ChatModel::class.java)
                if (chatMessage != null) {
                    if (chatMessage.fromId == intent.getStringExtra("id").toString()) {
                        adapter.add(ChatFromItem(chatMessage))
                    } else {
                        adapter.add(ChatToItem(chatMessage))
                    }
                }
            }

            override fun onCancelled(p0: DatabaseError) {

            }

            override fun onChildChanged(p0: DataSnapshot, p1: String?) {

            }

            override fun onChildMoved(p0: DataSnapshot, p1: String?) {

            }

            override fun onChildRemoved(p0: DataSnapshot) {

            }
        })
    }

    private fun performSendMessage() {
        val text = formpesan.text.toString()

        val fromId = SP.loadSP(this, "id")
        val toId = intent.getStringExtra("id")
        val username = intent.getStringExtra("username")
        val foto = intent.getStringExtra("foto")

        if (fromId == null) return

        val reference = FirebaseDatabase.getInstance().getReference("/pesan/$fromId/$toId").push()
        val referenceto = FirebaseDatabase.getInstance().getReference("/pesan/$toId/$fromId")
        val sdf = SimpleDateFormat("HH.mm")
        var waktu = sdf.format(Calendar.getInstance().time)
        val chatMessage = ChatModel(reference.key.toString(), text, fromId, toId.toString(),System.currentTimeMillis() / 1000
            , waktu.toString(), username.toString(), foto.toString())

        reference.setValue(chatMessage)
        referenceto.child(reference.key.toString()).setValue(chatMessage).addOnSuccessListener {
            formpesan.text.clear()
            chatlog.scrollToPosition(adapter.itemCount - 1)
        }

        //make latest chat
        val referencelatest = FirebaseDatabase.getInstance().getReference("/latest-pesan/$fromId/$toId")
        val referencelatestto = FirebaseDatabase.getInstance().getReference("/latest-pesan/$toId/$fromId")
        val chatMessageto = ChatModel(reference.key.toString(), text, toId.toString(), fromId, System.currentTimeMillis() / 1000
            , waktu.toString(), SP.loadSP(this, "username"), SP.loadSP(this, "fotoprofil"))
        referencelatest.setValue(chatMessage)
        referencelatestto.setValue(chatMessageto)
    }

    private fun report(){
        val dialog = AlertDialog.Builder(this)
        val inflater = LayoutInflater.from(this)
        val view = inflater.inflate(R.layout.report_pengguna, null)

        val report_txt = view.findViewById<EditText>(R.id.reporttxt)
        dialog.setView(view)
            .setCancelable(false)
            .setPositiveButton("Laporkan", object: DialogInterface.OnClickListener{
                override fun onClick(p1: DialogInterface?, p2: Int) {
                    val ref = FirebaseDatabase.getInstance().getReference("report")
                    val id = ref.push().key.toString()
                    val value = ReportModel(id, SP.loadSP(applicationContext, "id"), intent.getStringExtra("id"), report_txt.text.toString())
                    ref.child(id).setValue(value).addOnCompleteListener {
                        Toast.makeText(applicationContext, "Berhasil Dilaporkan", Toast.LENGTH_SHORT).show()
                        p1?.cancel()
                    }
                }
            })
            .setNegativeButton("Batal", object : DialogInterface.OnClickListener{
                override fun onClick(p0: DialogInterface?, p1: Int) {
                    p0?.cancel()
                }
            })
            .create().show()
    }
}
