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
    @Headers("Authorization: token c0e01df99948941d796c58cb55eddfa069558ccb")
    suspend fun getUser(): Response<ArrayList<User>>

    @GET("search/users")
    @Headers("Authorization: token c0e01df99948941d796c58cb55eddfa069558ccb")
    suspend fun searchUser(@Query("q") username: String): Response<ResponseUser>

    @GET("users/{username}")
    @Headers("Authorization: token c0e01df99948941d796c58cb55eddfa069558ccb")
    suspend fun getDetailUser(@Path("username") username: String): Response<User>

    @GET("users/{username}/followers")
    @Headers("Authorization: token c0e01df99948941d796c58cb55eddfa069558ccb")
    suspend fun getFollowers(@Path("username") username: String): Response<ArrayList<User>>

    @GET("users/{username}/following")
    @Headers("Authorization: token c0e01df99948941d796c58cb55eddfa069558ccb")
    suspend fun getFollowing(@Path("username") username: String): Response<ArrayList<User>>
}