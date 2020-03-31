package com.example.kancatani.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.kancatani.Model.BarangModel
import com.example.kancatani.R
import com.squareup.picasso.Picasso

class BarangAdapter(val context: Context, val List : ArrayList<BarangModel>) :
    RecyclerView.Adapter<BarangAdapter.Holder>(){
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

    inner class Holder(view: View?) : RecyclerView.ViewHolder(view!!){
        val foto = itemView.findViewById(R.id.foto_barang) as ImageView
        val namabarang = itemView.findViewById(R.id.nama_barang) as TextView
        val harga = itemView.findViewById(R.id.harga) as TextView
        val bintang = itemView.findViewById(R.id.bintang) as TextView
        val jumlahkirim = itemView.findViewById(R.id.jumlahkirim) as TextView
        val kota = itemView.findViewById(R.id.kota_barang) as TextView
        fun bind(bal: BarangModel, context: Context) {
            Picasso.get().load(bal.fotobarang).into(foto)
            namabarang.setText(bal.nama)
            harga.setText("Rp. " + bal.harga.toString() + ",-")
            bintang.setText(bal.bintang)
            jumlahkirim.setText(bal.terjual.toString())
            kota.setText(bal.kota)
        }
    }
}
