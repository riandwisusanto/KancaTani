package com.example.kancatani.Penjual.ui.Lapak

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.kancatani.Model.BarangModel
import com.example.kancatani.Model.UserModel
import com.example.kancatani.R
import com.example.kancatani.SharePreference.Sharepreference
import com.google.firebase.database.*
import com.google.firebase.storage.FirebaseStorage
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.tambah_barang.*
import java.io.ByteArrayOutputStream
import java.io.FileNotFoundException
import java.io.InputStream

class edit_barang : AppCompatActivity() {

    lateinit var SP: Sharepreference
    val pengiriman : ArrayList<String> = arrayListOf()
    var kondisit = toString()
    var kategorit = toString()
    private var fotoselected: Uri? = null
    lateinit var arraykondisi: Array<String>
    lateinit var adapterkondisi: ArrayAdapter<String>
    lateinit var arraykategori: Array<String>
    lateinit var adapterkategori: ArrayAdapter<String>
    var codt = false
    var antart = false
    var ambilt = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.tambah_barang)

        SP = Sharepreference()

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

        fotobarang.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            startActivityForResult(intent, 1)
        }

        arraykondisi = arrayOf("Baru", "Bekas")
        adapterkondisi = ArrayAdapter(applicationContext, R.layout.support_simple_spinner_dropdown_item, arraykondisi)

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

        arraykategori = arrayOf("Pupuk Organik", "Pupuk Kimia", "Alat Pertanian","Benih Padi","Benih Jagung","Obat Kimia")
        adapterkategori = ArrayAdapter(applicationContext, R.layout.support_simple_spinner_dropdown_item, arraykategori)

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

        load(intent.getStringExtra("id").toString())

        simpan.visibility = View.VISIBLE
        simpan.setOnClickListener {
            uploadImageToFirebaseStorage(intent.getStringExtra("id").toString())
        }

        btn_back.setOnClickListener {
            finish()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1 && resultCode == Activity.RESULT_OK && data != null) {
            fotoselected = data.data

            val bitmap = MediaStore.Images.Media.getBitmap(contentResolver, fotoselected)

            fotobarang.setImageBitmap(bitmap)
        }
    }

    private fun uploadImageToFirebaseStorage(id: String){
        val uid = SP.loadSP(applicationContext, "id")
        if(fotoselected == null){
            savetodatabase(id, uid, "kosong")
        }
        else{
            val ref = FirebaseStorage.getInstance().getReference("pengguna/$uid/$id/fotobarang")
            var imageStream: InputStream? = null
            try {
                imageStream = getContentResolver().openInputStream(
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
                        savetodatabase(id, uid, it.toString())
                    }
                }
                .addOnFailureListener {
                    //Log.d(TAG, "Failed to upload image to storage: ${it.message}")
                }
        }
    }

    private fun savetodatabase(id: String, id_lapak: String, foto: String){
        val ref = FirebaseDatabase.getInstance().getReference("barang")
            .child(id)
        ref.addListenerForSingleValueEvent(object : ValueEventListener{
            override fun onCancelled(p0: DatabaseError) {

            }

            override fun onDataChange(p0: DataSnapshot) {
                if(p0.exists()){
                    val value = p0.getValue(BarangModel::class.java)
                    var fotox = foto
                    if(fotox == "kosong"){
                        fotox = value!!.fotobarang
                    }
                    var onkir = 0
                    if(ongkir.text.isNotEmpty()){
                        onkir = ongkir.text.toString().toInt()
                    }
                    val vl = BarangModel(id, id_lapak, fotox, namabarang.text.toString(), harga.text.toString().toInt(), kategorit,
                        deskripsi.text.toString(), pengiriman, value!!.bintang, stok.text.toString().toInt(), kondisit, value.provinsi,
                        value.kota, value.kecamatan, value.terjual, onkir)

                    ref.setValue(vl).addOnCompleteListener {
                        Toast.makeText(applicationContext, "Berhasil Diubah", Toast.LENGTH_SHORT).show()
                        finish()
                    }
                }
            }
        })
    }

    private fun load(id: String){
        val ref = FirebaseDatabase.getInstance().getReference("barang")
            .child(id)
        ref.addListenerForSingleValueEvent(object : ValueEventListener{
            override fun onCancelled(p0: DatabaseError) {
            }

            override fun onDataChange(p0: DataSnapshot) {
                if(p0.exists()){
                    val value = p0.getValue(BarangModel::class.java)
                    Picasso.get().load(value!!.fotobarang).into(fotobarang)
                    fotobarang.visibility = View.VISIBLE
                    namabarang.setText(value.nama)
                    stok.setText(value.stok.toString())
                    harga.setText(value.harga.toString())
                    deskripsi.setText(value.deskripsi)
                    kondisi.setSelection(adapterkondisi.getPosition(value.kondisi))
                    kategori.setSelection(adapterkategori.getPosition(value.kategori))
                    for(i in value.jasapengiriman.indices){
                        if(value.jasapengiriman[i] == "COD"){
                            cod.setBackgroundColor(resources.getColor(R.color.colorWhite))
                            pengiriman.add(cod.text.toString())
                            codt = true
                        }
                        else if(value.jasapengiriman[i] == "Antar Kerumah"){
                            antar.setBackgroundColor(resources.getColor(R.color.colorWhite))
                            pengiriman.add(antar.text.toString())
                            ongkir.setText(value.ongkir.toString())
                            ongkir.visibility = View.VISIBLE
                            antart = true
                        }
                        else{
                            ambil.setBackgroundColor(resources.getColor(R.color.colorWhite))
                            pengiriman.add(ambil.text.toString())
                            ambilt = true
                        }
                    }
                }
            }
        })
    }
}