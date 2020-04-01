package com.example.kancatani.Pembeli.ui.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.kancatani.Model.BarangModel
import com.example.kancatani.Model.UserModel
import com.example.kancatani.R
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_lihat_barang_pembeli.*

class lihat_barang_pembeli : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lihat_barang_pembeli)

        loading.visibility = View.VISIBLE
        val id = intent.getStringExtra("id").toString()
        load(id)
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
}
