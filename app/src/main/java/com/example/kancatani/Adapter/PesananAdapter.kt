package com.example.kancatani.Adapter

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.example.kancatani.Chat.MessageActivity
import com.example.kancatani.Interface.ItemClickListener
import com.example.kancatani.Model.PesananModel
import com.example.kancatani.Model.UserModel
import com.example.kancatani.Pembeli.HomePembeli
import com.example.kancatani.R
import com.example.kancatani.SharePreference.Sharepreference
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.squareup.picasso.Picasso
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class PesananAdapter(val context: Context, val List : ArrayList<PesananModel>) :
    RecyclerView.Adapter<PesananAdapter.Holder>(){

    val SP = Sharepreference()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PesananAdapter.Holder {
        val view = LayoutInflater.from(context).inflate(R.layout.list_pesanan, parent, false)
        return Holder(view)
    }

    override fun getItemCount(): Int {
        return List.size
    }

    override fun onBindViewHolder(holder: PesananAdapter.Holder, position: Int) {
        holder.bind(List[position], context)
    }

    inner class Holder(view: View?) : RecyclerView.ViewHolder(view!!),
        View.OnClickListener{
        val foto = itemView.findViewById(R.id.fotobarang) as ImageView
        val namapembeli = itemView.findViewById(R.id.namapembeli) as TextView
        val namabarang = itemView.findViewById(R.id.namabarang) as TextView
        val jasakirim = itemView.findViewById(R.id.jasakirim) as TextView
        val jumlah = itemView.findViewById(R.id.jumlah) as TextView

        lateinit var iItemClickListener: ItemClickListener

        fun setClick(itemclicklistener: ItemClickListener) {
            this.iItemClickListener = itemclicklistener
        }

        fun bind(bal: PesananModel, context: Context) {
            Picasso.get().load(bal.fotobarang).into(foto)
            namapembeli.setText(bal.namapembeli)
            namabarang.setText(bal.namabarang)
            jasakirim.setText(bal.jasapengiriman)
            jumlah.setText(bal.jumlah.toString())

            itemView.setOnClickListener {
                itemclick(bal, context)
            }

        }

        fun itemclick(bal: PesananModel, context: Context){
            val dialog = Dialog(context)
            dialog.setContentView(R.layout.detail_pesanan_penjual)

            val fotopembeli = dialog.findViewById<ImageView>(R.id.fotopembeli)
            val namapembeli = dialog.findViewById<TextView>(R.id.namapembeli)
            val namabarang = dialog.findViewById<TextView>(R.id.namabarang)
            val harga = dialog.findViewById<TextView>(R.id.harga)
            val jumlah = dialog.findViewById<TextView>(R.id.jumlahpesan)
            val pengiriman = dialog.findViewById<TextView>(R.id.pengiriman)
            val request = dialog.findViewById<TextView>(R.id.request)
            val tolak = dialog.findViewById<Button>(R.id.tolak)
            val proses = dialog.findViewById<Button>(R.id.terima)
            val chat = dialog.findViewById<ImageButton>(R.id.btpesan)

            if(bal.status == "Diproses"){
                proses.setText("Kirim")
            }

            val ref = FirebaseDatabase.getInstance().getReference("pengguna").child(bal.id_pembeli)
            ref.addListenerForSingleValueEvent(object : ValueEventListener{
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

            chat.setOnClickListener {
                val ref = FirebaseDatabase.getInstance().getReference("pengguna").child(bal.id_pembeli)
                ref.addListenerForSingleValueEvent(object : ValueEventListener{
                    override fun onCancelled(p0: DatabaseError) {

                    }

                    override fun onDataChange(p0: DataSnapshot) {
                        if(p0.exists()){
                            val value = p0.getValue(UserModel::class.java)
                            val intent = Intent(context, MessageActivity::class.java)
                            intent.putExtra("id", value!!.id)
                            intent.putExtra("username", value.nama)
                            intent.putExtra("foto", value.foto)
                            context.startActivity(intent)
                        }
                    }

                })
            }

            proses.setOnClickListener {
                val ref = FirebaseDatabase.getInstance().getReference("keranjang")
                    .child(bal.id)
                val sdf = SimpleDateFormat("dd/M/yyyy hh.mm")
                val waktu = sdf.format(Date())
                ref.child("waktu_proses").setValue(waktu.toString())
                if(bal.status == "Diproses"){
                    ref.child("status").setValue("Dikirim").addOnCompleteListener {
                        Toast.makeText(context, "Pesanan Dikirim", Toast.LENGTH_SHORT).show()
                        dialog.dismiss()
                    }
                }
                else{
                    ref.child("status").setValue("Diproses").addOnCompleteListener {
                        Toast.makeText(context, "Pesanan Diproses", Toast.LENGTH_SHORT).show()
                        dialog.dismiss()
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
                    Toast.makeText(context, "Pesanan Ditolak", Toast.LENGTH_SHORT).show()
                    dialog.dismiss()
                }
            }

            dialog.show()
        }

        override fun onClick(v: View?) {
            iItemClickListener.onItemClickListener(v!!, adapterPosition)
        }
    }
}