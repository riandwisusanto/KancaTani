package com.example.kancatani.Penjual.ui.Lapak

import android.app.Activity
import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kancatani.Adapter.BarangAdapter
import com.example.kancatani.Adapter.RiwayatAdapter
import com.example.kancatani.Model.BarangModel
import com.example.kancatani.Model.PesananModel
import com.example.kancatani.Model.UserModel
import com.example.kancatani.R
import com.example.kancatani.SharePreference.Sharepreference
import com.google.firebase.database.*
import com.google.firebase.storage.FirebaseStorage
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.tambah_barang.*
import java.io.ByteArrayOutputStream
import java.io.FileNotFoundException
import java.io.InputStream


class lapak_penjual : Fragment() {

    lateinit var adapter: BarangAdapter
    lateinit var list: ArrayList<BarangModel>
    lateinit var listbarang: RecyclerView
    private var fotoselected: Uri? = null
    val pengiriman : ArrayList<String> = arrayListOf()
    var kondisit = toString()
    var kategorit = toString()
    lateinit var loading : RelativeLayout

    lateinit var SP: Sharepreference
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.lapak_penjual_fragment, container, false)
        SP = Sharepreference()
        loading = root.findViewById(R.id.loading)
        loading.visibility = View.VISIBLE

        list = arrayListOf()
        listbarang = root.findViewById<RecyclerView>(R.id.barangdijual)
        listbarang.layoutManager = GridLayoutManager(this.context, 2)
        loadbarang("", false)

        val tambahbarang= root.findViewById<Button>(R.id.tambah_barang)
        tambahbarang.setOnClickListener {
            tambahbarang()
        }

        val cari = root.findViewById<EditText>(R.id.text_search1)
        cari.addTextChangedListener(object : TextWatcher{
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                loadbarang(cari.text.toString().trim(), true)
            }

        })

        val btncar = root.findViewById<ImageView>(R.id.btn_search1)
        btncar.setOnClickListener {
            if(cari.text.isNotEmpty()){
                loadbarang(cari.text.toString().trim(), true)
            }
        }

        val riwayat = root.findViewById<Button>(R.id.riwayat)
        riwayat.setOnClickListener {
            riwayat()
        }
        return root
    }

    private fun loadbarang(namaBarang: String, cari: Boolean){
        val ref = FirebaseDatabase.getInstance().getReference("barang")
            .orderByChild("id_lapak").equalTo(SP.loadSP(context!!.applicationContext, "id"))
        ref.addValueEventListener(object : ValueEventListener{
            override fun onCancelled(p0: DatabaseError) {

            }

            override fun onDataChange(p0: DataSnapshot) {
                list.clear()
                if(p0.exists()){
                    p0.children.forEach{
                        val value = it.getValue(BarangModel::class.java)
                        if(value != null){
                            if(cari){
                                if(value.nama.toLowerCase().contains(namaBarang.toLowerCase())){
                                    list.add(value)
                                }
                            }else{
                                list.add(value)
                            }
                            loading.visibility = View.GONE
                        }
                    }
                    adapter = BarangAdapter(context!!, list)
                    adapter.notifyDataSetChanged()
                    listbarang.adapter = adapter
                }
            }
        })
    }

    private fun tambahbarang(){
        val dialog = AlertDialog.Builder(this.context)
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.tambah_barang, null)

        var codt = false
        var antart = false
        var ambilt = false
        val namabarang = view.findViewById<TextView>(R.id.namabarang)
        val stok = view.findViewById<TextView>(R.id.stok)
        val harga = view.findViewById<TextView>(R.id.harga)
        val kondisi = view.findViewById<Spinner>(R.id.kondisi)
        val kategori = view.findViewById<Spinner>(R.id.kategori)
        val deskripsi = view.findViewById<TextView>(R.id.deskripsi)
        val cod = view.findViewById<Button>(R.id.cod)
        val antar = view.findViewById<Button>(R.id.antar)
        val ongkir = view.findViewById<EditText>(R.id.ongkir)
        val ambil = view.findViewById<Button>(R.id.ambil)
        val foto = view.findViewById<Button>(R.id.btn_fotobarang)

        cod.setOnClickListener {
            if(codt){
                cod.setBackgroundColor(resources.getColor(R.color.colorGreyMuda))
                pengiriman.remove(cod.text.toString())
                codt = false
            }
            else{
                cod.setBackgroundColor(resources.getColor(R.color.colorWhite))
                pengiriman.add(cod.text.toString())
                codt = true
            }
        }
        antar.setOnClickListener {
            if (antart) {
                antar.setBackgroundColor(resources.getColor(R.color.colorGreyMuda))
                pengiriman.remove(antar.text.toString())
                ongkir.visibility = View.GONE
                antart = false
            } else {
                antar.setBackgroundColor(resources.getColor(R.color.colorWhite))
                pengiriman.add(antar.text.toString())
                ongkir.visibility = View.VISIBLE
                antart = true
            }
        }
        ambil.setOnClickListener {
            if (ambilt) {
                ambil.setBackgroundColor(resources.getColor(R.color.colorGreyMuda))
                pengiriman.remove(ambil.text.toString())
                ambilt = false
            } else {
                ambil.setBackgroundColor(resources.getColor(R.color.colorWhite))
                pengiriman.add(ambil.text.toString())
                ambilt = true
            }
        }

        foto.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            startActivityForResult(intent, 1)
        }

        val arraykondisi = arrayOf("Baru", "Bekas")
        val adapterkondisi = ArrayAdapter(context!!.applicationContext, R.layout.support_simple_spinner_dropdown_item, arraykondisi)

        kondisi.adapter = adapterkondisi

        kondisi.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                kondisit = arraykondisi[position]
            }
        }

        val arraykategori = arrayOf("Pupuk Organik", "Pupuk Kimia", "Alat Pertanian","Benih Padi","Benih Jagung","Obat Kimia")
        val adapterkategori = ArrayAdapter(context!!.applicationContext, R.layout.support_simple_spinner_dropdown_item, arraykategori)

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

        dialog.setView(view)
            .setCancelable(false)
            .setPositiveButton("Simpan", object: DialogInterface.OnClickListener{
                override fun onClick(p1: DialogInterface?, p2: Int) {
                    val ref = FirebaseDatabase.getInstance().getReference("barang")
                    val id = ref.push().key.toString()
                    println("id = " + id)
                    var onkir = 0
                    if(ongkir.text.isNotEmpty()){
                        onkir = ongkir.text.toString().toInt()
                    }
                    uploadImageToFirebaseStorage(id, namabarang.text.toString()
                        , harga.text.toString(), deskripsi.text.toString(), stok.text.toString(), ref, onkir)
                    p1?.cancel()
                }
            })
            .setNegativeButton("Batal", object :DialogInterface.OnClickListener{
                override fun onClick(p0: DialogInterface?, p1: Int) {
                    p0?.cancel()
                }
            })
            .create().show()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1 && resultCode == Activity.RESULT_OK && data != null) {
            fotoselected = data.data
            println("fotoselected" + fotoselected.toString())
        }
    }

    private fun uploadImageToFirebaseStorage(id: String, namabarang: String, harga: String, deskripsi: String, stok: String, refe: DatabaseReference, ongkir: Int){
        val uid = SP.loadSP(context!!.applicationContext, "id")
        if(fotoselected == null){
            savetodatabase(id, namabarang, harga, deskripsi, stok, refe, "x", ongkir)
        }
        else{
            val ref = FirebaseStorage.getInstance().getReference("pengguna/$uid/$id/fotobarang")
            var imageStream: InputStream? = null
            try {
                imageStream = this.context!!.getContentResolver().openInputStream(
                    fotoselected!!
                )
            } catch (e: FileNotFoundException) {
                e.printStackTrace()
            }

            val bmp = BitmapFactory.decodeStream(imageStream)

            val stream = ByteArrayOutputStream()
            bmp.compress(Bitmap.CompressFormat.WEBP, 10, stream)
            val byteArray: ByteArray = stream.toByteArray()
            ref.putBytes(byteArray)
                .addOnSuccessListener {
                    ref.downloadUrl.addOnSuccessListener {
                        savetodatabase(id, namabarang, harga, deskripsi, stok, refe, it.toString(), ongkir)
                    }
                }
                .addOnFailureListener {
                    //Log.d(TAG, "Failed to upload image to storage: ${it.message}")
                }
        }
    }

    private fun savetodatabase(id: String, namabarang: String, harga: String, deskripsi: String, stok: String, ref: DatabaseReference, fotobar: String, ongkir: Int){
        val referens = FirebaseDatabase.getInstance().getReference("pengguna")
            .orderByChild("id").equalTo(SP.loadSP(context!!.applicationContext, "id"))
        referens.addValueEventListener(object : ValueEventListener{
            override fun onCancelled(p0: DatabaseError) {

            }

            override fun onDataChange(p0: DataSnapshot) {
                if(p0.exists()){
                    p0.children.forEach{
                        val vale = it.getValue(UserModel::class.java)
                        val value = BarangModel(id, SP.loadSP(context!!.applicationContext, "id")
                            , fotobar,
                            namabarang, harga.toInt(),
                            kategorit,  deskripsi, pengiriman , "0",
                            stok.toInt()
                            , kondisit , vale!!.provinsi, vale.kota, vale.kecamatan, 0, ongkir)
                        ref.child(id).setValue(value).addOnCompleteListener {
                            Toast.makeText(context, "Berhasil Ditambahkan", Toast.LENGTH_SHORT).show()
                        }
                    }
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
            .orderByChild("id_penjual").equalTo(SP.loadSP(this.context!!, "id"))
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
