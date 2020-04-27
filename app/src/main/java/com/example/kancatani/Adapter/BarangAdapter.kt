package com.example.kancatani.Adapter

import android.app.Activity
import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.example.kancatani.Interface.ItemClickListener
import com.example.kancatani.Model.BarangModel
import com.example.kancatani.Model.UserModel
import com.example.kancatani.Pembeli.ui.home.lihat_barang_pembeli
import com.example.kancatani.Penjual.ui.Lapak.edit_barang
import com.example.kancatani.R
import com.example.kancatani.SharePreference.Sharepreference
import com.google.firebase.database.*
import com.google.firebase.storage.FirebaseStorage
import com.squareup.picasso.Picasso
import java.io.ByteArrayOutputStream
import java.io.FileNotFoundException
import java.io.InputStream

class BarangAdapter(val context: Context, val List : ArrayList<BarangModel>) :
    RecyclerView.Adapter<BarangAdapter.Holder>(){

    val SP = Sharepreference()
    private var fotoselected: Uri? = null
    val pengiriman : ArrayList<String> = arrayListOf()
    var kondisit = toString()
    var kategorit = toString()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BarangAdapter.Holder {
        val view = LayoutInflater.from(context).inflate(R.layout.list_item_barang, parent, false)
        return Holder(view)
    }

    override fun getItemCount(): Int {
        return List.size
    }

    override fun onBindViewHolder(holder: BarangAdapter.Holder, position: Int) {
        holder.bind(List[position], context)
    }

    inner class Holder(view: View?) : RecyclerView.ViewHolder(view!!),
        View.OnClickListener{
            val foto = itemView.findViewById(R.id.foto_barang) as ImageView
            val namabarang = itemView.findViewById(R.id.nama_barang) as TextView
            val harga = itemView.findViewById(R.id.harga) as TextView
            val bintang = itemView.findViewById(R.id.bintang) as TextView
            val jumlahkirim = itemView.findViewById(R.id.jumlahkirim) as TextView
            val kota = itemView.findViewById(R.id.kota_barang) as TextView

            lateinit var iItemClickListener: ItemClickListener

            fun setClick(itemclicklistener: ItemClickListener) {
                this.iItemClickListener = itemclicklistener
            }

            fun bind(bal: BarangModel, context: Context) {
                Picasso.get().load(bal.fotobarang).into(foto)
                namabarang.setText(bal.nama)
                harga.setText("Rp. " + bal.harga.toString() + ",-")
                bintang.setText(bal.bintang)
                jumlahkirim.setText(bal.terjual.toString())
                kota.setText(bal.kota)
                itemView.setOnClickListener {

                    if(SP.loadSP(context, "st") == "penjual"){
                        val dialog = Dialog(context)
                        dialog.setTitle("Detail Barang")
                        dialog.setContentView(R.layout.detail_barang_lapak)

                        load(dialog, bal)

                        val edit = dialog.findViewById<Button>(R.id.edit)
                        val hapus = dialog.findViewById<Button>(R.id.hapus)

                        edit.setOnClickListener {
                            val intent = Intent(context, edit_barang::class.java)
                            intent.putExtra("id", bal.id)
                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                            context.startActivity(intent)
                        }

                        hapus.setOnClickListener {
                            hapus(context, bal, dialog)
                        }

                        dialog.show()
                    }

                    else{
                        val intent = Intent(context, lihat_barang_pembeli::class.java)
                        intent.putExtra("id", bal.id)
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        context.startActivity(intent)
                    }
                }
            }

        override fun onClick(v: View?) {
            iItemClickListener.onItemClickListener(v!!, adapterPosition)
        }
    }

    private fun load(view: Dialog, list: BarangModel){
        val gambar_barang = view.findViewById<ImageView>(R.id.gambar_barang)
        val namabarang = view.findViewById<TextView>(R.id.namabarang)
        val harga = view.findViewById<TextView>(R.id.harga)
        val rating = view.findViewById<TextView>(R.id.rating)
        val terjual = view.findViewById<TextView>(R.id.terjual)
        val pengiriman = view.findViewById<TextView>(R.id.pengiriman)
        val stokbarang = view.findViewById<TextView>(R.id.stokbarang)
        val kategoribarang = view.findViewById<TextView>(R.id.kategoribarang)
        val kondisibarang = view.findViewById<TextView>(R.id.kondisibarang)
        val deskripsi = view.findViewById<TextView>(R.id.deskripsi)
        val dikirimdari = view.findViewById<TextView>(R.id.dikirimdari)
        val loading = view.findViewById<RelativeLayout>(R.id.loading)
        loading.visibility = View.GONE

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

    }

    private fun hapus(context: Context, list: BarangModel, dial: Dialog){
        val dialog = AlertDialog.Builder(dial.context)
        dialog.setTitle("Hapus")
        dialog.setMessage("Hapus " + list.nama + " ?")
            .setCancelable(true)
            .setPositiveButton("Hapus", object : DialogInterface.OnClickListener {
                override fun onClick(dialog: DialogInterface?, id: Int) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                        val ref = FirebaseDatabase.getInstance().getReference("barang")
                        ref.child(list.id).removeValue().addOnCompleteListener {
                            Toast.makeText(context, "Berhasil Dihapus", Toast.LENGTH_SHORT).show()
                        }
                        dialog!!.dismiss()
                        dial.dismiss()
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
