package com.example.kancatani.Pembeli.ui.home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kancatani.Adapter.UlasanAdapter
import com.example.kancatani.Chat.MessageActivity
import com.example.kancatani.Etalase_penjual
import com.example.kancatani.Model.BarangModel
import com.example.kancatani.Model.UlasanModel
import com.example.kancatani.Model.UserModel
import com.example.kancatani.R
import com.example.kancatani.SharePreference.Sharepreference
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_lihat_barang_pembeli.*

class lihat_barang_pembeli : AppCompatActivity() {

    lateinit var SP: Sharepreference
    lateinit var listulasan: ArrayList<UlasanModel>
    lateinit var adaper: UlasanAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lihat_barang_pembeli)
        SP = Sharepreference()
        listulasan = arrayListOf()

        loading.visibility = View.VISIBLE
        if(intent.getStringExtra("set") != null){
            checkout.visibility = View.GONE
        }
        val id = intent.getStringExtra("id").toString()
        load(id)

        chat.setOnClickListener {
            val intent = Intent(this, MessageActivity::class.java)
            intent.putExtra("id", SP.loadSP(this, "idtk"))
            intent.putExtra("username", SP.loadSP(this, "usernametk"))
            intent.putExtra("foto", SP.loadSP(this, "fototk"))
            startActivity(intent)
        }

        checkout.setOnClickListener {
            val intent = Intent(this, keranjang_barang_pembeli::class.java)
            intent.putExtra("id", id)
            startActivity(intent)
        }

        kunjungitoko.setOnClickListener {
            val intent = Intent(this, Etalase_penjual::class.java)
            intent.putExtra("id", SP.loadSP(this, "idtk"))
            startActivity(intent)
        }

        ulasan.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        adaper = UlasanAdapter(this, listulasan)
        loadulasan(id)
    }

    private fun load(id: String){
        val ref = FirebaseDatabase.getInstance().getReference("barang")
            .orderByChild("id").equalTo(id)
        ref.addValueEventListener(object : ValueEventListener{
            override fun onCancelled(p0: DatabaseError) {

            }

            override fun onDataChange(p0: DataSnapshot) {
                if(p0.exists()){
                    p0.children.forEach {
                        val value = it.getValue(BarangModel::class.java)
                        if(value != null){
                            Picasso.get().load(value.fotobarang).into(gambar_barang)
                            namabarang.setText(value.nama)
                            harga.setText("Rp. " + value.harga + ",-")
                            rating.setText(value.bintang)
                            terjual.setText(value.terjual.toString()+" Terjual")
                            var kirim = "x"
                            for(i in value.jasapengiriman.indices){
                                if(kirim == "x"){
                                    kirim = value.jasapengiriman[i]
                                }else{
                                    kirim = kirim + ", " + value.jasapengiriman[i]
                                }
                            }
                            pengiriman.setText(kirim)
                            loadtoko(value.id_lapak)

                            stokbarang.setText(value.stok.toString())
                            kategoribarang.setText(value.kategori)
                            kondisibarang.setText(value.kondisi)
                            deskripsi.setText(value.deskripsi)
                        }
                    }
                }
            }

        })
    }

    private fun loadtoko(id: String){
        val refer = FirebaseDatabase.getInstance().getReference("pengguna")
            .orderByChild("id").equalTo(id)
        refer.addValueEventListener(object : ValueEventListener{
            override fun onCancelled(p0: DatabaseError) {

            }

            override fun onDataChange(p0: DataSnapshot) {
                if(p0.exists()){
                    p0.children.forEach {
                        val valu = it.getValue(UserModel::class.java)
                        if(valu != null){
                            Picasso.get().load(valu.foto).into(fototoko)
                            namatoko.setText(valu.nama)
                            lokasi.setText(valu.kota)
                            dikirimdari.setText(valu.alamat)
                            jumlahproduk(valu.id)

                            //untuk menuju chat
                            SP.createSP(applicationContext, "fototk", valu.foto)
                            SP.createSP(applicationContext, "usernametk", valu.nama)
                            SP.createSP(applicationContext, "idtk", valu.id)
                        }
                    }
                }
            }

        })
    }

    private fun jumlahproduk(id: String){
        val ref = FirebaseDatabase.getInstance().getReference("barang")
            .orderByChild("id_lapak").equalTo(id)
        ref.addValueEventListener(object : ValueEventListener{
            override fun onCancelled(p0: DatabaseError) {

            }

            override fun onDataChange(p0: DataSnapshot) {
                if(p0.exists()){
                    var jumlah = 0
                    p0.children.forEach {
                        val value = it.getValue(BarangModel::class.java)
                        if(value != null){
                            jumlah++
                        }
                        loading.visibility = View.GONE
                    }
                    jumlahproduk.setText(jumlah.toString())
                }
            }

        })
    }

    private fun loadulasan(id: String){
        val ref = FirebaseDatabase.getInstance().getReference("ulasan")
            .orderByChild("id_barang").equalTo(id)
        ref.addValueEventListener(object : ValueEventListener{
            override fun onCancelled(p0: DatabaseError) {

            }

            override fun onDataChange(p0: DataSnapshot) {
                listulasan.clear()
                if(p0.exists()){
                    p0.children.forEach{
                        val value = it.getValue(UlasanModel::class.java)
                        if(value != null){
                            listulasan.add(value)
                        }
                    }
                    ulasan.adapter = adaper
                    adaper.notifyDataSetChanged()
                }
            }

        })
    }
}
