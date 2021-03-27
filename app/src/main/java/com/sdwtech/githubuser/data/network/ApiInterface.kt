package com.sdwtech.githubuser.data.network

import com.sdwtech.githubuser.data.ResponseUser
import com.sdwtech.githubuser.data.User
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiInterface {
    @GET("users")
    suspend fun getUser(): Response<ArrayList<User>>

    @GET("search/users")
    suspend fun searchUser(@Query("q") username: String): Response<ResponseUser>

    @GET("users/{username}")
    suspend fun getDetail(@Path("username") username: String): Response<User>

    @GET("users/{username}/followers")
    suspend fun getFollowers(@Path("username") username: String): Response<ArrayList<User>>

    @GET("users/{username}/following")
    suspend fun getFollowing(@Path("username") username: String): Response<ArrayList<User>>
}