package com.example.kancatani.Penjual

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.example.kancatani.Penjual.ui.Home.home_penjual
import com.example.kancatani.Penjual.ui.Lapak.lapak_penjual
import com.example.kancatani.Penjual.ui.Profil.profil_penjual
import com.example.kancatani.R
import com.example.kancatani.SharePreference.Sharepreference
import com.google.android.material.bottomnavigation.BottomNavigationView

class PenjualActivity : AppCompatActivity() {

    lateinit var SP: Sharepreference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_penjual)
        val navView: BottomNavigationView = findViewById(R.id.nav_view_penjual)

        val navController = findNavController(R.id.nav_host_fragment_penjual)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications
            )
        )
        navView.setupWithNavController(navController)
    }

    override fun onBackPressed() {
        val alertDialog = AlertDialog.Builder(this)
        alertDialog.setTitle("Close Application")
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
}
