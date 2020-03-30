package com.example.kancatani.Penjual.ui.Profil

import android.app.Activity
import android.app.AlertDialog
import android.app.DatePickerDialog
import android.app.Dialog
import android.content.DialogInterface
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.text.InputType
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.example.kancatani.Login.LoginActivity
import com.example.kancatani.Model.UserModel
import com.example.kancatani.Model.db_alamat
import com.example.kancatani.R
import com.example.kancatani.SharePreference.Sharepreference
import com.google.firebase.auth.EmailAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.squareup.picasso.Picasso
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList


class profil_penjual : Fragment() {

    lateinit var auth: FirebaseAuth
    lateinit var dbAlamat: db_alamat
    lateinit var provinsi: Array<String>
    lateinit var kota: ArrayList<String>
    lateinit var kecamatan: ArrayList<String>
    lateinit var SP: Sharepreference

    lateinit var alamat: TextView
    lateinit var foto_profil: ImageView
    lateinit var ganti_foto_profil: TextView
    lateinit var profil_username: TextView
    lateinit var profil_tgllahir: TextView
    lateinit var profil_jk: TextView
    lateinit var profil_telp: TextView
    lateinit var profil_email: TextView
    lateinit var profil_ktp: TextView
    lateinit var btn_save: ImageView

    var c = Calendar.getInstance()
    var selectedPhoto: Uri? = null
    var x = true
    var y = false

