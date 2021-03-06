package com.sdwtech.githubuser.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sdwtech.githubuser.data.User
import com.sdwtech.githubuser.data.network.ApiClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FollowersViewModel: ViewModel() {
    private val listFollowers = MutableLiveData<ArrayList<User>>()

    fun setData(login: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = ApiClient.instance.getFollowers(login)
            if (response.isSuccessful) {
                listFollowers.postValue(response.body())
            }
        }
    }

    fun getData(): LiveData<ArrayList<User>> = listFollowers
}