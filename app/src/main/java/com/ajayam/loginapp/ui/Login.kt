package com.ajayam.loginapp.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ajayam.loginapp.R
import com.ajayam.loginapp.databinding.ActivityLoginBinding

class Login : AppCompatActivity() {
    private lateinit var loginBinding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loginBinding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(loginBinding.root)

        loginBinding.tvCreateAccount.setOnClickListener{
            startActivity(Intent(this, Signup::class.java))
        }


    }
}