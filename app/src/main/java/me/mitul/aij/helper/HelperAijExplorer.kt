package me.mitul.aij.helper

import android.annotation.SuppressLint
import android.content.Context
import com.readystatesoftware.sqliteasset.SQLiteAssetHelper
import me.mitul.aij.utils.Constants

class HelperAijExplorer(context: Context?) : SQLiteAssetHelper(
    context, Constants.DB_NAME, Constants.DB_PATH, null, Constants.DB_VERSION
) {
    @SuppressLint("NewApi", "DefaultLocale")
    fun selectHeader(id: Int): String {
        val database = getReadableDatabase()
        val cursor = database.rawQuery("SELECT TitleText FROM MST_Aij WHERE _id = $id", null)
        if (cursor.moveToFirst()) {
            return cursor.getString(cursor.getColumnIndex("TitleText"))
        }
        cursor.close()
        database.close()
        return "AIJ - Under Construction"
    }

    @SuppressLint("NewApi", "DefaultLocale")
    fun selectDetail(id: Int): String {
        val database = getReadableDatabase()
        val cursor = database.rawQuery("SELECT DetailText FROM MST_Aij WHERE _id = $id", null)
        if (cursor.moveToFirst()) {
            return cursor.getString(cursor.getColumnIndex("DetailText"))
        }
        cursor.close()
        database.close()
        return "AIJ - Under Construction"
    }
}
