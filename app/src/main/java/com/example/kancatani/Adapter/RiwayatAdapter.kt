package com.example.kancatani.Adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.kancatani.Model.PesananModel
import com.example.kancatani.Pembeli.ui.home.lihat_barang_pembeli
import com.example.kancatani.R
import com.example.kancatani.SharePreference.Sharepreference
import com.squareup.picasso.Picasso

class RiwayatAdapter(val context: Context, val List : ArrayList<PesananModel>) :
    RecyclerView.Adapter<RiwayatAdapter.Holder>(){

    val SP = Sharepreference()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RiwayatAdapter.Holder {
        val view = LayoutInflater.from(context).inflate(R.layout.list_riwayat, parent, false)
        return Holder(view)
    }

    override fun getItemCount(): Int {
        return List.size
    }

    override fun onBindViewHolder(holder: RiwayatAdapter.Holder, position: Int) {
        holder.bind(List[position], context)
    }

    inner class Holder(view: View?) : RecyclerView.ViewHolder(view!!),
        View.OnClickListener{

        val fotobarang : ImageView
        val namabarang : TextView
        val status : TextView
        val harga : TextView
        val jumlah : TextView
        val waktu : TextView
        val waktuproses : TextView

        init {
            fotobarang = view!!.findViewById(R.id.fotobarang) as ImageView
            namabarang = view.findViewById(R.id.namabarang) as TextView
            status = view.findViewById(R.id.status) as TextView
            harga = view.findViewById(R.id.harga) as TextView
            jumlah = view.findViewById(R.id.jumlah) as TextView
            waktu = view.findViewById(R.id.waktu) as TextView
            waktuproses = view.findViewById(R.id.waktuproses) as TextView
        }

        fun bind(list: PesananModel, context: Context) {
            Picasso.get().load(list.fotobarang).into(fotobarang)
            namabarang.setText(list.namabarang)
            status.setText(list.status)
            harga.setText(list.harga_total.toString())
            jumlah.setText(list.jumlah.toString())
            waktu.setText(list.waktu_pesan)
            waktuproses.setText(list.waktu_proses)

            if(SP.loadSP(context,"st") == "pembeli"){
                itemView.setOnClickListener {
                    val intent = Intent(context, lihat_barang_pembeli::class.java)
                    intent.putExtra("id", list.id_barang)
                    intent.putExtra("set", "detail")
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                    context.startActivity(intent)
                }
            }
        }

        override fun onClick(p0: View?) {

        }
    }

}