package com.example.kancatani.Login

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.kancatani.R
import com.example.kancatani.SharePreference.Sharepreference
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import kotlinx.android.synthetic.main.activity_regiskelengkapan.*

class RegisterKelengkapan : AppCompatActivity() {

    lateinit var SP: Sharepreference
    var selectedPhoto: Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_regiskelengkapan)
        loading.visibility = View.GONE
        SP = Sharepreference()

        up_foto.setOnClickListener{
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            startActivityForResult(intent, 0)
        }

        val nama = intent.getStringExtra("nama").toString()
        val email = intent.getStringExtra("email").toString()

        btn_selesai.setOnClickListener {
            val intent = Intent(this, Register2Activity::class.java)
            intent.putExtra("activity","penjual")
            intent.putExtra("noktp", et_ktp.text.toString())
            if(selectedPhoto != null){
                intent.putExtra("fototoko", selectedPhoto.toString())
            }
            intent.putExtra("nama", nama)
            intent.putExtra("email",email)
            startActivity(intent)
            this.finish()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 0 && resultCode == Activity.RESULT_OK && data != null) {
            // proceed and check what the selected image was....
            //Log.d(TAG, "Photo was selected")

            selectedPhoto = data.data

            println("fototoko = " + selectedPhoto.toString())

            val bitmap = MediaStore.Images.Media.getBitmap(contentResolver, selectedPhoto)

            foto_toko.setImageBitmap(bitmap)
        }
    }
}