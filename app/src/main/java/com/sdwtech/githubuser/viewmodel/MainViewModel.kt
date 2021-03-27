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

class MainViewModel: ViewModel() {

    private val listUser = MutableLiveData<ArrayList<User>>()
    private val searchUser = MutableLiveData<ArrayList<User>>()

            fun setData() {
                viewModelScope.launch(Dispatchers.IO) {
                    val response = ApiClient.instance.getUser()
                    if(response.isSuccessful) {
                        listUser.postValue(response.body())
                        Log.d("MainViewModel", response.body().toString())
                    }
                }
            }

    fun getData(): LiveData<ArrayList<User>> = listUser

    fun setSearch(login: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = ApiClient.instance.searchUser(login)
            if (response.isSuccessful) {
                searchUser.postValue(response.body()?.items)
            }
        }
    }

    fun getSearch(): LiveData<ArrayList<User>> = searchUser
}