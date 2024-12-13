package com.example.loginregister_ceksehat

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity

class LogoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_logo)

        // Pindah ke BrandnameActivity setelah 2 detik
        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this, BrandnameActivity::class.java)
            startActivity(intent)
            finish() // Tutup LogoActivity agar tidak bisa kembali
        }, 2000) // 2000 ms = 2 detik
    }
}
