package com.example.kancatani.Penjual.ui.Lapak

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.kancatani.Model.BarangModel
import com.example.kancatani.R
import com.example.kancatani.SharePreference.Sharepreference
import com.google.firebase.database.FirebaseDatabase
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.detail_barang_lapak.*

class detail_produk : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.detail_barang_lapak)

        loading.visibility = View.GONE
        val list = intent.getSerializableExtra("list") as BarangModel

        Picasso.get().load(list.fotobarang).into(gambar_barang)
        namabarang.setText(list.nama)
        harga.setText("Rp. " + list.harga + ",-")
        rating.setText(list.bintang)
        terjual.setText(list.terjual.toString()+" Terjual")
        var kirim = "x"
        for(i in list.jasapengiriman.indices){
            if(kirim == "x"){
                kirim = list.jasapengiriman[i]
            }else{
                kirim = kirim + ", " + list.jasapengiriman[i]
            }
        }
        pengiriman.setText(kirim)

        stokbarang.setText(list.stok.toString())
        kategoribarang.setText(list.kategori)
        kondisibarang.setText(list.kondisi)
        dikirimdari.setText(list.kecamatan)
        deskripsi.setText(list.deskripsi)

        edit.setOnClickListener {
            val intent = Intent(applicationContext, edit_barang::class.java)
            intent.putExtra("id", list.id)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
        }

        hapus.setOnClickListener {
            hapus(list)
        }

        btn_back.setOnClickListener {
            finish()
        }
    }

    private fun hapus(list: BarangModel){
        val dialog = AlertDialog.Builder(this)
        dialog.setTitle("Hapus")
        dialog.setMessage("Hapus " + list.nama + " ?")
            .setCancelable(true)
            .setPositiveButton("Hapus", object : DialogInterface.OnClickListener {
                override fun onClick(dialog: DialogInterface?, id: Int) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                        val ref = FirebaseDatabase.getInstance().getReference("barang")
                        ref.child(list.id).removeValue().addOnCompleteListener {
                            Toast.makeText(applicationContext, "Berhasil Dihapus", Toast.LENGTH_SHORT).show()
                        }
                        finish()
                        dialog!!.dismiss()
                    }
                }
            })
            .setNegativeButton("Cancel", object : DialogInterface.OnClickListener{
                override fun onClick(dialog: DialogInterface?, which: Int) {
                    dialog!!.dismiss()
                }
            })
            .create().show()
    }
}