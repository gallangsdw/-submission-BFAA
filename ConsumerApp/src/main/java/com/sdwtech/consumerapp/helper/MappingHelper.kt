package com.sdwtech.consumerapp.helper

import android.database.Cursor
import com.sdwtech.consumerapp.data.User
import com.sdwtech.consumerapp.db.UserContract.UserColumn.Companion.AVATAR
import com.sdwtech.consumerapp.db.UserContract.UserColumn.Companion.COMPANY
import com.sdwtech.consumerapp.db.UserContract.UserColumn.Companion.USERNAME
import com.sdwtech.consumerapp.db.UserContract.UserColumn.Companion._ID

object MappingHelper {

    fun mapCursorToArrayList(userCursor: Cursor?): ArrayList<User> {
        val userList = ArrayList<User>()

        userCursor?.apply {
            while (moveToNext()) {
                val id = getInt(getColumnIndexOrThrow(_ID))
                val username = getString(getColumnIndexOrThrow(USERNAME))
                val Photo = getString(getColumnIndexOrThrow(AVATAR))
                val company = getString(getColumnIndexOrThrow(COMPANY))
                userList.add(User(id = id, login = username, avatar_url = Photo, company = company))
            }
        }

        return userList
    }
}