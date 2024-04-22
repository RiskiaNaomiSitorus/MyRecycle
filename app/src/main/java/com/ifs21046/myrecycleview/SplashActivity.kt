package com.ifs21046.myrecycleview

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity

class SplashActivity : AppCompatActivity() {

    private val splashTimeOut: Long = 3000 // Durasi splash screen dalam milidetik

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        // Handler untuk menunda navigasi ke activity berikutnya
        Handler().postDelayed({
            // Navigasi ke activity berikutnya setelah splashTimeOut
            startActivity(Intent(this, MainActivity::class.java))
            finish() // Tutup activity saat ini agar tidak bisa kembali ke splash screen lagi
        }, splashTimeOut)
    }
}