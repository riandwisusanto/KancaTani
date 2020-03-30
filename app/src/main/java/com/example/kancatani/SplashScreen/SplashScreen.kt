package com.example.kancatani.SplashScreen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.kancatani.Login.LoginActivity
import com.example.kancatani.R

class SplashScreen : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        val backgrond = object : Thread(){
            override fun run() {
                try {
                    java.lang.Thread.sleep(2500)
                    val intent = android.content.Intent(applicationContext, SplashScrennMenu::class.java)
                    startActivity(intent)
                } catch (e: Exception){
                    e.printStackTrace()
                }
            }
        }

        backgrond.start()
    }
}
