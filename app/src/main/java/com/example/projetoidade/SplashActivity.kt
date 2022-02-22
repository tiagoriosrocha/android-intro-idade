package com.example.projetoidade

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper

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