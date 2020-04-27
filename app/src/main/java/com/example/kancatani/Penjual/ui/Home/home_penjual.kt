package com.example.kancatani.Penjual.ui.Home

import android.content.Intent
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.denzcoskun.imageslider.ImageSlider
import com.denzcoskun.imageslider.models.SlideModel
import com.example.kancatani.Adapter.PesananAdapter
import com.example.kancatani.Chat.ChatActivity
import com.example.kancatani.Model.IklanModel
import com.example.kancatani.Model.PesananModel
import com.example.kancatani.Model.UserModel
import com.example.kancatani.R
import com.example.kancatani.SharePreference.Sharepreference
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener


class home_penjual : Fragment() {

    lateinit var SP: Sharepreference
    lateinit var list: ArrayList<PesananModel>
    lateinit var adapter: PesananAdapter
    lateinit var listpesanan: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.home_penjual_fragment, container, false)
        SP = Sharepreference()

        list = arrayListOf()
        val lokasi =  view.findViewById<TextView>(R.id.lokasitxt)
        val pesan = view.findViewById<ImageButton>(R.id.btpesan)
        listpesanan = view.findViewById<RecyclerView>(R.id.pesananmasuk)
        listpesanan.setHasFixedSize(true)
        listpesanan.setLayoutManager(LinearLayoutManager(context))
        adapter = PesananAdapter(this.context!!, list)
        loadpesanan()

        loadlok(lokasi)

        val isBerita = view.findViewById<ImageSlider>(R.id.berita_imageslider)
        val slideModel  = ArrayList<SlideModel>()
        val refbarang = FirebaseDatabase.getInstance().getReference("iklan_penjual")
        refbarang.addValueEventListener(object : ValueEventListener{
            override fun onCancelled(p0: DatabaseError) {

            }

            override fun onDataChange(p0: DataSnapshot) {
                if(p0.exists()){
                    p0.children.forEach{
                        val value = it.getValue(IklanModel::class.java)
                        slideModel.add(SlideModel(value!!.foto, true))
                    }
                    isBerita.setImageList(slideModel,true)
                }
            }

        })

        pesan.setOnClickListener {
            val intent = Intent(this.context, ChatActivity::class.java)
            startActivity(intent)
        }
        return view
    }

    private fun loadlok(textView: TextView){
        val ref = FirebaseDatabase.getInstance().getReference("pengguna")
            .orderByChild("id").equalTo(SP.loadSP(context!!.applicationContext, "id"))
        ref.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {

            }

            override fun onDataChange(p0: DataSnapshot) {
                if(p0.exists()){
                    p0.children.forEach{
                        val vale = it.getValue(UserModel::class.java)
                        if(vale!=null){
                            textView.setText(vale.kecamatan)
                        }
                    }
                }
            }

        })
    }

    private fun loadpesanan(){
        val ref = FirebaseDatabase.getInstance().getReference("keranjang").orderByChild("id_penjual").equalTo(SP.loadSP(
            context!!.applicationContext, "id"))

        ref.addValueEventListener(object: ValueEventListener{
            override fun onCancelled(p0: DatabaseError) {

            }

            override fun onDataChange(p0: DataSnapshot) {
                list.clear()
                if(p0.exists()){
                    p0.children.forEach{
                        val value = it.getValue(PesananModel::class.java)
                        if(value != null){
                            if(value.status != "Ditolak"){
                                if(value.status_diterima == false){
                                    list.add(value)
                                }
                            }
                        }
                    }
                    listpesanan.adapter = adapter
                    adapter.notifyDataSetChanged()
                }
            }

        })

    }
}
