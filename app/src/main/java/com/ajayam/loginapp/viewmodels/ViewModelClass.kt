package com.ajayam.loginapp.viewmodels

import android.app.Application
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ajayam.loginapp.repo.DataRepository
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ViewModelClass(application: Application, private val dataRepository: DataRepository):
    ViewModel() {

    val errorMessage = MutableLiveData<String>()
    val loading = MutableLiveData<Boolean>()
//    val userResponse = MutableLiveData<MovieData>()

    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        onError("Exception handled: ${throwable.localizedMessage}")
    }


    // For Login User
    fun loginUser(user:String, pass:String) {
        viewModelScope.launch(Dispatchers.IO + exceptionHandler) {
            val response = dataRepository.authentication(user, pass)
            withContext(Dispatchers.Main) {
//                if (response.isSuccessful) {
//                    Log.e("onSuccess1", Gson().toJson(response.body()))
//                    userResponse.postValue(response.body())
//                    loading.value = false
//                } else {
//                    Log.e("Error", Gson().toJson(response.message()))
//                    onError("Error : ${response.message()} ")
//                }
            }
        }
    }







    private fun onError(message: String) {
        errorMessage.value = message
        Log.e("TAG", "onError: " + message)
        loading.value = false
    }
}