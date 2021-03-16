package com.sdwtech.githubuser.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class User(
        var username: String = "",
        var name: String = "",
        var location: String = "",
        var repository: Int = 0,
        var company: String = "",
        var followers: Int = 0,
        var following: Int = 0,
        var photo: Int = 0
): Parcelable