    lateinit var dialogalamat: AlertDialog.Builder

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.profil_penjual_fragment, container, false)

        SP = Sharepreference()
        auth = FirebaseAuth.getInstance()
        loaddata()
        //setawal

        alamat = root.findViewById<TextView>(R.id.profil_alamat)
        foto_profil = root.findViewById<ImageView>(R.id.bg_profil)
        ganti_foto_profil= root.findViewById<TextView>(R.id.ganti_foto_profil)
        profil_username= root.findViewById<TextView>(R.id.profil_username)
        profil_tgllahir= root.findViewById<TextView>(R.id.profil_tgllahir)
        profil_jk = root.findViewById<TextView>(R.id.profil_jk)
        profil_telp = root.findViewById<TextView>(R.id.profil_telp)
        profil_email = root.findViewById<TextView>(R.id.profil_email)
        profil_ktp = root.findViewById<TextView>(R.id.profil_noktp)

        btn_save = root.findViewById<ImageView>(R.id.btn_save)

        btn_save.setOnClickListener {
            if(x == true){
                btnsavetrue()
                x = false
            }
            else if(x == false){
                if(y == true){
                    uploadImageToFirebaseStorage()
                }
                btnsavefalse()
                x = true
            }
        }

        profil_username.setOnClickListener {
            editform("username")
        }

        profil_email.setOnClickListener {
            editform("email")
        }

        profil_telp.setOnClickListener {
            editform("telp")
        }

        profil_jk.setOnClickListener {
            gantiJK()
        }

        val dt = profil_tgllahir.text.toString()
        //set date awal
        var dy = toString()
        var mt = toString()
        var th = toString()
        if(dt.substring(1,2) == "/"){
            dy = dt.substring(0,1)
            if(dt.substring(3,4) == "/"){
                mt = dt.substring(2,3)
                th = dt.substring(4,8)
            }
            else{
                mt = dt.substring(2,4)
                th = dt.substring(5,9)
            }
        }
        else{
            dy = dt.substring(0,2)
            if(dt.substring(4,5) == "/"){
                mt = dt.substring(3,4)
                th = dt.substring(5,9)
            }
            else{
                mt = dt.substring(3,5)
                th = dt.substring(6,10)
            }
        }

        val date = DatePickerDialog.OnDateSetListener{ view, year1, monthOfYear, dayOfMonth ->
            c.set(Calendar.YEAR, year1)
            c.set(Calendar.MONTH, monthOfYear)
            c.set(Calendar.DAY_OF_MONTH, dayOfMonth)
            updateLabel()
        }

        val dx = (dy.toInt() - 1).toString()+"/"+mt+"/"+ (th.toInt() - 1).toString()
        println("day " + dx)
        profil_tgllahir.setOnClickListener {
            println("klik" + dx)
            println(profil_tgllahir.text.toString())
            if(profil_tgllahir.text.toString() == dx){
                DatePickerDialog(context?.applicationContext!!, date, th.toInt()-1, mt.toInt()-1, dy.toInt()-1).show()
            }
            else{
                DatePickerDialog(context?.applicationContext!!, date, c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH)).show()
            }
        }

        alamat.setOnClickListener {
            editalamat()
        }

        ganti_foto_profil.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            startActivityForResult(intent, 1)
        }

        val gantipass = root.findViewById<TextView>(R.id.profil_password)
        gantipass.setOnClickListener {
            gantipassword()
        }
        val logout = root.findViewById<Button>(R.id.btn_keluar)
        logout.setOnClickListener {
            val alertDialog = AlertDialog.Builder(context?.applicationContext)
            Toast.makeText(context?.applicationContext, "Back is Clicked", Toast.LENGTH_SHORT)
            alertDialog.setTitle("Keluar Akun")
            alertDialog.setMessage("Apakah anda mau keluar dari akun ini ?")
                .setCancelable(false)
                .setPositiveButton("YA", object: DialogInterface.OnClickListener {
                    override fun onClick(dialog: DialogInterface?, id: Int) {
                        val intent = Intent(context?.applicationContext, LoginActivity::class.java)
                        SP.createSP(context!!.applicationContext,"id", "")
                        SP.createSP(context!!.applicationContext,"status", "")
                        startActivity(intent)
                    }
                })

                .setNegativeButton("TIDAK", object: DialogInterface.OnClickListener{
                    override fun onClick(dialog: DialogInterface?, id: Int) {
                        dialog?.cancel()
                    }
                }).create().show()
        }

        return root
    }

    private fun loaddata(){
        val ref = FirebaseDatabase.getInstance().getReference("pengguna").orderByChild("id").equalTo(SP.loadSP((context!!.applicationContext), "id"))
        ref.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {

            }

            override fun onDataChange(p0: DataSnapshot) {
                if(p0.exists()){
                    p0.children.forEach{
                        val vl = it.getValue(UserModel::class.java)
                        if(vl != null){
                            if(vl.foto != null){
                                Picasso.get().load(vl.foto).into(foto_profil)
                            }
                            profil_username.setText(vl.nama)
                            profil_tgllahir.setText(vl.tgllahir)
                            profil_jk.setText(vl.jk)
                            profil_email.setText(vl.email)
                            profil_telp.setText(vl.notelp)
                            profil_ktp.setText(vl.ktp)

                            SP.createSP(this@profil_penjual.context!!, "username", vl.nama)
                            SP.createSP(this@profil_penjual.context!!, "alamat", vl.alamat)
                            SP.createSP(this@profil_penjual.context!!, "fotoprofil", vl.foto)
                            SP.createSP(this@profil_penjual.context!!, "tgllahir", vl.tgllahir)
                            SP.createSP(this@profil_penjual.context!!, "jk", vl.jk)
                            SP.createSP(this@profil_penjual.context!!, "notelp", vl.notelp)
                            SP.createSP(this@profil_penjual.context!!, "email", vl.email)
                            SP.createSP(this@profil_penjual.context!!, "provinsi", vl.provinsi)
                            SP.createSP(this@profil_penjual.context!!, "kota", vl.kota)
                            SP.createSP(this@profil_penjual.context!!, "kecamatan", vl.kecamatan)
                        }
                    }
                }
            }
        })
    }

    private fun editform(string: String){
        val dialog = AlertDialog.Builder(context?.applicationContext)
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.activity_edit_form, null)

        val header = view.findViewById<TextView>(R.id.text_header)
        val header1 = view.findViewById<TextView>(R.id.text_form)
        val edit = view.findViewById<TextView>(R.id.edit_text)
        if(string == "username"){
            header.setText("Edit Username")
            header1.setText("Username")
            edit.setText(profil_username.text.toString())
            edit.inputType = InputType.TYPE_TEXT_VARIATION_PERSON_NAME
        }else if(string == "telp"){
            header.setText("Edit Nomor Telepon")
            header1.setText("Nomor Telepon")
            edit.setText(profil_telp.text.toString())
            edit.inputType = InputType.TYPE_CLASS_NUMBER
        }else if(string == "email"){
            header.setText("Edit Email")
            header1.setText("Email")
            edit.setText(profil_email.text.toString())
            edit.inputType = InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS
        }

        dialog.setView(view)
            .setCancelable(false)
            .setPositiveButton("Simpan", object: DialogInterface.OnClickListener{
                override fun onClick(p0: DialogInterface?, p1: Int) {
                    if(string == "username"){
                        SP.createSP(context!!.applicationContext, "username", edit.text.toString())
                        profil_username.text = edit.text.toString()
                    }else if(string == "telp"){
                        SP.createSP(context!!.applicationContext, "notelp", edit.text.toString())
                        profil_telp.text = edit.text.toString()
                    }else{
                        SP.createSP(context!!.applicationContext, "email", edit.text.toString())
                        profil_email.text = edit.text.toString()
                    }
                    y = true
                    btn_save.setImageResource(R.drawable.ic_ceklis)
                    p0?.cancel()
                }

            })
            .setNegativeButton("Batal", object :DialogInterface.OnClickListener{
                override fun onClick(p0: DialogInterface?, p1: Int) {
                    p0?.cancel()
                }
            })
            .create().show()
    }

    private fun editalamat(){
        dialogalamat = AlertDialog.Builder(context!!.applicationContext)
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.activity_edit_alamat, null)

        val form_provinsi = view.findViewById<Spinner>(R.id.form_provinsi)
        val form_kota = view.findViewById<Spinner>(R.id.form_kota)
        val form_kecamatan = view.findViewById<Spinner>(R.id.form_kecamatan)
        SP = Sharepreference()

        dbAlamat = db_alamat
        provinsi = dbAlamat.provinsi
        kota = arrayListOf()
        kecamatan = arrayListOf()

        var Provinsi = toString()
        var Kota = toString()
        var Kecamatan = toString()

        val adapter_prov = ArrayAdapter(context!!.applicationContext, R.layout.support_simple_spinner_dropdown_item, provinsi)
        val adapter_kota = ArrayAdapter(context!!.applicationContext, R.layout.support_simple_spinner_dropdown_item, kota)
        val adapter_kecamatan = ArrayAdapter(context!!.applicationContext, R.layout.support_simple_spinner_dropdown_item, kecamatan)

        //set provinsi
        form_provinsi.adapter = adapter_prov

        loadlocation(view, adapter_prov)
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
                Provinsi = provinsi[position]
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
                Kota = kota[position]
            }
        }

        form_kecamatan.onItemSelectedListener = object :
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

        dialogalamat.setView(view)
            .setCancelable(false)
            .setPositiveButton("Simpan", object: DialogInterface.OnClickListener{
                override fun onClick(p0: DialogInterface?, p1: Int) {
                    SP.createSP(context!!.applicationContext, "provinsi",Provinsi)
                    SP.createSP(context!!.applicationContext, "kota",Kota)
                    SP.createSP(context!!.applicationContext, "kecamatan",Kecamatan)
                    btn_save.setImageResource(R.drawable.ic_ceklis)
                    y = true
                    println("provinsi " + Provinsi)
                    p0?.cancel()
                }
            })
            .setNegativeButton("Batal", object :DialogInterface.OnClickListener{
                override fun onClick(p0: DialogInterface?, p1: Int) {
                    p0?.cancel()
                }
            })
            .create().show()
    }

    private fun loadlocation(view: View?, adapter: ArrayAdapter<String>){
        val form_provinsi = view?.findViewById<Spinner>(R.id.form_provinsi)
        val form_kota = view?.findViewById<Spinner>(R.id.form_provinsi)
        val form_kecamatan = view?.findViewById<Spinner>(R.id.form_provinsi)
        val ref = FirebaseDatabase.getInstance().getReference("pengguna").orderByChild("id").equalTo(SP.loadSP((context!!.applicationContext), "id"))
        ref.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {

            }

            override fun onDataChange(p0: DataSnapshot) {
                if(p0.exists()){
                    p0.children.forEach{
                        val vl = it.getValue(UserModel::class.java)
                        form_provinsi?.setSelection(adapter.getPosition(vl?.provinsi))
                    }
                }
            }
        })
    }

    private fun btnsavetrue(){
        ganti_foto_profil.visibility = View.VISIBLE
        ganti_foto_profil.isClickable = true
        profil_username.isClickable = true
        profil_username.setCompoundDrawablesWithIntrinsicBounds(0,0,R.drawable.ic_edit, 0)
        profil_tgllahir.isClickable = true
        profil_tgllahir.setCompoundDrawablesWithIntrinsicBounds(0,0,R.drawable.ic_edit, 0)
        profil_jk.isClickable = true
        profil_jk.setCompoundDrawablesWithIntrinsicBounds(0,0,R.drawable.ic_edit, 0)
        profil_telp.isClickable = true
        profil_telp.setCompoundDrawablesWithIntrinsicBounds(0,0,R.drawable.ic_edit, 0)
        profil_email.isClickable = true
        profil_email.setCompoundDrawablesWithIntrinsicBounds(0,0,R.drawable.ic_edit, 0)
        alamat.isClickable = true
        alamat.setCompoundDrawablesWithIntrinsicBounds(0,0,R.drawable.ic_edit, 0)
    }

    private fun btnsavefalse(){
        ganti_foto_profil.visibility = View.INVISIBLE
        ganti_foto_profil.isClickable = false
        profil_username.isClickable = false
        profil_username.setCompoundDrawablesWithIntrinsicBounds(0,0,0, 0)
        profil_tgllahir.isClickable = false
        profil_tgllahir.setCompoundDrawablesWithIntrinsicBounds(0,0,0, 0)
        profil_jk.isClickable = false
        profil_jk.setCompoundDrawablesWithIntrinsicBounds(0,0,0, 0)
        profil_telp.isClickable = false
        profil_telp.setCompoundDrawablesWithIntrinsicBounds(0,0,0, 0)
        profil_email.isClickable = false
        profil_email.setCompoundDrawablesWithIntrinsicBounds(0,0,0, 0)
        alamat.isClickable = false
        alamat.setCompoundDrawablesWithIntrinsicBounds(0,0,0, 0)
    }

    fun gantiJK(){
        val dialog = Dialog(context!!.applicationContext)
        dialog.setTitle("Ganti Jenis Kelamin")
        dialog.setContentView(R.layout.activity_edit_jeniskelamin)

        val laki = dialog.findViewById<TextView>(R.id.jkLaki)
        val perempuan = dialog.findViewById<TextView>(R.id.jkPerempuan)

        laki.setOnClickListener {
            SP.createSP(context!!.applicationContext, "jk", "Laki - laki")
            btn_save.setImageResource(R.drawable.ic_ceklis)
            y = true
            profil_jk.text = "Laki - laki"
            dialog.dismiss()
        }
        perempuan.setOnClickListener {
            SP.createSP(context!!.applicationContext, "jk", "Perempuan")
            btn_save.setImageResource(R.drawable.ic_ceklis)
            y == true
            profil_jk.text = "Perempuan"
            dialog.dismiss()
        }
        dialog.show()
    }

    fun updateLabel(){
        val myFormat =  "dd/MM/yyyy"
        val sdf = SimpleDateFormat(myFormat, Locale.US)
        profil_tgllahir.setText(sdf.format(c.time))
        btn_save.setImageResource(R.drawable.ic_ceklis)
        y = true
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 1 && resultCode == Activity.RESULT_OK && data != null) {
            // proceed and check what the selected image was....
            //Log.d(TAG, "Photo was selected")

            selectedPhoto = data.data

            val bitmap = MediaStore.Images.Media.getBitmap(this.context!!.contentResolver, selectedPhoto)

            foto_profil.setImageBitmap(bitmap)

            btn_save.setImageResource(R.drawable.ic_ceklis)
            y = true
        }
    }

    private fun uploadImageToFirebaseStorage() {
        val uid = SP.loadSP(context!!.applicationContext, "id")
        if(selectedPhoto == null){
            savetodatabase()
        }
        else{
            val ref = FirebaseStorage.getInstance().getReference("pengguna/$uid/fotoprofil")
            putfile(ref, selectedPhoto!!)
            savetodatabase()
        }
    }

    private fun putfile(ref: StorageReference, isi: Uri){
        ref.putFile(isi)
            .addOnSuccessListener {
                ref.downloadUrl.addOnSuccessListener {
                    SP.createSP(this.context!!, "fotoprofil", it.toString())
                }
            }
            .addOnFailureListener {
                //Log.d(TAG, "Failed to upload image to storage: ${it.message}")
            }
    }

    private fun savetodatabase(){
        val ref = FirebaseDatabase.getInstance().getReference("pengguna").child(SP.loadSP(context!!.applicationContext, "id"))
        val value = UserModel(SP.loadSP(context!!.applicationContext, "id"), SP.loadSP(context!!.applicationContext, "alamat"),
            SP.loadSP(context!!.applicationContext, "provinsi"), SP.loadSP(context!!.applicationContext, "kota"),
            SP.loadSP(context!!.applicationContext, "kecamatan"), SP.loadSP(context!!.applicationContext, "email"),
            SP.loadSP(context!!.applicationContext, "jk"), SP.loadSP(context!!.applicationContext, "username"),
            SP.loadSP(context!!.applicationContext, "notelp"), profil_tgllahir.text.toString(),
            SP.loadSP(context!!.applicationContext, "fotoprofil"), profil_ktp.text.toString())

        println("uri = " + SP.loadSP(context!!.applicationContext, "fotoprofil"))

        ref.setValue(value).addOnCompleteListener {
            Toast.makeText(context!!.applicationContext, "Berhasil Disimpan", Toast.LENGTH_SHORT).show()
            btn_save.setImageResource(R.drawable.ic_edit)
            y = false
        }
    }

    fun gantipassword(){
        val dialog = AlertDialog.Builder(this.context)
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.activity_ganti_password, null)

        val passlama = view.findViewById<EditText>(R.id.passlama)
        val passkonfir = view.findViewById<EditText>(R.id.passkonfir)
        val passbaru = view.findViewById<EditText>(R.id.passbaru)

        dialog.setView(view)
            .setCancelable(false)
            .setPositiveButton("Simpan", object: DialogInterface.OnClickListener{
                override fun onClick(p0: DialogInterface?, p1: Int) {
                    if(passlama.text.isNotEmpty() && passbaru.text.isNotEmpty() && passkonfir.text.isNotEmpty()){
                        if(passbaru.text.toString().equals(passkonfir.text.toString())){
                            val user = auth.currentUser
                            if(user != null && user.email != null){
                                val credential = EmailAuthProvider
                                    .getCredential(user.email!!, passlama.text.toString())

                                user.reauthenticate(credential)
                                    .addOnCompleteListener {
                                        if(it.isSuccessful){
                                            Toast.makeText(context!!.applicationContext, "Password berhasil diubah, silahkan login kembali", Toast.LENGTH_LONG).show()
                                            user.updatePassword(passbaru.text.toString())
                                                .addOnCompleteListener {
                                                    SP.createSP(context!!.applicationContext, "id", "")
                                                    SP.createSP(context!!.applicationContext, "status", "")
                                                    val intent = Intent(context!!.applicationContext, LoginActivity::class.java)
                                                    startActivity(intent)
                                                }
                                        }
                                        else{
                                            Toast.makeText(context!!.applicationContext, "Gagal mengautentikasi", Toast.LENGTH_LONG).show()
                                        }
                                    }
                            }
                            else{
                                Toast.makeText(context!!.applicationContext, "Password missmatching", Toast.LENGTH_LONG).show()
                            }
                        }
                        else{
                            Toast.makeText(context!!.applicationContext, "Silahkan isi semua field", Toast.LENGTH_LONG).show()
                        }
                    }
                    p0?.cancel()
                }

            })
            .setNegativeButton("Batal", object :DialogInterface.OnClickListener{
                override fun onClick(p0: DialogInterface?, p1: Int) {
                    p0?.cancel()
                }
            })
            .create().show()
    }
}
