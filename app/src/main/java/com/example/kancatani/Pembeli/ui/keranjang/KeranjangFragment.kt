package com.example.kancatani.Pembeli.ui.keranjang

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kancatani.Adapter.KeranjangAdapter
import com.example.kancatani.Adapter.RiwayatAdapter
import com.example.kancatani.Model.PesananModel
import com.example.kancatani.Penjual.ui.Lapak.riwayat_transaksi
import com.example.kancatani.R
import com.example.kancatani.SharePreference.Sharepreference
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_keranjang.*

class KeranjangFragment : Fragment() {

    lateinit var SP: Sharepreference
    lateinit var listkerangjang: ArrayList<PesananModel>
    lateinit var adapter: KeranjangAdapter
    lateinit var listkeranjangx: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_keranjang, container, false)
        SP = Sharepreference()

        //load
        listkeranjangx = root.findViewById(R.id.listkeranjang)
        listkerangjang = arrayListOf()
        loadkeranjang()
        listkeranjangx.layoutManager = LinearLayoutManager(this.context, LinearLayoutManager.VERTICAL, false)
        adapter = KeranjangAdapter(this.context!!, listkerangjang)

        val riwayat = root.findViewById<Button>(R.id.riwayat)
        riwayat.setOnClickListener {
            startActivity(Intent(context?.applicationContext, riwayat_transaksi::class.java))
        }

        return root
    }

    private fun loadkeranjang(){
        val ref = FirebaseDatabase.getInstance().getReference("keranjang")
            .orderByChild("id_pembeli").equalTo(SP.loadSP(context!!.applicationContext, "id"))
        ref.addValueEventListener(object : ValueEventListener{
            override fun onCancelled(p0: DatabaseError) {

            }

            override fun onDataChange(p0: DataSnapshot) {
                listkerangjang.clear()
                if(p0.exists()){
                    p0.children.forEach {
                        val value = it.getValue(PesananModel::class.java)
                        if(value != null){
                            if(value.status_diterima == false){
                                listkerangjang.add(value)
                            }
                        }
                    }
                    listkeranjangx.adapter = adapter
                    adapter.notifyDataSetChanged()
                }
            }

        })
    }

    private fun riwayat(){
        val dialog = AlertDialog.Builder(this.context)
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.riwayat_pembelian_keranjang, null)

        val list = view.findViewById<RecyclerView>(R.id.listriwayat)
        val listbar = arrayListOf<PesananModel>()
        list.layoutManager = LinearLayoutManager(this.context, LinearLayoutManager.VERTICAL, false)
        val adap = RiwayatAdapter(this.context!!, listbar)

        val ref = FirebaseDatabase.getInstance().getReference("keranjang")
            .orderByChild("id_pembeli").equalTo(SP.loadSP(this.context!!, "id"))
        ref.addValueEventListener(object : ValueEventListener{
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
        dialog.setView(view)
            .setCancelable(true)
            .create().show()
    }

}