package com.sdwtech.githubuser.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.sdwtech.githubuser.db.UserContract.UserColumn.Companion.TABLE_NAME

internal class DatabaseHelper(context: Context): SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(SQL_CREATE_TABLE_NOTE)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }

    companion object {
        private const val DATABASE_NAME = "dbgithubapp"
        private const val DATABASE_VERSION = 1
        private const val SQL_CREATE_TABLE_NOTE = "CREATE TABLE $TABLE_NAME" +
                "(${UserContract.UserColumn._ID} INTEGER PRIMARY KEY," +
                "${UserContract.UserColumn.USERNAME} TEXT NOT NULL," +
                "${UserContract.UserColumn.AVATAR} TEXT NOT NULL," +
                "${UserContract.UserColumn.COMPANY} TEXT NOT NULL)"
    }


}