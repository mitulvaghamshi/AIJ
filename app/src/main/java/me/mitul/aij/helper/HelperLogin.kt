package me.mitul.aij.helper

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import com.readystatesoftware.sqliteasset.SQLiteAssetHelper

class HelperLogin @SuppressLint("SdCardPath") constructor(context: Context?) :
    SQLiteAssetHelper(context, "AIJ_DB.s3db", "/data/data/me.mitul.aij/databases", null, 1) {
    init {
        getReadableDatabase().close()
    }

    fun attemptLogin(email: String, pass: String): Int {
        val query = "SELECT * FROM MST_User WHERE UserName = '$email' AND Password = '$pass'"
        val database = getReadableDatabase()
        val cursor = database.rawQuery(query, null)
        if (cursor.count < 0) return 999
        if (cursor.moveToFirst()) {
            val userName = cursor.getString(cursor.getColumnIndex("UserName"))
            val password = cursor.getString(cursor.getColumnIndex("Password"))
            return if (userName == email && password == pass) cursor.getInt(cursor.getColumnIndex("UserID")) else 999
        }
        cursor.close()
        database.close()
        return 999
    }

    fun insertUser(email: String?, pass: String?) {
        val database = getWritableDatabase()
        val values = ContentValues()
        values.put("UserName", email)
        values.put("Password", pass)
        database.insert("MST_User", null, values)
        database.close()
    }
}
