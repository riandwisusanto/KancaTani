package com.example.kancatani.Pembeli.ui.home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import com.example.kancatani.Adapter.BarangAdapter
import com.example.kancatani.Model.BarangModel
import com.example.kancatani.Pembeli.HomePembeli
import com.example.kancatani.R
import com.example.kancatani.SharePreference.Sharepreference
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_search_barang.*
import java.util.*
import kotlin.collections.ArrayList

class SearchBarang : AppCompatActivity() {

    lateinit var listbarang: ArrayList<BarangModel>
    lateinit var adapter: BarangAdapter
    var harga_btn = false
    lateinit var SP: Sharepreference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_barang)

        SP = Sharepreference()
        listbarang = arrayListOf()
        var clik = "bintang"
        var cari = ""
        adapter = BarangAdapter(applicationContext, listbarang)
        list_search.layoutManager = GridLayoutManager(this, 2)
        loading.visibility = View.VISIBLE
        loadbarang(cari,clik)

        btn_populer.setOnClickListener {
            loading.visibility = View.VISIBLE
            clik = "bintang"
            loadbarang(cari,clik)
        }

        btn_harga.setOnClickListener {
            loading.visibility = View.VISIBLE
            clik = "harga"
            loadbarang(cari,clik)
        }

        text_search1.addTextChangedListener(object : TextWatcher{
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                cari = text_search1.text.toString().trim()
                loadbarang(cari , clik)
            }

        })

        btn_back.setOnClickListener {
            val intent = Intent(applicationContext, HomePembeli::class.java)
            startActivity(intent)
        }

    }

    private fun loadbarang(cari: String ,string: String){
        val ref = FirebaseDatabase.getInstance().getReference("barang")
            .orderByChild(string)
        ref.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {

            }

            override fun onDataChange(p0: DataSnapshot) {
                listbarang.clear()
                if(p0.exists()){
                    p0.children.forEach {
                        val value = it.getValue(BarangModel::class.java)
                        if(value != null){
                            if(cari.isEmpty()){
                                listbarang.add(value)
                            }
                            else{
                                if(cari.contains(value.nama.trim())){
                                    listbarang.add(value)
                                }
                            }
                        }
                    }
                    if(string == "harga"){
                        if(harga_btn){
                            harga_btn = false
                        }
                        else{
                            Collections.reverse(listbarang)
                            harga_btn = true
                        }
                    }
                    list_search.adapter = adapter
                    adapter.notifyDataSetChanged()
                    loading.visibility = View.GONE
                }
            }

        })
    }
}
