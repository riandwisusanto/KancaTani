package com.example.kancatani.SplashScreen

import android.content.DialogInterface
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.example.kancatani.Login.LoginActivity
import com.example.kancatani.Pembeli.HomePembeli
import com.example.kancatani.Penjual.PenjualActivity
import com.example.kancatani.R
import com.example.kancatani.SharePreference.Sharepreference
import com.github.paolorotolo.appintro.AppIntro

class SplashScrennMenu : AppIntro() {

    lateinit var SP: Sharepreference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        //setContentView(R.layout.activity_splash_screnn_menu)

        SP = Sharepreference()
        if(SP.loadSP(this, "status") == "login"){
            if(SP.loadSP(this, "st") == "pembeli"){
                val intent = Intent(applicationContext, HomePembeli::class.java)
                startActivity(intent)
            }else{
                val intent = Intent(applicationContext, PenjualActivity::class.java)
                startActivity(intent)
            }
        }

        addSlide(SplashScreen1())
        addSlide(SplashScreen2())
        addSlide(SplashScreen3())
    }

    override fun onSkipPressed(currentFragment: Fragment?) {
        super.onSkipPressed(currentFragment)

        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        this.finish()
    }

    override fun onDonePressed(currentFragment: Fragment?) {
        super.onDonePressed(currentFragment)
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        this.finish()
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
