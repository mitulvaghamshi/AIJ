package me.mitul.aij.helpers

import android.content.ContentValues
import android.provider.Settings
import me.mitul.aij.utils.Database
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class AuthHelper(private val db: Database) {
    companion object {
        private const val TBL_USER = "User"

        const val KEY_LOGIN_INFO = "shared_pref_login_info"
        const val KEY_KEEP_SIGNED = "keep_me_signed"

        const val COL_USER_ID = "user_id"
        const val COL_USERNAME = "username"
        const val COL_PASSWORD = "password"
        const val COL_EMAIL = "email"
        const val COL_PHONE = "phone"
        const val COL_CITY = "city"

        private const val COL_DEVICE_ID = "device_id"
        private const val COL_DATE_CREATED = "date_created"

        private const val DATE_FORMAT = "yyyy-MM-dd"
    }

    private object Sql {
        const val LOGIN_WITH = """
            SELECT  $COL_USER_ID, $COL_USERNAME, $COL_PASSWORD
            FROM    $TBL_USER
            WHERE   $COL_USERNAME = ? AND $COL_PASSWORD = ?;
        """
    }

    fun login(values: ContentValues): Long {
        var userId = -1L
        val username = values.getAsString(COL_USERNAME)
        val password = values.getAsString(COL_PASSWORD)
        if (username.isBlank() || password.isBlank()) return userId
        val cursor = db.readableDatabase
            .rawQuery(Sql.LOGIN_WITH, arrayOf(username, password))
        if (cursor.moveToFirst()) {
            val user = cursor.getString(cursor.getColumnIndexOrThrow(COL_USERNAME))
            val pass = cursor.getString(cursor.getColumnIndexOrThrow(COL_PASSWORD))
            if (user != username || pass != password) return userId
            userId = cursor.getLong(cursor.getColumnIndexOrThrow(COL_USER_ID))
        }
        cursor.close()
        return userId
    }

    fun register(values: ContentValues) = if (login(values) != -1L) -1L else
        db.writableDatabase.insert(TBL_USER, null, values.apply {
            putNull(COL_USER_ID)
            put(COL_DEVICE_ID, Settings.Secure.ANDROID_ID)
            put(COL_DATE_CREATED, SimpleDateFormat(DATE_FORMAT, Locale.ENGLISH).format(Date()))
        })
}
