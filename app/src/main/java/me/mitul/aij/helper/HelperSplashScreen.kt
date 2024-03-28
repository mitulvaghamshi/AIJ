package me.mitul.aij.helper

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import androidx.core.database.getIntOrNull
import androidx.core.database.getStringOrNull
import com.readystatesoftware.sqliteasset.SQLiteAssetHelper
import me.mitul.aij.model.Splash
import me.mitul.aij.utils.Consts

class HelperSplashScreen(context: Context?) : SQLiteAssetHelper(
    context, Consts.DB_NAME, Consts.DB_PATH, null, Consts.DB_VERSION
) {
    private val database: SQLiteDatabase = getReadableDatabase()

    private object Keys {
        const val DB_TBL_NAME = "Splash"
        const val DB_COL_ID = "id"
        const val DB_COL_CONTENT = "content"
    }

    fun getSplashContent(): MutableList<Splash> {
        val cursor = database.rawQuery("SELECT * FROM ${Keys.DB_TBL_NAME}", null)
        val items = mutableListOf<Splash>()
        while (cursor.moveToNext()) {
            items += Splash(
                id = cursor.getIntOrNull(cursor.getColumnIndex(Keys.DB_COL_ID)) ?: 0,
                text = cursor.getStringOrNull(cursor.getColumnIndex(Keys.DB_COL_CONTENT)) ?: ""
            )
        }

        cursor.close()
        database.close()
        return items
    }
}
