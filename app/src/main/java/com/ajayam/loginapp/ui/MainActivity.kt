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
import androidx.recyclerview.widget.GridLayoutManager
import com.ajayam.loginapp.databinding.ActivityMainBinding
import com.ajayam.loginapp.repo.DataRepository
import com.ajayam.loginapp.util.ViewModelFactory
import com.ajayam.loginapp.viewmodels.ViewModelClass

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: ViewModelClass

    private var progressBar: ProgressBar?= null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initialize()


    }

    private fun initialize() {
        binding.submitLogin.setOnClickListener(this)


        val dataRepository = DataRepository()
        viewModel = ViewModelProvider(this, ViewModelFactory(Application(), dataRepository)).get(
            ViewModelClass::class.java
        )
    }

    override fun onClick(v: View?) {
        when (v) {
            binding.submitLogin -> {
                LoginUser()
            }

        }
    }

    //get user
    private fun LoginUser() {

        //call getUser in view model


        val user = binding.userName.text.toString()
        val pass = binding.password.text.toString()

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


}