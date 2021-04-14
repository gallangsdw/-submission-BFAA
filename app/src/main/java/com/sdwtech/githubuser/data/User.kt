package com.sdwtech.githubuser.data

import android.os.Parcelable

import kotlinx.parcelize.Parcelize

@Parcelize
data class User(
        var id: Int = 0,
        var login: String = "",
        var name: String = "",
        var location: String = "",
        var public_repos: Int = 0,
        var company: String = "",
        var followers: Int = 0,
        var following: Int = 0,
        var avatar_url: String = ""
): Parcelable
