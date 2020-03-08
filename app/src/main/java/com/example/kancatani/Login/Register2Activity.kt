package com.example.kancatani.Login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.kancatani.Model.UserModel
import com.example.kancatani.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_register2.*

class Register2Activity : AppCompatActivity() {

    private var nama = ""
    private var email = ""
    private lateinit var ref : DatabaseReference
    private lateinit var auth : FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register2)
        auth = FirebaseAuth.getInstance()
        ref = FirebaseDatabase.getInstance().getReference("pengguna")
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
                val user = auth.currentUser
                user?.sendEmailVerification()?.addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(
                            this.applicationContext,
                            "Verifikasi akun anda, KancaTani telah mengirimkan email verifikasi",
                            Toast.LENGTH_LONG
                        ).show()
                        saveUserToDB()
                        auth.signOut()
                        startActivity(Intent(this, LoginActivity::class.java))
                        finish()
                    }
                }
            }
        }
    }

    private fun saveUserToDB(){
        val userId = auth.currentUser!!.uid
        val dataUser = UserModel(userId,
            "",
            email,
            "",
            nama,
            "",
            "",
            "")
        ref.child(userId).setValue(dataUser)
    }
}
