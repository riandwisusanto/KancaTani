package com.example.kancatani

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kancatani.Adapter.BarangAdapter
import com.example.kancatani.Model.BarangModel
import com.example.kancatani.Model.UserModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_etalase_penjual.*

class Etalase_penjual : AppCompatActivity() {

    lateinit var list: ArrayList<BarangModel>
    lateinit var adapter: BarangAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_etalase_penjual)

        val id = intent.getStringExtra("id").toString()

        load(id)

        println("id = " + id)

        list = arrayListOf()
        etalase.layoutManager = GridLayoutManager(this, 2)
        adapter = BarangAdapter(this, list)
        loadbarang(id)
    }

    private fun loadbarang(id: String){
        val ref = FirebaseDatabase.getInstance().getReference("barang")
            .orderByChild("id_lapak").equalTo(id)
        ref.addValueEventListener(object : ValueEventListener{
            override fun onCancelled(p0: DatabaseError) {
            }

            override fun onDataChange(p0: DataSnapshot) {
                list.clear()
                if(p0.exists()){
                    p0.children.forEach{
                        val value = it.getValue(BarangModel::class.java)
                        if(value != null){
                            list.add(value)
                        }
                    }
                    etalase.adapter = adapter
                    adapter.notifyDataSetChanged()
                    loading.visibility = View.GONE
                }
            }
        })
    }

    private fun load(id: String){
        val ref = FirebaseDatabase.getInstance().getReference("pengguna")
            .child(id)
        ref.addListenerForSingleValueEvent(object : ValueEventListener{
            override fun onCancelled(p0: DatabaseError) {

            }

            override fun onDataChange(p0: DataSnapshot) {
                if(p0.exists()){
                    val value = p0.getValue(UserModel::class.java)
                    Picasso.get().load(value!!.foto).into(fototoko)
                    namatoko.setText(value.nama)
                    lokasi.setText(value.alamat)
                }
            }

        })
    }
}
