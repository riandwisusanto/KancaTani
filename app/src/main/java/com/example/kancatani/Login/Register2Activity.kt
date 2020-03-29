package com.example.kancatani.Login

import android.content.DialogInterface
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.net.toUri
import com.example.kancatani.Model.UserModel
import com.example.kancatani.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import kotlinx.android.synthetic.main.activity_register2.*

class Register2Activity : AppCompatActivity() {

    private var nama = " "
    private var email = " "
    private var fototoko = " "
    private var noktp = " "
    private lateinit var ref : DatabaseReference
    private lateinit var auth : FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register2)
        auth = FirebaseAuth.getInstance()
        ref = FirebaseDatabase.getInstance().getReference("pengguna")
        if(intent.getStringExtra("activity") == "penjual"){
            fototoko = intent.getStringExtra("fototoko").toString()
            noktp = intent.getStringExtra("noktp").toString()
            println("fototoko = " + fototoko)
        }
        nama = intent.getStringExtra("nama").toString()
        email = intent.getStringExtra("email").toString()

        btn_daftar3.setOnClickListener {
            createUser()
        }
    }

    private fun createUser() {

        if (et_password.text.toString() == "") {
            et_password.error = "Data tidak boleh kosong"
            et_password.requestFocus()
        } else if (et_password.text.toString().length < 8) {
            et_password.error = "Minimal 8 karakter"
            et_password.requestFocus()
        } else if (et_repassword.text.toString() == "") {
            et_repassword.error = "Data tidak boleh kosong"
            et_repassword.requestFocus()
        } else if (et_repassword.text.toString().length < 8) {
            et_repassword.error = "Minimal 8 karakter"
            et_repassword.requestFocus()
        } else if (et_password.text.toString() != et_repassword.text.toString()) {
            et_repassword.error = "Password tidak sama"
            et_repassword.requestFocus()
        }else{
            loading.visibility = View.VISIBLE

            createUserWithEmail()

        }

    }

    private fun createUserWithEmail(){
        auth.createUserWithEmailAndPassword(email,et_password.text.toString()).addOnCompleteListener { task->
            if(task.isSuccessful){
                if(intent.getStringExtra("activity") == "penjual"){
                    val alertDialog = AlertDialog.Builder(this)
                    alertDialog.setTitle("Tunggu Konfirmasi !")
                    alertDialog.setMessage("Silahkan menunggu data anda divalidasi oleh pihak KancaTani, Verifikasi akun akan" +
                                "dikirim melalui email anda dalam 1x24jam")
                        .setCancelable(false)
                        .setNegativeButton("OKE", object: DialogInterface.OnClickListener{
                            override fun onClick(dialog: DialogInterface?, id: Int) {
                                dialog?.cancel()
                                startActivity(Intent(this@Register2Activity, LoginActivity::class.java))
                                finish()
                            }
                        })
                        .create().show()
                }
                else{
                    val user = auth.currentUser
                    user?.sendEmailVerification()?.addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            uploadImageToFirebaseStorage()
                            Toast.makeText(this.applicationContext, "Verifikasi akun anda, KancaTani telah mengirimkan email verifikasi", Toast.LENGTH_LONG).show()
                            startActivity(Intent(this@Register2Activity, LoginActivity::class.java))
                            finish()
                        }
                    }
                }
            }
        }
    }

    private fun saveUserToDB(string: String){
        val userId = auth.currentUser!!.uid
        val dataUser = UserModel(userId,
            intent.getStringExtra("activity").toString(),
            "-",
            " ",
            " ",
            " ",
            email,
            "-",
            nama,
            "-",
            "1/1/1999",
            string,
            noktp
        )
        ref.child(userId).setValue(dataUser)
    }

    private fun uploadImageToFirebaseStorage() {
        val uid = auth.currentUser!!.uid
        if(fototoko == null){
            saveUserToDB("x")
        }
        else{
            val ref = FirebaseStorage.getInstance().getReference("pengguna/$uid/fototoko")
            putfile(ref, fototoko.toUri())
        }
    }

    private fun putfile(ref: StorageReference, isi: Uri){
        ref.putFile(isi)
            .addOnSuccessListener {
                ref.downloadUrl.addOnSuccessListener {
                    saveUserToDB(it.toString())
                    auth.signOut()
                }
            }
            .addOnFailureListener {
                //Log.d(TAG, "Failed to upload image to storage: ${it.message}")
            }
    }
}
