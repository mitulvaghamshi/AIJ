package me.mitul.aij.helper

import android.content.ContentValues
import android.content.Context
import androidx.core.database.getIntOrNull
import com.readystatesoftware.sqliteasset.SQLiteAssetHelper
import me.mitul.aij.utils.Consts
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class HelperRegistration(val context: Context?) : SQLiteAssetHelper(
    context, Consts.DB_NAME, Consts.DB_PATH, null, Consts.DB_VERSION
) {
    private object Keys {
        const val DB_TBL_NAME = "Registration"
        const val DB_COL_USER_ID = "user_id"
        const val DB_COL_USERNAME = "username"
        const val DB_COL_EMAIL = "email"
        const val DB_COL_PHONE = "phone"
        const val DB_COL_CITY = "city"
        const val DB_COL_PASSWORD = "password"
        const val DB_COL_DEVICE_ID = "device_id"
        const val DB_COL_DATE_CREATED = "date_created"
        const val DATE_FORMAT = "dd-MM-yyyy"
    }

    fun register(
        username: String,
        email: String,
        phone: String,
        city: String,
        password: String,
        deviceId: String,
    ): Long {
        val cursor = readableDatabase.query(
            Keys.DB_TBL_NAME, null,
            "${Keys.DB_COL_USERNAME}= ? AND ${Keys.DB_COL_PASSWORD} = ?",
            arrayOf(username, password), null, null, null
        )
        if (cursor.count < 0) return -1L

        val id = (cursor.getIntOrNull(cursor.getColumnIndex(Keys.DB_COL_USER_ID)) ?: 0) + 1
        val now = SimpleDateFormat(Keys.DATE_FORMAT, Locale.ENGLISH).format(Date())
        cursor.close()

        val values = ContentValues().also {
            it.put(Keys.DB_COL_USER_ID, id)
            it.put(Keys.DB_COL_EMAIL, email)
            it.put(Keys.DB_COL_PHONE, phone)
            it.put(Keys.DB_COL_CITY, city)
            it.put(Keys.DB_COL_DEVICE_ID, deviceId)
            it.put(Keys.DB_COL_DATE_CREATED, now)
        }

        val status = writableDatabase.insert(Keys.DB_TBL_NAME, null, values)
        if (status == -1L) return status
        return insertUser(id, username, password)
    }

    private fun insertUser(userId: Int, username: String, password: String): Long {
        val values = ContentValues().also {
            it.put(Keys.DB_COL_USER_ID, userId)
            it.put(Keys.DB_COL_USERNAME, username)
            it.put(Keys.DB_COL_PASSWORD, password)
        }
        return writableDatabase.insert(Keys.DB_TBL_NAME, null, values)
    }
}
