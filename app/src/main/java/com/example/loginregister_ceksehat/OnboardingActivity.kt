package com.example.loginregister_ceksehat

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button

class OnboardingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_onboarding)

        // Temukan tombol "Sudah punya akun?" dan set onClickListener
        val loginButton = findViewById<Button>(R.id.loginButton)
        loginButton.setOnClickListener {
            // Pindah ke LoginActivity ketika tombol ditekan
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish() // Tutup OnboardingActivity agar tidak bisa kembali
        }

        // Temukan tombol "Daftar" dan set onClickListener
        val registerButton = findViewById<Button>(R.id.registerButton)
        registerButton.setOnClickListener {
            // Pindah ke RegisterActivity ketika tombol ditekan
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
            finish() // Tutup OnboardingActivity agar tidak bisa kembali
        }
    }
}
