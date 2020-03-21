package com.example.kancatani.Home.ui.profile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.example.kancatani.R
import com.example.kancatani.SharePreference.Sharepreference
import kotlinx.android.synthetic.main.activity_edit_alamat.*

class EditAlamat : AppCompatActivity() {

    lateinit var provinsi: Array<String>
    lateinit var kota: ArrayList<String>
    lateinit var kecamatan: ArrayList<String>
    lateinit var SP: Sharepreference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_alamat)

        SP = Sharepreference()
        val prov = SP.loadSP(this, "provinsi")
        val kot = SP.loadSP(this, "kota")
        val kec = SP.loadSP(this, "kecamatan")

        val dbAlamat = db_alamat
        provinsi = dbAlamat.provinsi
        kota = arrayListOf()
        kecamatan = arrayListOf()

        val adapter_prov = ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, provinsi)
        val adapter_kota = ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, kota)
        val adapter_kecamatan = ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, kecamatan)

        //set provinsi
        form_provinsi.adapter = adapter_prov
        form_kota.adapter = adapter_kota

        for(i in 0..(form_provinsi.count - 1)){
            if(provinsi[i] == prov){
                form_provinsi.setSelection(i)
            }
        }
        form_provinsi.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {
                kota = arrayListOf()
            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                kota.clear()
                dbAlamat.kota.forEach {
                    if(it.id == provinsi[position]){
                        kota.addAll(it.value)
                    }
                }
                form_kota.adapter = adapter_kota
            }
        }

        form_kota.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                kecamatan.clear()
                dbAlamat.kecamatan.forEach {
                    if(it.id == kota[position]){
                        kecamatan.addAll(it.value)
                    }
                }
                form_kecamatan.adapter = adapter_kecamatan
            }

        }
    }
}
