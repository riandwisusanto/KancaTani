package com.example.kancatani.Pembeli.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.denzcoskun.imageslider.ImageSlider
import com.denzcoskun.imageslider.models.SlideModel
import com.example.kancatani.Adapter.BarangAdapter
import com.example.kancatani.Model.BarangModel
import com.example.kancatani.Model.UserModel
import com.example.kancatani.R
import com.example.kancatani.SharePreference.Sharepreference
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class HomeFragment : Fragment() {

    lateinit var SP: Sharepreference
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        SP = Sharepreference()
        val lokasi =  view.findViewById<TextView>(R.id.lokasitxt)
        loadlok(lokasi)
        val isBerita = view.findViewById<ImageSlider>(R.id.berita_imageslider)
        val slideModel  = ArrayList<SlideModel>()
        slideModel.add(SlideModel(R.drawable.liquid,true))
        slideModel.add(SlideModel(R.drawable.jugg,true))
        slideModel.add(SlideModel(R.drawable.liquid,true))
        slideModel.add(SlideModel(R.drawable.jugg,true))
        isBerita.setImageList(slideModel,true)

        val cari = view.findViewById<SearchView>(R.id.btcari)
        cari.setOnClickListener {
            val intent = Intent(this.context, SearchBarang::class.java)
            startActivity(intent)
        }

        return view
    }

    private fun loadlok(textView: TextView){
        val ref = FirebaseDatabase.getInstance().getReference("pengguna")
            .orderByChild("id").equalTo(SP.loadSP(context!!.applicationContext, "id"))
        ref.addValueEventListener(object : ValueEventListener{
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
}
