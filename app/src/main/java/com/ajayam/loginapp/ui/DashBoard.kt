package com.ajayam.loginapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ajayam.loginapp.R
import com.ajayam.loginapp.databinding.ActivityDashBoardBinding

class DashBoard : AppCompatActivity() {
    private lateinit var dashBoardBinding: ActivityDashBoardBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dashBoardBinding = ActivityDashBoardBinding.inflate(layoutInflater)
        setContentView(dashBoardBinding.root)



    }
}