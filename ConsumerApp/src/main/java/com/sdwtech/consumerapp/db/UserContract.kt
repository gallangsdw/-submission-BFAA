package com.sdwtech.consumerapp.db

import android.net.Uri
import android.provider.BaseColumns

object UserContract {
    const val AUTHORITY = "com.sdwtech.githubuser"
    const val SCHEME = "content"

    class UserColumn: BaseColumns {
        companion object {
            const val TABLE_NAME = "user"
            const val _ID = "id"
            const val USERNAME = "username"
            const val AVATAR = "avatar"
            const val COMPANY = "company"

            val CONTENT_URI: Uri = Uri.Builder().scheme(SCHEME)
                    .authority(AUTHORITY)
                    .appendPath(TABLE_NAME)
                    .build()
        }
    }
}