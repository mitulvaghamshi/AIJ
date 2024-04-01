package me.mitul.aij.login

import android.content.ContentValues
import android.provider.Settings
import androidx.core.database.getIntOrNull
import androidx.core.database.getStringOrNull
import me.mitul.aij.utils.Database
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class LoginModel(private val db: Database) {
    private companion object {
        const val TBL_USER = "User"

        const val COL_USER_ID = "user_id"
        const val COL_USERNAME = "username"
        const val COL_PASSWORD = "password"
        const val COL_EMAIL = "email"
        const val COL_PHONE = "phone"
        const val COL_CITY = "city"
        const val COL_DEVICE_ID = "device_id"
        const val COL_DATE_CREATED = "date_created"

        const val DATE_FORMAT = "yyyy-MM-dd"
    }

    object Keys {
        const val KEY_LOGIN_INFO = "login_info"
        const val KEY_USER_ID = COL_USER_ID
        const val KEY_USERNAME = COL_USERNAME
        const val KEY_PASSWORD = COL_PASSWORD
        const val KEY_KEEP_SIGNED = "keep_me_signed"
        const val KEY_IS_REGISTERED = "is_registered"
    }

    fun login(username: String, password: String): Int {
        if (username.isBlank() || password.isBlank()) return -1
        val cursor = db.readableDatabase.query(
            TBL_USER, null,
            "$COL_USERNAME = ? AND $COL_PASSWORD = ?",
            arrayOf(username, password), null, null, null
        )
        if (cursor.count < 0) return -1
        if (cursor.moveToFirst()) {
            val user = cursor.getStringOrNull(cursor.getColumnIndex(COL_USERNAME))
            val pass = cursor.getStringOrNull(cursor.getColumnIndex(COL_PASSWORD))
            if (user != username || pass != password) return -1
            return cursor.getIntOrNull(cursor.getColumnIndex(COL_USER_ID)) ?: -1
        }
        cursor.close()
        return -1
    }

    fun register(
        username: String, password: String, email: String, phone: String, city: String,
    ): Long {
        if (login(username, password) != -1) return -1L
        val values = ContentValues().apply {
            putNull(COL_USER_ID)
            put(COL_USERNAME, username)
            put(COL_PASSWORD, password)
            put(COL_EMAIL, email)
            put(COL_PHONE, phone)
            put(COL_CITY, city)
            put(COL_DEVICE_ID, Settings.Secure.ANDROID_ID)
            put(COL_DATE_CREATED, SimpleDateFormat(DATE_FORMAT, Locale.ENGLISH).format(Date()))
        }
        return db.writableDatabase.insert(TBL_USER, null, values)
    }
}
