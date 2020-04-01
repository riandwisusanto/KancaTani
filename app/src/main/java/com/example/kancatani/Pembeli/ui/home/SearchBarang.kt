package com.example.kancatani.Pembeli.ui.home

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.widget.*
import androidx.recyclerview.widget.GridLayoutManager
import com.example.kancatani.Adapter.BarangAdapter
import com.example.kancatani.Model.BarangModel
import com.example.kancatani.Model.db_alamat
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
    lateinit var cari: String
    lateinit var clik: String
    var harga_btn = false
    lateinit var SP: Sharepreference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_barang)

        SP = Sharepreference()
        listbarang = arrayListOf()
        clik = "bintang"
        cari = ""
        adapter = BarangAdapter(applicationContext, listbarang)
        list_search.layoutManager = GridLayoutManager(this, 2)
        loading.visibility = View.VISIBLE
        loadbarang(cari,clik,0,999999,5,"x","x","x","x","x")

        btn_populer.setOnClickListener {
            loading.visibility = View.VISIBLE
            clik = "bintang"
            loadbarang(cari,clik,0,999999,5,"x","x","x","x","x")
        }

        btn_harga.setOnClickListener {
            loading.visibility = View.VISIBLE
            clik = "harga"
            loadbarang(cari,clik,0,999999,5,"x","x","x","x","x")
        }

        text_search1.addTextChangedListener(object : TextWatcher{
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                cari = text_search1.text.toString().trim()
                loadbarang(cari,clik,0,999999,5,"x","x","x","x","x")
            }

        })

        btn_filter.setOnClickListener {
            filter()
        }

        btn_back.setOnClickListener {
            val intent = Intent(applicationContext, HomePembeli::class.java)
            startActivity(intent)
        }

    }

    private fun loadbarang(cari: String ,pilih: String, min: Int, max: Int, rating: Int, kondisi: String, kategori: String, provinsi: String,
                            kota: String, kecamatan: String){
        val ref = FirebaseDatabase.getInstance().getReference("barang")
            .orderByChild(pilih)
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
                                if(value.harga >= min && value.harga <= max){
                                    if(value.bintang.toFloat() <= rating){
                                        if(kondisi != "x"){
                                            if(value.kondisi == kondisi){
                                                if(kategori != "x"){
                                                    if(value.kategori == kategori){
                                                        if(provinsi != "x"){
                                                            if(value.provinsi == provinsi){
                                                                if(kota != "x"){
                                                                    if(value.kota == kota){
                                                                        if(kecamatan != "x"){
                                                                            if(value.kecamatan == kecamatan){
                                                                                listbarang.add(value)
                                                                            }
                                                                        }
                                                                        else{
                                                                            listbarang.add(value)
                                                                        }
                                                                    }
                                                                }
                                                                else{
                                                                    if(kecamatan != "x"){
                                                                        if(value.kecamatan == kecamatan){
                                                                            listbarang.add(value)
                                                                        }
                                                                    }
                                                                    else{
                                                                        listbarang.add(value)
                                                                    }
                                                                }
                                                            }
                                                        }
                                                        else{
                                                            if(kota != "x"){
                                                                if(value.kota == kota){
                                                                    if(kecamatan != "x"){
                                                                        if(value.kecamatan == kecamatan){
                                                                            listbarang.add(value)
                                                                        }
                                                                    }
                                                                    else{
                                                                        listbarang.add(value)
                                                                    }
                                                                }
                                                            }
                                                            else{
                                                                if(kecamatan != "x"){
                                                                    if(value.kecamatan == kecamatan){
                                                                        listbarang.add(value)
                                                                    }
                                                                }
                                                                else{
                                                                    listbarang.add(value)
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                                else{
                                                    if(provinsi != "x"){
                                                        if(value.provinsi == provinsi){
                                                            if(kota != "x"){
                                                                if(value.kota == kota){
                                                                    if(kecamatan != "x"){
                                                                        if(value.kecamatan == kecamatan){
                                                                            listbarang.add(value)
                                                                        }
                                                                    }
                                                                    else{
                                                                        listbarang.add(value)
                                                                    }
                                                                }
                                                            }
                                                            else{
                                                                if(kecamatan != "x"){
                                                                    if(value.kecamatan == kecamatan){
                                                                        listbarang.add(value)
                                                                    }
                                                                }
                                                                else{
                                                                    listbarang.add(value)
                                                                }
                                                            }
                                                        }
                                                    }
                                                    else{
                                                        if(kota != "x"){
                                                            if(value.kota == kota){
                                                                if(kecamatan != "x"){
                                                                    if(value.kecamatan == kecamatan){
                                                                        listbarang.add(value)
                                                                    }
                                                                }
                                                                else{
                                                                    listbarang.add(value)
                                                                }
                                                            }
                                                        }
                                                        else{
                                                            if(kecamatan != "x"){
                                                                if(value.kecamatan == kecamatan){
                                                                    listbarang.add(value)
                                                                }
                                                            }
                                                            else{
                                                                listbarang.add(value)
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                        else{
                                            if(kategori != "x"){
                                                if(value.kategori == kategori){
                                                    if(provinsi != "x"){
                                                        if(value.provinsi == provinsi){
                                                            if(kota != "x"){
                                                                if(value.kota == kota){
                                                                    if(kecamatan != "x"){
                                                                        if(value.kecamatan == kecamatan){
                                                                            listbarang.add(value)
                                                                        }
                                                                    }
                                                                    else{
                                                                        listbarang.add(value)
                                                                    }
                                                                }
                                                            }
                                                            else{
                                                                if(kecamatan != "x"){
                                                                    if(value.kecamatan == kecamatan){
                                                                        listbarang.add(value)
                                                                    }
                                                                }
                                                                else{
                                                                    listbarang.add(value)
                                                                }
                                                            }
                                                        }
                                                    }
                                                    else{
                                                        if(kota != "x"){
                                                            if(value.kota == kota){
                                                                if(kecamatan != "x"){
                                                                    if(value.kecamatan == kecamatan){
                                                                        listbarang.add(value)
                                                                    }
                                                                }
                                                                else{
                                                                    listbarang.add(value)
                                                                }
                                                            }
                                                        }
                                                        else{
                                                            if(kecamatan != "x"){
                                                                if(value.kecamatan == kecamatan){
                                                                    listbarang.add(value)
                                                                }
                                                            }
                                                            else{
                                                                listbarang.add(value)
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                            else{
                                                if(provinsi != "x"){
                                                    if(value.provinsi == provinsi){
                                                        if(kota != "x"){
                                                            if(value.kota == kota){
                                                                if(kecamatan != "x"){
                                                                    if(value.kecamatan == kecamatan){
                                                                        listbarang.add(value)
                                                                    }
                                                                }
                                                                else{
                                                                    listbarang.add(value)
                                                                }
                                                            }
                                                        }
                                                        else{
                                                            if(kecamatan != "x"){
                                                                if(value.kecamatan == kecamatan){
                                                                    listbarang.add(value)
                                                                }
                                                            }
                                                            else{
                                                                listbarang.add(value)
                                                            }
                                                        }
                                                    }
                                                }
                                                else{
                                                    if(kota != "x"){
                                                        if(value.kota == kota){
                                                            if(kecamatan != "x"){
                                                                if(value.kecamatan == kecamatan){
                                                                    listbarang.add(value)
                                                                }
                                                            }
                                                            else{
                                                                listbarang.add(value)
                                                            }
                                                        }
                                                    }
                                                    else{
                                                        if(kecamatan != "x"){
                                                            if(value.kecamatan == kecamatan){
                                                                listbarang.add(value)
                                                            }
                                                        }
                                                        else{
                                                            listbarang.add(value)
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                            else{
                                if(cari.contains(value.nama.trim())){
                                    if(value.harga >= min && value.harga <= max){
                                        if(value.bintang.toFloat() <= rating){
                                            if(kondisi != "x"){
                                                if(value.kondisi == kondisi){
                                                    if(kategori != "x"){
                                                        if(value.kategori == kategori){
                                                            if(provinsi != "x"){
                                                                if(value.provinsi == provinsi){
                                                                    if(kota != "x"){
                                                                        if(value.kota == kota){
                                                                            if(kecamatan != "x"){
                                                                                if(value.kecamatan == kecamatan){
                                                                                    listbarang.add(value)
                                                                                }
                                                                            }
                                                                            else{
                                                                                listbarang.add(value)
                                                                            }
                                                                        }
                                                                    }
                                                                    else{
                                                                        if(kecamatan != "x"){
                                                                            if(value.kecamatan == kecamatan){
                                                                                listbarang.add(value)
                                                                            }
                                                                        }
                                                                        else{
                                                                            listbarang.add(value)
                                                                        }
                                                                    }
                                                                }
                                                            }
                                                            else{
                                                                if(kota != "x"){
                                                                    if(value.kota == kota){
                                                                        if(kecamatan != "x"){
                                                                            if(value.kecamatan == kecamatan){
                                                                                listbarang.add(value)
                                                                            }
                                                                        }
                                                                        else{
                                                                            listbarang.add(value)
                                                                        }
                                                                    }
                                                                }
                                                                else{
                                                                    if(kecamatan != "x"){
                                                                        if(value.kecamatan == kecamatan){
                                                                            listbarang.add(value)
                                                                        }
                                                                    }
                                                                    else{
                                                                        listbarang.add(value)
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                    else{
                                                        if(provinsi != "x"){
                                                            if(value.provinsi == provinsi){
                                                                if(kota != "x"){
                                                                    if(value.kota == kota){
                                                                        if(kecamatan != "x"){
                                                                            if(value.kecamatan == kecamatan){
                                                                                listbarang.add(value)
                                                                            }
                                                                        }
                                                                        else{
                                                                            listbarang.add(value)
                                                                        }
                                                                    }
                                                                }
                                                                else{
                                                                    if(kecamatan != "x"){
                                                                        if(value.kecamatan == kecamatan){
                                                                            listbarang.add(value)
                                                                        }
                                                                    }
                                                                    else{
                                                                        listbarang.add(value)
                                                                    }
                                                                }
                                                            }
                                                        }
                                                        else{
                                                            if(kota != "x"){
                                                                if(value.kota == kota){
                                                                    if(kecamatan != "x"){
                                                                        if(value.kecamatan == kecamatan){
                                                                            listbarang.add(value)
                                                                        }
                                                                    }
                                                                    else{
                                                                        listbarang.add(value)
                                                                    }
                                                                }
                                                            }
                                                            else{
                                                                if(kecamatan != "x"){
                                                                    if(value.kecamatan == kecamatan){
                                                                        listbarang.add(value)
                                                                    }
                                                                }
                                                                else{
                                                                    listbarang.add(value)
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                            else{
                                                if(kategori != "x"){
                                                    if(value.kategori == kategori){
                                                        if(provinsi != "x"){
                                                            if(value.provinsi == provinsi){
                                                                if(kota != "x"){
                                                                    if(value.kota == kota){
                                                                        if(kecamatan != "x"){
                                                                            if(value.kecamatan == kecamatan){
                                                                                listbarang.add(value)
                                                                            }
                                                                        }
                                                                        else{
                                                                            listbarang.add(value)
                                                                        }
                                                                    }
                                                                }
                                                                else{
                                                                    if(kecamatan != "x"){
                                                                        if(value.kecamatan == kecamatan){
                                                                            listbarang.add(value)
                                                                        }
                                                                    }
                                                                    else{
                                                                        listbarang.add(value)
                                                                    }
                                                                }
                                                            }
                                                        }
                                                        else{
                                                            if(kota != "x"){
                                                                if(value.kota == kota){
                                                                    if(kecamatan != "x"){
                                                                        if(value.kecamatan == kecamatan){
                                                                            listbarang.add(value)
                                                                        }
                                                                    }
                                                                    else{
                                                                        listbarang.add(value)
                                                                    }
                                                                }
                                                            }
                                                            else{
                                                                if(kecamatan != "x"){
                                                                    if(value.kecamatan == kecamatan){
                                                                        listbarang.add(value)
                                                                    }
                                                                }
                                                                else{
                                                                    listbarang.add(value)
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                                else{
                                                    if(provinsi != "x"){
                                                        if(value.provinsi == provinsi){
                                                            if(kota != "x"){
                                                                if(value.kota == kota){
                                                                    if(kecamatan != "x"){
                                                                        if(value.kecamatan == kecamatan){
                                                                            listbarang.add(value)
                                                                        }
                                                                    }
                                                                    else{
                                                                        listbarang.add(value)
                                                                    }
                                                                }
                                                            }
                                                            else{
                                                                if(kecamatan != "x"){
                                                                    if(value.kecamatan == kecamatan){
                                                                        listbarang.add(value)
                                                                    }
                                                                }
                                                                else{
                                                                    listbarang.add(value)
                                                                }
                                                            }
                                                        }
                                                    }
                                                    else{
                                                        if(kota != "x"){
                                                            if(value.kota == kota){
                                                                if(kecamatan != "x"){
                                                                    if(value.kecamatan == kecamatan){
                                                                        listbarang.add(value)
                                                                    }
                                                                }
                                                                else{
                                                                    listbarang.add(value)
                                                                }
                                                            }
                                                        }
                                                        else{
                                                            if(kecamatan != "x"){
                                                                if(value.kecamatan == kecamatan){
                                                                    listbarang.add(value)
                                                                }
                                                            }
                                                            else{
                                                                listbarang.add(value)
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                    if(pilih == "harga"){
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

    private fun filter(){
        val dialog = AlertDialog.Builder(this)
        val inflater = LayoutInflater.from(this)
        val view = inflater.inflate(R.layout.filter_barang, null)

        var kondisii = "x"
        var kategorit = "x"
        var ratingg = ""
        val min = view.findViewById<EditText>(R.id.minharga)
        val max = view.findViewById<EditText>(R.id.maxharga)
        val kondisi = view.findViewById<RadioGroup>(R.id.kondisi)
        val kategori = view.findViewById<Spinner>(R.id.kategori)
        val rating = view.findViewById<RadioGroup>(R.id.rating)
        val pilih_provinsi = view.findViewById<Spinner>(R.id.pilih_provinsi)
        val pilih_kota = view.findViewById<Spinner>(R.id.pilih_kota)
        val pilih_kecamatan = view.findViewById<Spinner>(R.id.pilih_kecamatan)

        kondisi.setOnCheckedChangeListener(
            RadioGroup.OnCheckedChangeListener { group, checkedId ->
                val radio: RadioButton = view.findViewById(checkedId)
                kondisii = radio.text.toString()
            })

        val arraykategori = arrayOf("x","Benih", "Obat", "Alat","Pupuk")
        val adapterkategori = ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, arraykategori)

        kategori.adapter = adapterkategori
        kategori.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                kategorit = arraykategori[position]
            }
        }

        rating.setOnCheckedChangeListener(
            RadioGroup.OnCheckedChangeListener { group, checkedId ->
                val radio: RadioButton = view.findViewById(checkedId)
                ratingg = radio.text.toString().substring(2,3)
            })

        val dbAlamat = db_alamat
        val provinsi = dbAlamat.provinsi
        var kota : ArrayList<String> = arrayListOf()
        val kecamatan : ArrayList<String> = arrayListOf()

        var Provinsi = "x"
        var Kota = "x"
        var Kecamatan = "x"

        val adapter_prov = ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, provinsi)
        val adapter_kota = ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, kota)
        val adapter_kecamatan = ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, kecamatan)

        //set provinsi
        pilih_provinsi.adapter = adapter_prov

        pilih_provinsi.onItemSelectedListener = object :
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
                pilih_kota.adapter = adapter_kota
                Provinsi = provinsi[position]
            }
        }

        pilih_kota.onItemSelectedListener = object :
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
                pilih_kecamatan.adapter = adapter_kecamatan
                Kota = kota[position]
            }
        }

        pilih_kecamatan.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                Kecamatan = kecamatan[position]
            }

        }

        dialog.setView(view)
            .setCancelable(true)
            .setPositiveButton("Terapkan", object: DialogInterface.OnClickListener{
                override fun onClick(p0: DialogInterface?, p1: Int) {
                    var minx = min.text.toString()
                    var maxx = max.text.toString()
                    if(min.text.isEmpty()){
                        minx = "0"
                    }
                    if(max.text.isEmpty()){
                        maxx = "99999999"
                    }
                    if(ratingg.isEmpty()){
                        ratingg = "5"
                    }
                    loading.visibility = View.VISIBLE
                    loadbarang(cari, clik, minx.toInt(), maxx.toInt(), ratingg.toInt(), kondisii, kategorit, Provinsi, Kota, Kecamatan)
                    p0?.cancel()
                }

            })
            .setNegativeButton("Batal", object : DialogInterface.OnClickListener{
                override fun onClick(p0: DialogInterface?, p1: Int) {
                    p0?.cancel()
                }
            })
            .create().show()
    }
}
