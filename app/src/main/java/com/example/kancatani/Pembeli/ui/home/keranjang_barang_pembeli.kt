package com.example.kancatani.Pembeli.ui.home

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Toast
import com.example.kancatani.Model.BarangModel
import com.example.kancatani.Model.PesananModel
import com.example.kancatani.Pembeli.HomePembeli
import com.example.kancatani.R
import com.example.kancatani.SharePreference.Sharepreference
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_keranjang_barang_pembeli.*

class keranjang_barang_pembeli : AppCompatActivity() {

    lateinit var SP: Sharepreference
    lateinit var pengirim: ArrayList<String>
    lateinit var adapter: ArrayAdapter<*>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_keranjang_barang_pembeli)

        SP = Sharepreference()
        pengirim = arrayListOf()
        loading.visibility = View.VISIBLE

        load(intent.getStringExtra("id").toString())
        adapter = ArrayAdapter(this,
            R.layout.support_simple_spinner_dropdown_item, pengirim)

        pengiriman.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                if(pengirim[position] == "Antar Kerumah"){
                    ongkir.visibility = View.VISIBLE
                    total.setText((jumlahbarang.text.toString().toInt() * harga.text.toString().toInt() + subtotalppengiriman.text.toString().toInt()).toString())
                }
                else{
                    ongkir.visibility = View.INVISIBLE
                    total.setText((jumlahbarang.text.toString().toInt() * harga.text.toString().toInt() + subtotalppengiriman.text.toString().toInt()).toString())
                }
                SP.createSP(this@keranjang_barang_pembeli, "jasapengiriman", pengirim[position])
            }
        }

        val alamat = SP.loadSP(this, "alamat") + ", " + SP.loadSP(this, "kecamatan") + ", " +
                SP.loadSP(this, "kota") + ", " + SP.loadSP(this, "provinsi")
        alamatpengiriman.setText(alamat)

        metodebayar.setOnClickListener {
            val dialog = AlertDialog.Builder(this)
            val inflater = LayoutInflater.from(this)
            val vieww = inflater.inflate(R.layout.metode_pembayaran, null)
            val saldo = vieww.findViewById<Button>(R.id.dompet)
            saldo.setOnClickListener {
                Toast.makeText(this, "Maaf fitur belum tersedia", Toast.LENGTH_SHORT).show()
            }
            dialog.setView(vieww)
                .setCancelable(false)
                .setNegativeButton("Simpan", object : DialogInterface.OnClickListener{
                    override fun onClick(p0: DialogInterface?, p1: Int) {
                        p0?.cancel()
                    }
                })
                .create().show()
        }

        plus.setOnClickListener {
            jumlahbarang.setText((jumlahbarang.text.toString().toInt()+1).toString())
            total.setText((jumlahbarang.text.toString().toInt() * harga.text.toString().toInt() + subtotalppengiriman.text.toString().toInt()).toString())
        }
        min.setOnClickListener {
            val jum = jumlahbarang.text.toString()
            if(jum != "1"){
                jumlahbarang.setText((jum.toInt() - 1).toString())
                total.setText((jumlahbarang.text.toString().toInt() * harga.text.toString().toInt() + subtotalppengiriman.text.toString().toInt()).toString())
            }
        }

        buatpesanan.setOnClickListener {
            buatpesanan()
        }

    }

    private fun load(id: String){
        val ref = FirebaseDatabase.getInstance().getReference("barang")
            .orderByChild("id").equalTo(id)
        ref.addValueEventListener(object : ValueEventListener{
            override fun onCancelled(p0: DatabaseError) {
            }

            override fun onDataChange(p0: DataSnapshot) {
                pengirim.clear()
                if(p0.exists()){
                    p0.children.forEach {
                        val value = it.getValue(BarangModel::class.java)
                        if(value != null){
                            Picasso.get().load(value.fotobarang).into(fotobarang)
                            SP.createSP(this@keranjang_barang_pembeli, "fotobarang", value.fotobarang)
                            namabarang.setText(value.nama)
                            harga.setText(value.harga.toString())
                            subtotalproduk.setText(value.harga.toString())
                            pengirim.addAll(value.jasapengiriman)
                            total.setText(value.harga.toString())
                            subtotalppengiriman.setText(value.ongkir.toString())
                            loading.visibility = View.GONE
                        }
                    }
                    pengiriman.adapter = adapter
                    adapter.notifyDataSetChanged()
                }
            }
        })
    }

    private fun buatpesanan(){
        val ref = FirebaseDatabase.getInstance().getReference("keranjang")
        val id = ref.push().key.toString()

        var pesan = pesan.text.toString()
        if(pesan.isEmpty()){
            pesan = "-"
        }
        val value = PesananModel(id, intent.getStringExtra("id").toString(),
            SP.loadSP(this, "id"), SP.loadSP(this, "fotobarang"),
            namabarang.text.toString(), harga.text.toString().toInt(), SP.loadSP(this, "jasapengirim"),
            jumlahbarang.text.toString().toInt(), pesan, "Tunai", total.text.toString().toInt(), "menunggu")

        ref.child(id).setValue(value).addOnCompleteListener {
            Toast.makeText(this, "Pesanan Dibuat", Toast.LENGTH_SHORT).show()
            startActivity(Intent(this, HomePembeli::class.java))
        }
    }
}
