package com.example.kancatani.Adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.kancatani.Interface.ItemClickListener
import com.example.kancatani.Model.BarangModel
import com.example.kancatani.Pembeli.ui.home.lihat_barang_pembeli
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
                    val intent = Intent(context, lihat_barang_pembeli::class.java)
                    intent.putExtra("id", bal.id)
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                    context.startActivity(intent)
                }

            }

        override fun onClick(v: View?) {
            iItemClickListener.onItemClickListener(v!!, adapterPosition)
        }
    }
}
