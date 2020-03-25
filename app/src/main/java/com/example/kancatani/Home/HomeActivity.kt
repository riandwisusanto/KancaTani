package com.example.kancatani.Home

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.kancatani.R

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        val navView: BottomNavigationView = findViewById(R.id.nav_view)

        val navController = findNavController(R.id.nav_host_fragment)
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
}
