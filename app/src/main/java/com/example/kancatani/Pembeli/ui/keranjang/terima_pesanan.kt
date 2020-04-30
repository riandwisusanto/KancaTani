package com.example.kancatani.Pembeli.ui.keranjang

import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.kancatani.Model.BarangModel
import com.example.kancatani.Model.PesananModel
import com.example.kancatani.Model.UlasanModel
import com.example.kancatani.R
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_terima_pesanan.*
import java.text.SimpleDateFormat
import java.util.*

class terima_pesanan : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_terima_pesanan)

        val list = intent.getSerializableExtra("list") as PesananModel

        val terima = findViewById<Button>(R.id.terima)
        val rat = findViewById<LinearLayout>(R.id.beribintang)
        val rating = findViewById<RatingBar>(R.id.rating)
        val ulasan = findViewById<EditText>(R.id.ulasan)
        val pesananselesai = findViewById<Button>(R.id.pesananselesai)

        val fotobarang = findViewById<ImageView>(R.id.fotobarang)
        val namabarang = findViewById<TextView>(R.id.namabarang)
        val status = findViewById<TextView>(R.id.status)
        val harga = findViewById<TextView>(R.id.harga)
        val jumlah = findViewById<TextView>(R.id.jumlah)
        val waktu_pesan = findViewById<TextView>(R.id.waktu)
        Picasso.get().load(list.fotobarang).into(fotobarang)
        namabarang.setText(list.namabarang)
        status.setText(list.status)
        harga.setText(list.harga_total.toString())
        jumlah.setText("x" + list.jumlah.toString())
        waktu_pesan.setText(list.waktu_pesan)

        waktuproses.setText(list.waktu_proses)
        waktukirim.setText(list.waktu_kirim)

        terima.setOnClickListener {
            rat.visibility = View.VISIBLE
        }

        pesananselesai.setOnClickListener {
            var ulas = ulasan.text.toString()
            if(ulas.isEmpty()){
                ulas = "-"
            }
            val refulasan = FirebaseDatabase.getInstance().getReference("ulasan")
            val id_ulasan = refulasan.push().key
            val value = UlasanModel(id_ulasan.toString(), list.id, list.id_barang, list.id_penjual, list.id_pembeli,
                rating.numStars.toString(), ulas)
            refulasan.child(id_ulasan.toString()).setValue(value)

            val refx = FirebaseDatabase.getInstance().getReference("keranjang")
                .child(list.id)
            val sdf = SimpleDateFormat("dd/M/yyyy hh.mm")
            val waktu = sdf.format(Date())
            refx.child("waktu_diterima").setValue(waktu.toString())

            kalkulasibintang(list.id_barang)

            setterjual(list.id_barang, list.jumlah)

            val ref = FirebaseDatabase.getInstance().getReference("keranjang")
                .child(list.id)
            ref.child("status_diterima").setValue(true).addOnCompleteListener {
                Toast.makeText(applicationContext, "Barang telah diterima", Toast.LENGTH_SHORT).show()
            }
        }

        btn_back.setOnClickListener {
            finish()
        }
    }

    private fun kalkulasibintang(idbarang: String){
        val ref = FirebaseDatabase.getInstance().getReference("ulasan").orderByChild("id_barang").equalTo(idbarang)
        ref.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {

            }

            override fun onDataChange(p0: DataSnapshot) {
                if(p0.exists()){
                    var x = 0
                    var y = 0
                    p0.children.forEach{
                        val value = it.getValue(UlasanModel::class.java)
                        if(value != null){
                            x = x + value.bintang.toString().toInt()
                            y++
                        }
                    }
                    val rate = (x / y).toFloat()
                    FirebaseDatabase.getInstance().getReference("barang").child(idbarang).child("bintang").setValue(rate.toString())
                }
            }

        })
    }

    private fun setterjual(idbarang: String, jumlah: Int){
        val ref = FirebaseDatabase.getInstance().getReference("barang")
            .child(idbarang)
        ref.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {

            }

            override fun onDataChange(p0: DataSnapshot) {
                if(p0.exists()){
                    val value = p0.getValue(BarangModel::class.java)
                    if(value != null){
                        val jum = value.terjual
                        val stok = value.stok
                        ref.child("terjual").setValue(jum + jumlah)
                        ref.child("stok").setValue(stok - jumlah)
                    }
                }
            }
        })
    }
}