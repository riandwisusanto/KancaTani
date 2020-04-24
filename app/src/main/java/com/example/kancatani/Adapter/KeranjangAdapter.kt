package com.example.kancatani.Adapter

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.example.kancatani.Interface.ItemClickListener
import com.example.kancatani.Model.PesananModel
import com.example.kancatani.Model.UlasanModel
import com.example.kancatani.Pembeli.ui.home.lihat_barang_pembeli
import com.example.kancatani.R
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.squareup.picasso.Picasso
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class KeranjangAdapter(val context: Context, val List : ArrayList<PesananModel>) :
    RecyclerView.Adapter<KeranjangAdapter.Holder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): KeranjangAdapter.Holder {
        val view = LayoutInflater.from(context).inflate(R.layout.list_keranjang, parent, false)
        return Holder(view)
    }

    override fun getItemCount(): Int {
        return List.size
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
            if(list.waktu_proses != "x"){
                waktuproses.setText(list.waktu_proses)
            }
            itemView.setOnClickListener {
                if(list.status == "Dikirim"){
                    val dialog = Dialog(context)
                    dialog.setContentView(R.layout.activity_terima_pesanan)

                    val terima = dialog.findViewById<Button>(R.id.terima)
                    val rat = dialog.findViewById<LinearLayout>(R.id.beribintang)
                    val rating = dialog.findViewById<RatingBar>(R.id.rating)
                    val ulasan = dialog.findViewById<EditText>(R.id.ulasan)
                    val pesananselesai = dialog.findViewById<Button>(R.id.pesananselesai)

                    val fotobarang = dialog.findViewById<ImageView>(R.id.fotobarang)
                    val namabarang = dialog.findViewById<TextView>(R.id.namabarang)
                    val status = dialog.findViewById<TextView>(R.id.status)
                    val waktu_proses = dialog.findViewById<TextView>(R.id.waktuproses)
                    val harga = dialog.findViewById<TextView>(R.id.harga)
                    val jumlah = dialog.findViewById<TextView>(R.id.jumlah)
                    val waktu_pesan = dialog.findViewById<TextView>(R.id.waktu)

                    Picasso.get().load(list.fotobarang).into(fotobarang)
                    namabarang.setText(list.namabarang)
                    status.setText(list.status)
                    waktu_proses.setText(list.waktu_proses)
                    harga.setText(list.harga_total.toString())
                    jumlah.setText("x" + list.jumlah.toString())
                    waktu_pesan.setText(list.waktu_pesan)

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

                        val ref = FirebaseDatabase.getInstance().getReference("keranjang")
                            .child(list.id)
                        ref.child("status_diterima").setValue(true).addOnCompleteListener {
                            Toast.makeText(context, "Barang telah diterima", Toast.LENGTH_SHORT).show()
                            dialog.dismiss()
                        }
                    }
                    dialog.show()
                }
                else{
                    val intent = Intent(context, lihat_barang_pembeli::class.java)
                    intent.putExtra("id", list.id_barang)
                    intent.putExtra("set", "detail")
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                    context.startActivity(intent)
                }
            }
        }

        override fun onClick(v: View?) {
        }

    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(List[position], context)
    }

    private fun kalkulasibintang(idbarang: String){
        val ref = FirebaseDatabase.getInstance().getReference("ulasan").orderByChild("id_barang").equalTo(idbarang)
        ref.addValueEventListener(object : ValueEventListener{
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
}