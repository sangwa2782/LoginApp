package com.ajayam.loginapp.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.ajayam.loginapp.R

class Welcom : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcom)

        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this, Login::class.java))
            finish()
        }, 3000)
    }
}