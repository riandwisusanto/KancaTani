package com.example.kancatani.Login

import android.content.DialogInterface
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.kancatani.Pembeli.HomePembeli
import com.example.kancatani.Model.UserModel
import com.example.kancatani.Penjual.PenjualActivity
import com.example.kancatani.R
import com.example.kancatani.SharePreference.Sharepreference
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    private lateinit var ref : DatabaseReference
    private lateinit var auth: FirebaseAuth
    lateinit var mGoogleSignInClient : GoogleSignInClient
    private val RC_SIGN_IN: Int = 0
    lateinit var SP: Sharepreference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        loading.visibility = View.GONE
        ref = FirebaseDatabase.getInstance().getReference("pengguna")
        auth = FirebaseAuth.getInstance()
        SP = Sharepreference()
        createRequest()

        btn_masuk.setOnClickListener {
            loading.visibility = View.VISIBLE
            doLogin()
        }

        link_regis.setOnClickListener {
            gotoRegisterPage()
        }
    }

    private fun gotoRegisterPage(){

        val alertDialog = AlertDialog.Builder(this)
        alertDialog.setTitle("Daftar Akun")
        alertDialog.setMessage("Anda ingin mendaftar sebagai ?")
            .setCancelable(true)
            .setPositiveButton("PEMBELI", object: DialogInterface.OnClickListener {
                override fun onClick(dialog: DialogInterface?, id: Int) {
                    val intent = Intent(this@LoginActivity, RegisterActivity::class.java)
                    intent.putExtra("status","pembeli")
                    startActivity(intent)
                }
            })

            .setNegativeButton("PENJUAL", object: DialogInterface.OnClickListener{
                override fun onClick(dialog: DialogInterface?, id: Int) {
                    val intent = Intent(this@LoginActivity, RegisterActivity::class.java)
                    intent.putExtra("status","penjual")
                    startActivity(intent)
                }
            })
            .create().show()
    }

    private fun updateUI(currentUser : FirebaseUser?){
        if(currentUser != null){
            if(currentUser.isEmailVerified){
                cekPengguna(currentUser)
            }else{
                Toast.makeText(this,"Verifikasi akun anda, KancaTani telah mengirimkan email verifikasi",
                    Toast.LENGTH_LONG).show()
                loading.visibility = View.GONE
                auth.signOut()
            }
        }else{
            loading.visibility = View.GONE
            Toast.makeText(this,"Gagal login",Toast.LENGTH_LONG).show()
        }
    }

    private fun doLogin(){
        if(formusername.text.toString().isEmpty()){
            formusername.error = "Data tidak boleh kosong"
            formusername.requestFocus()
        }else if(formpassword.text.toString().isEmpty()){
            formpassword.error = "Data tidak boleh kosong"
            formpassword.requestFocus()
        }else{
            auth.signInWithEmailAndPassword(formusername.text.toString(),formpassword.text.toString()).addOnCompleteListener { task ->
                if(task.isSuccessful){
                    val currentUser = auth.currentUser
                    updateUI(currentUser)
                }else{
                    updateUI(null)
                }
            }
        }
    }

    private fun cekPengguna(firebaseUser: FirebaseUser){
        val query = FirebaseDatabase.getInstance().getReference("pengguna").child(firebaseUser.uid)
        query.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
            }
            override fun onDataChange(data: DataSnapshot) {
                if(data.exists()){
                    val value = data.getValue(UserModel::class.java)
                    SP.createSP(applicationContext, "username", value!!.nama)
                    SP.createSP(applicationContext, "fotoprofil", value.foto)
                    SP.createSP(applicationContext, "provinsi", value.provinsi)
                    SP.createSP(applicationContext, "id", auth.currentUser!!.uid)
                    SP.createSP(applicationContext, "status", "login")
                    if(value.status == "pembeli"){
                        SP.createSP(applicationContext, "st", "pembeli")
                        startActivity(Intent(applicationContext,HomePembeli::class.java))
                    }
                    else{
                        SP.createSP(applicationContext, "st", "penjual")
                        startActivity(Intent(applicationContext, PenjualActivity::class.java))
                    }
                }
            }
        })
    }

    private fun createRequest(){
        // Configure Google Sign In
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso)

    }

    private fun signInGoogle() {
        val signInIntent = mGoogleSignInClient.signInIntent
        startActivityForResult(signInIntent, RC_SIGN_IN)
    }

    public override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        loading.visibility = View.VISIBLE
        if (requestCode == RC_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                val account = task.getResult(ApiException::class.java)
                firebaseAuthWithGoogle(account!!)
            } catch (e: ApiException) {
            }
        }
    }

    private fun firebaseAuthWithGoogle(acct: GoogleSignInAccount) {
        val credential = GoogleAuthProvider.getCredential(acct.idToken, null)
        auth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    saveData()
                    loading.visibility = View.GONE
                    val intent = Intent(applicationContext, HomePembeli::class.java)
                    SP.createSP(this, "status", "login")
                    SP.createSP(this, "id", auth.currentUser!!.uid)
                    startActivity(intent)
                } else {
                    Toast.makeText(this,"Gagal", Toast.LENGTH_SHORT).show()
                }

            }
    }

    private fun cekAkun(email : String):Boolean{
        var result = false
        val query = FirebaseDatabase.getInstance().getReference("pengguna").orderByChild("email").equalTo(email)
        query.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {

            }
            override fun onDataChange(data: DataSnapshot) {
                result = data.exists()
            }
        })
        return result
    }

    override fun onBackPressed() {
        val alertDialog = AlertDialog.Builder(this)
        Toast.makeText(this, "Back is Clicked", Toast.LENGTH_SHORT)
        alertDialog.setTitle("Keluar Aplikasi")
        alertDialog.setMessage("Apakah anda mau keluar dari aplikasi ?")
            .setCancelable(false)
            .setPositiveButton("YA", object: DialogInterface.OnClickListener {
                override fun onClick(dialog: DialogInterface?, id: Int) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                        finishAffinity()
                    }
                }
            })

            .setNegativeButton("TIDAK", object: DialogInterface.OnClickListener{
                override fun onClick(dialog: DialogInterface?, id: Int) {
                    dialog?.cancel()
                }
            }).create().show()
    }

    private fun saveData(){
        val userId = auth.currentUser!!.uid

        if(!cekAkun(auth.currentUser!!.email.toString())){
            val dataUser = UserModel(userId,
                "",
                auth.currentUser!!.email.toString(),
                "",
                auth.currentUser!!.displayName.toString(),
                "",
                "",
                auth.currentUser!!.photoUrl.toString())

            ref.child(userId).setValue(dataUser)
        }else{

        }

    }
}
