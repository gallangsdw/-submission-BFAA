package com.sdwtech.githubuser.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sdwtech.githubuser.data.User
import com.sdwtech.githubuser.data.network.ApiClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetailViewModel: ViewModel() {

    private val user = MutableLiveData<User>()

    fun setData(login: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = ApiClient.instance.getDetail(login)
            if(response.isSuccessful) {
                user.postValue(response.body())
                Log.d("MainViewModel", response.body().toString())
            }
        }
    }

    fun getData(): LiveData<User> = user

}