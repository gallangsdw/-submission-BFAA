package com.sdwtech.githubuser.helper

import android.database.Cursor
import com.sdwtech.githubuser.data.User
import com.sdwtech.githubuser.db.UserContract.UserColumn.Companion.AVATAR
import com.sdwtech.githubuser.db.UserContract.UserColumn.Companion.USERNAME
import com.sdwtech.githubuser.db.UserContract.UserColumn.Companion._ID

object MappingHelper {

    fun mapCursorToArrayList(userCursor: Cursor?): ArrayList<User> {
        val userList = ArrayList<User>()

        userCursor?.apply {
            while (moveToNext()) {
                val id = getInt(getColumnIndexOrThrow(_ID))
                val username = getString(getColumnIndexOrThrow(USERNAME))
                val imgPhoto = getString(getColumnIndexOrThrow(AVATAR))
                userList.add(User(id = id, login = username, avatar_url = imgPhoto))
            }
        }

        return userList
    }
}