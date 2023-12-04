package com.ajayam.loginapp.util

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ajayam.loginapp.repo.DataRepository
import com.ajayam.loginapp.viewmodels.ViewModelClass
import java.lang.IllegalArgumentException

class ViewModelFactory constructor(private val application: Application, private val repository: DataRepository ): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(ViewModelClass::class.java)) {
            ViewModelClass(this.application, this.repository) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }

}