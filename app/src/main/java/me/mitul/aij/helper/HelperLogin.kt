package me.mitul.aij.helper

import android.content.ContentValues
import android.content.Context
import androidx.core.database.getIntOrNull
import androidx.core.database.getStringOrNull
import com.readystatesoftware.sqliteasset.SQLiteAssetHelper
import me.mitul.aij.utils.Consts

class HelperLogin(context: Context?) : SQLiteAssetHelper(
    context, Consts.DB_NAME, Consts.DB_PATH, null, Consts.DB_VERSION
) {
    private object Keys {
        const val DB_TBL_NAME = "User"
        const val DB_COL_USER_ID = "user_id"
        const val DB_COL_USERNAME = "username"
        const val DB_COL_PASSWORD = "password"
    }

    fun login(username: String, password: String): Int {
        val cursor = readableDatabase.query(
            Keys.DB_TBL_NAME, null,
            "${Keys.DB_COL_USERNAME}= ? AND ${Keys.DB_COL_PASSWORD} = ?",
            arrayOf(username, password), null, null, null
        )

        if (cursor.count < 0) return -1
        if (cursor.moveToFirst()) {
            val user = cursor.getStringOrNull(cursor.getColumnIndex(Keys.DB_COL_USERNAME))
            val pass = cursor.getStringOrNull(cursor.getColumnIndex(Keys.DB_COL_PASSWORD))
            if (user != username || pass != password) return -1
            return cursor.getIntOrNull(cursor.getColumnIndex(Keys.DB_COL_USER_ID)) ?: -1
        }
        cursor.close()
        return -1
    }
}
