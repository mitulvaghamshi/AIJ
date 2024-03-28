package me.mitul.aij.helper

import android.content.Context
import com.readystatesoftware.sqliteasset.SQLiteAssetHelper
import me.mitul.aij.utils.Consts

class HelperAijExplorer(context: Context?) : SQLiteAssetHelper(
    context, Consts.DB_NAME, Consts.DB_PATH, null, Consts.DB_VERSION
) {
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
