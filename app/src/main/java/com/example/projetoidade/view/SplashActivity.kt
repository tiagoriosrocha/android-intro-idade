package com.example.projetoidade.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.example.projetoidade.R

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash_activity)

        Handler(Looper.myLooper()!!).postDelayed({
            val intent = Intent(this, CalculaIdadeActivity::class.java)
            startActivity(intent)
            finish()
        }, 5000)
    }
}