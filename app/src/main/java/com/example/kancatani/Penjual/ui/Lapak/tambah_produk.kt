package com.example.kancatani.Penjual.ui.Lapak

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.kancatani.Model.BarangModel
import com.example.kancatani.Model.UserModel
import com.example.kancatani.R
import com.example.kancatani.SharePreference.Sharepreference
import com.google.firebase.database.*
import com.google.firebase.storage.FirebaseStorage
import kotlinx.android.synthetic.main.tambah_barang.*
import java.io.ByteArrayOutputStream
import java.io.FileNotFoundException
import java.io.InputStream

class tambah_produk : AppCompatActivity() {

    private var fotoselected: Uri? = null
    val pengiriman : ArrayList<String> = arrayListOf()
    var kondisit = toString()
    var kategorit = toString()
    lateinit var SP: Sharepreference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.tambah_barang)
        SP = Sharepreference()

        tambahbarang()

        btn_back.setOnClickListener {
            finish()
        }
    }

    private fun tambahbarang(){
        var codt = false
        var antart = false
        var ambilt = false

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

        btn_fotobarang.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            startActivityForResult(intent, 1)
        }

        val arraykondisi = arrayOf("Baru", "Bekas")
        val adapterkondisi = ArrayAdapter(applicationContext, R.layout.support_simple_spinner_dropdown_item, arraykondisi)

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
        val adapterkategori = ArrayAdapter(applicationContext, R.layout.support_simple_spinner_dropdown_item, arraykategori)

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

        simpan.setOnClickListener {
            val ref = FirebaseDatabase.getInstance().getReference("barang")
            val id = ref.push().key.toString()
            println("id = " + id)
            var onkir = 0
            if(ongkir.text.isNotEmpty()){
                onkir = ongkir.text.toString().toInt()
            }
            uploadImageToFirebaseStorage(id, namabarang.text.toString()
                , harga.text.toString(), deskripsi.text.toString(), stok.text.toString(), ref, onkir)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1 && resultCode == Activity.RESULT_OK && data != null) {
            fotoselected = data.data
            val bitmap = MediaStore.Images.Media.getBitmap(contentResolver, fotoselected)

            fotobarang.visibility = View.VISIBLE
            simpan.visibility = View.VISIBLE

            fotobarang.setImageBitmap(bitmap)
        }
    }

    private fun uploadImageToFirebaseStorage(id: String, namabarang: String, harga: String, deskripsi: String, stok: String, refe: DatabaseReference, ongkir: Int){
        val uid = SP.loadSP(applicationContext, "id")
        if(fotoselected == null){
            savetodatabase(id, namabarang, harga, deskripsi, stok, refe, "x", ongkir)
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
            .orderByChild("id").equalTo(SP.loadSP(applicationContext, "id"))
        referens.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {

            }

            override fun onDataChange(p0: DataSnapshot) {
                if(p0.exists()){
                    p0.children.forEach{
                        val vale = it.getValue(UserModel::class.java)
                        val value = BarangModel(id, SP.loadSP(applicationContext, "id")
                            , fotobar,
                            namabarang, harga.toInt(),
                            kategorit,  deskripsi, pengiriman , "0",
                            stok.toInt()
                            , kondisit , vale!!.provinsi, vale.kota, vale.kecamatan, 0, ongkir)
                        ref.child(id).setValue(value).addOnCompleteListener {
                            Toast.makeText(applicationContext, "Barang Berhasil Ditambahkan", Toast.LENGTH_SHORT).show()
                            finish()
                        }
                    }
                }
            }

        })
    }
}