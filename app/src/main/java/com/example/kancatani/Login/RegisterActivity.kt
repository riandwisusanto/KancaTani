package com.example.kancatani.Login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.kancatani.R
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        loading1.visibility = View.GONE
        btn_daftar1.setOnClickListener {
            loading1.visibility = View.VISIBLE
            gotoRegisDua()
        }
    }

    private fun gotoRegisDua(){
        if(et_nama.text.toString().isEmpty()){
            et_nama.error = "Data tidak boleh kosong"
            et_nama.requestFocus()
        }else if(!cekEmailFormat(et_email.text.toString())){
            et_email.error = "Format email salah"
            et_email.requestFocus()
        }else if(et_email.text.toString().isEmpty()){
            et_email.error = "Data tidak boleh kosong"
            et_email.requestFocus()
        }else{
            cekEmail(et_email.text.toString())
        }
    }

    private fun StartActRegisDua(){
        val intent = Intent(this, Register2Activity::class.java)
        intent.putExtra("activity","RegisterSatu")
        intent.putExtra("nama", et_nama.text.toString())
        intent.putExtra("email",et_email.text.toString())
        startActivity(intent)
        this.finish()
    }

    private fun cekEmailFormat(email :String):Boolean{
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    private fun cekEmail(email : String){
        val query = FirebaseDatabase.getInstance().getReference("pengguna").orderByChild("email").equalTo(email)
        query.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {

            }
            override fun onDataChange(email: DataSnapshot) {
                if(email.exists()){
                    loading1.visibility = View.GONE
                    et_email.error = "Email sudah terdaftar"
                    et_email.requestFocus()
                }else{
                    loading1.visibility = View.GONE
                    StartActRegisDua()
                }
            }
        })
    }
}
