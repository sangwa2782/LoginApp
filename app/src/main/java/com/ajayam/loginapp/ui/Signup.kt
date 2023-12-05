package com.ajayam.loginapp.ui

import android.app.Application
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.ajayam.loginapp.databinding.ActivityMainBinding
import com.ajayam.loginapp.repo.DataRepository
import com.ajayam.loginapp.util.ViewModelFactory
import com.ajayam.loginapp.viewmodels.ViewModelClass
import com.google.firebase.auth.FirebaseAuth
import com.google.gson.Gson

class Signup : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: ViewModelClass

    private var progressBar: ProgressBar?= null


    // create Firebase authentication object
    private lateinit var auth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)



        initialize()



    }

    private fun initialize() {
        binding.btnSubmit.setOnClickListener(this)

        // Initialising auth object
        auth = FirebaseAuth.getInstance()

        val dataRepository = DataRepository()
        viewModel = ViewModelProvider(this, ViewModelFactory(Application(), dataRepository)).get(
            ViewModelClass::class.java
        )


    }

    override fun onClick(v: View?) {
        when (v) {
            binding.btnSubmit -> {
                loginUser()
            }

        }
    }

    //get user
    private fun loginUser() {

        //call getUser in view model


        val user = binding.etUser.text.toString()
        val pass = binding.etPassword.text.toString()

        viewModel.loginUser(user, pass)


        //observing view model live data
//        viewModel.userResponse.observe(this) {
//            if (it != null) {
//                Toast.makeText(this, "Data fetched successfully", Toast.LENGTH_SHORT).show()
//                Log.e("TAG", "getRepoData: " + Gson().toJson(it.Search[1].Title))
//                binding.ryclrMain.layoutManager = GridLayoutManager(this, 2)
//                recyclerviewAdapter = MainSearchAdapter()
//                binding.ryclrMain.adapter = recyclerviewAdapter
//                recyclerviewAdapter.setUpdateData(it.Search)
//            }
//        }

        // If all credential are correct
        // We call createUserWithEmailAndPassword
        // using auth object and pass the
        // email and pass in it.
        auth.signInWithEmailAndPassword(user, pass).addOnCompleteListener(this) {
            if (it.isSuccessful) {
                Toast.makeText(this, "Successfully Singed Up", Toast.LENGTH_SHORT).show()
                Log.e("TAG", "LoginUser: "+ it.toString() )
                finish()
            } else {
                Toast.makeText(this, "Singed Up Failed!", Toast.LENGTH_SHORT).show()
                Log.e("TAG", "LoginFailed: "+ it.toString() )
            }
        }


        //observing errors
        viewModel.errorMessage.observe(this) {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
            Log.e("TAG", "errorMessage: ")
        }

        //observing process
        viewModel.loading.observe(this, Observer {
            if (it) {
                //show loader
                progressBar?.visibility = View.VISIBLE
            } else {
                //hide loader
                progressBar?.visibility = View.INVISIBLE
            }

        })

    }

    private fun signUp() {

        //call getUser in view model


        val user = binding.etUser.text.toString()
        val pass = binding.etPassword.text.toString()

        viewModel.loginUser(user, pass)


        //observing view model live data
//        viewModel.userResponse.observe(this) {
//            if (it != null) {
//                Toast.makeText(this, "Data fetched successfully", Toast.LENGTH_SHORT).show()
//                Log.e("TAG", "getRepoData: " + Gson().toJson(it.Search[1].Title))
//                binding.ryclrMain.layoutManager = GridLayoutManager(this, 2)
//                recyclerviewAdapter = MainSearchAdapter()
//                binding.ryclrMain.adapter = recyclerviewAdapter
//                recyclerviewAdapter.setUpdateData(it.Search)
//            }
//        }

        // check pass
        if (user.isBlank() || pass.isBlank()) {
            Toast.makeText(this, "Email and Password can't be blank", Toast.LENGTH_SHORT).show()
            return
        }

//        if (pass != confirmPassword) {
//            Toast.makeText(this, "Password and Confirm Password do not match", Toast.LENGTH_SHORT)
//                .show()
//            return
//        }

        // If all credential are correct
        // We call createUserWithEmailAndPassword
        // using auth object and pass the
        // email and pass in it.
        auth.createUserWithEmailAndPassword(user, pass).addOnCompleteListener(this) {
            if (it.isSuccessful) {
                Toast.makeText(this, "Successfully Singed Up", Toast.LENGTH_SHORT).show()
                Log.e("TAG", "Signup: "+ it.toString() )

                finish()
            } else {
                Toast.makeText(this, "Singed Up Failed!", Toast.LENGTH_SHORT).show()
                Log.e("TAG", "SignupFailed: "+ Gson().toJson(it) )

            }
        }


//        //observing errors
//        viewModel.errorMessage.observe(this) {
//            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
//            Log.e("TAG", "errorMessage: ")
//        }
//
//        //observing process
//        viewModel.loading.observe(this, Observer {
//            if (it) {
//                //show loader
//                progressBar?.visibility = View.VISIBLE
//            } else {
//                //hide loader
//                progressBar?.visibility = View.INVISIBLE
//            }
//
//        })

    }

    //1. field validation
    //2. follow architecture
    //3. in signup successful response  - show toast message "User Created successfully"
    //4. in signup failed response - show toast message "User signup failed"

    //5. in login successful response - show toast message "User login successfully"
    //6. in login failed response - show toast message "User login failed"



}