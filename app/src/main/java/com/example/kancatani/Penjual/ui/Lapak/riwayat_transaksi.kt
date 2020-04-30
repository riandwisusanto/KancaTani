package com.example.kancatani.Penjual.ui.Lapak

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kancatani.Adapter.RiwayatAdapter
import com.example.kancatani.Model.PesananModel
import com.example.kancatani.R
import com.example.kancatani.SharePreference.Sharepreference
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.riwayat_pembelian_keranjang.*

class riwayat_transaksi : AppCompatActivity()  {

    lateinit var SP: Sharepreference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.riwayat_pembelian_keranjang)
        SP = Sharepreference()

        val list = findViewById<RecyclerView>(R.id.listriwayat)
        val listbar = arrayListOf<PesananModel>()
        list.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        val adap = RiwayatAdapter(this, listbar)

        val ref = FirebaseDatabase.getInstance().getReference("keranjang")
            .orderByChild("id_penjual").equalTo(SP.loadSP(this, "id"))
        ref.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
            }

            override fun onDataChange(p0: DataSnapshot) {
                listbar.clear()
                if(p0.exists()){
                    p0.children.forEach {
                        val value = it.getValue(PesananModel::class.java)
                        if(value != null){
                            if(value.status_diterima == true){
                                listbar.add(value)
                            }
                        }
                    }
                    list.adapter = adap
                    adap.notifyDataSetChanged()
                }
            }
        })

        btn_back.setOnClickListener {
            finish()
        }
    }
}