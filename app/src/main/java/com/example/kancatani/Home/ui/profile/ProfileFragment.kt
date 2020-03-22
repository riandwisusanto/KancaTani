package com.example.kancatani.Home.ui.profile

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import com.example.kancatani.Model.UserModel
import com.example.kancatani.R
import com.example.kancatani.SharePreference.Sharepreference
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_edit_alamat.*

class ProfileFragment : Fragment() {

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

    lateinit var dialogalamat: AlertDialog.Builder

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_profile, container, false)

        loaddata()

        alamat = root.findViewById<TextView>(R.id.profil_alamat)
        foto_profil = root.findViewById<ImageView>(R.id.bg_profil)
        ganti_foto_profil= root.findViewById<TextView>(R.id.ganti_foto_profil)
        profil_username= root.findViewById<TextView>(R.id.profil_username)
        profil_tgllahir= root.findViewById<TextView>(R.id.profil_tgllahir)
        profil_jk= root.findViewById<TextView>(R.id.profil_jk)
        profil_telp= root.findViewById<TextView>(R.id.profil_telp)
        profil_email= root.findViewById<TextView>(R.id.profil_email)

        val btn_save = root.findViewById<ImageView>(R.id.btn_save)

        var x = true
        btn_save.setOnClickListener {
            if(x){
                btnsavetrue()
                x = false
            }
            else{
                btnsavefalse()
                x = true
            }
        }

        alamat.setOnClickListener {
            editalamat()
        }

        return root
    }

    private fun loaddata(){
        val ref = FirebaseDatabase.getInstance().getReference("pengguna").orderByChild("id").equalTo(SP.loadSP(
            this.context!!, "id"))
        ref.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {

            }

            override fun onDataChange(p0: DataSnapshot) {
                if(p0.exists()){
                    p0.children.forEach{
                        val vl = it.getValue(UserModel::class.java)
                        if(vl != null){
                            Picasso.get().load(vl.foto).into(foto_profil)
                            profil_username.setText(vl.nama)
                            profil_tgllahir.setText(vl.tgllahir)
                            profil_jk.setText(vl.jk)
                            profil_email.setText(vl.email)
                            profil_telp.setText(vl.notelp)
                        }
                    }
                }
            }
        })
    }

    private fun editalamat(){
        dialogalamat = AlertDialog.Builder(this.context)
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

        val adapter_prov = ArrayAdapter(this.context!!, R.layout.support_simple_spinner_dropdown_item, provinsi)
        val adapter_kota = ArrayAdapter(this.context!!, R.layout.support_simple_spinner_dropdown_item, kota)
        val adapter_kecamatan = ArrayAdapter(this.context!!, R.layout.support_simple_spinner_dropdown_item, kecamatan)

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
                    SP.createSP(this@ProfileFragment.context!!, "provinsi",Provinsi)
                    SP.createSP(this@ProfileFragment.context!!, "kota",Kota)
                    SP.createSP(this@ProfileFragment.context!!, "kecamatan",Kecamatan)
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
        val ref = FirebaseDatabase.getInstance().getReference("pengguna").orderByChild("id").equalTo(SP.loadSP(
            this.context!!, "id"))
        ref.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {

            }

            override fun onDataChange(p0: DataSnapshot) {
                if(p0.exists()){
                    p0.children.forEach{
                        val vl = it.getValue(UserModel::class.java)
                        form_provinsi?.setSelection(adapter.getPosition(vl?.provinsi))
                        form_kota?.setSelection(adapter.getPosition(vl?.kota))
                        form_kecamatan?.setSelection(adapter.getPosition(vl?.kecamatan))
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

}