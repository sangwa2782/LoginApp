package com.ajayam.loginapp.repo

import android.util.Log
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class DataRepository {

    // create Firebase authentication object
    private lateinit var auth: FirebaseAuth



    fun signup(user:String, pass:String) {

        // Initialising auth object
        auth = FirebaseAuth.getInstance()

    }

    fun login(user:String, pass:String) {

        // Initialising auth object
        auth = FirebaseAuth.getInstance()

//        auth.signInWithEmailAndPassword(user, pass).addOnCompleteListener(this) {
//            if (it.isSuccessful) {
//                finish()
//            } else {
//                Toast.makeText(this, "Singed Up Failed!", Toast.LENGTH_SHORT).show()
//                Log.e("TAG", "LoginFailed: "+ it.toString() )
//            }
//        }
//        auth.signInWithEmailAndPassword(user, pass).addOnCompleteListener(this)
    }
}