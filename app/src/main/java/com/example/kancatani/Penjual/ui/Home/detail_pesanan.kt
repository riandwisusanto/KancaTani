package com.example.kancatani.Penjual.ui.Home

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.kancatani.Chat.MessageActivity
import com.example.kancatani.Model.PesananModel
import com.example.kancatani.Model.UserModel
import com.example.kancatani.R
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.detail_pesanan_penjual.*
import java.text.SimpleDateFormat
import java.util.*

class detail_pesanan : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.detail_pesanan_penjual)

        val bal = intent.getSerializableExtra("list") as PesananModel
        val fotopembeli = findViewById<ImageView>(R.id.fotopembeli)
            val namapembeli = findViewById<TextView>(R.id.namapembeli)
            val namabarang = findViewById<TextView>(R.id.namabarang)
            val harga = findViewById<TextView>(R.id.harga)
            val jumlah = findViewById<TextView>(R.id.jumlahpesan)
            val pengiriman = findViewById<TextView>(R.id.pengiriman)
            val request = findViewById<TextView>(R.id.request)
            val tolak = findViewById<Button>(R.id.tolak)
            val proses = findViewById<Button>(R.id.terima)
            val chat = findViewById<ImageButton>(R.id.btpesan)

            if(bal.status == "Diproses"){
                proses.setText("Kirim")
            }

            val ref = FirebaseDatabase.getInstance().getReference("pengguna").child(bal.id_pembeli)
            ref.addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onCancelled(p0: DatabaseError) {

                }

                override fun onDataChange(p0: DataSnapshot) {
                    if(p0.exists()){
                        val value = p0.getValue(UserModel::class.java)
                        Picasso.get().load(value!!.foto).into(fotopembeli)
                        namapembeli.setText(value.nama)
                    }
                }

            })

            namabarang.setText(bal.namabarang)
            harga.setText("Rp." + bal.harga.toString() + ",-")
            jumlah.setText(bal.jumlah.toString())
            pengiriman.setText(bal.jasapengiriman)
            request.setText(bal.pesan)

            if(bal.status == "Diproses"){
                tolak.visibility = View.GONE
            }

            if(bal.status == "Dikirim"){
                proses.visibility = View.GONE
                tolak.visibility = View.GONE
            }

            chat.setOnClickListener {
                val ref = FirebaseDatabase.getInstance().getReference("pengguna").child(bal.id_pembeli)
                ref.addListenerForSingleValueEvent(object : ValueEventListener{
                    override fun onCancelled(p0: DatabaseError) {

                    }

                    override fun onDataChange(p0: DataSnapshot) {
                        if(p0.exists()){
                            val value = p0.getValue(UserModel::class.java)
                            val intent = Intent(applicationContext, MessageActivity::class.java)
                            intent.putExtra("id", value!!.id)
                            intent.putExtra("username", value.nama)
                            intent.putExtra("foto", value.foto)
                            startActivity(intent)
                        }
                    }

                })
            }

            proses.setOnClickListener {
                val ref = FirebaseDatabase.getInstance().getReference("keranjang")
                    .child(bal.id)
                val sdf = SimpleDateFormat("dd/M/yyyy hh.mm")
                val waktu = sdf.format(Date())
                if(bal.status == "Diproses"){
                    ref.child("waktu_kirim").setValue(waktu.toString())
                    ref.child("status").setValue("Dikirim").addOnCompleteListener {
                        Toast.makeText(applicationContext, "Pesanan Dikirim", Toast.LENGTH_SHORT).show()
                    }
                }
                else{
                    ref.child("waktu_proses").setValue(waktu.toString())
                    ref.child("status").setValue("Diproses").addOnCompleteListener {
                        Toast.makeText(applicationContext, "Pesanan Diproses", Toast.LENGTH_SHORT).show()
                    }
                }
            }

            tolak.setOnClickListener {
                val ref = FirebaseDatabase.getInstance().getReference("keranjang")
                    .child(bal.id)
                val sdf = SimpleDateFormat("HH.mm")
                val waktu = sdf.format(Calendar.getInstance().time)
                ref.child("waktu_proses").setValue(waktu.toString())
                ref.child("status").setValue("Ditolak").addOnCompleteListener {
                    Toast.makeText(applicationContext, "Pesanan Ditolak", Toast.LENGTH_SHORT).show()
                }
            }

        btn_back.setOnClickListener {
            finish()
        }
    }

}