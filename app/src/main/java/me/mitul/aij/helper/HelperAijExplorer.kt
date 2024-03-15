package me.mitul.aij.helper

import android.annotation.SuppressLint
import android.content.Context
import com.readystatesoftware.sqliteasset.SQLiteAssetHelper

class HelperAijExplorer @SuppressLint("SdCardPath") constructor(context: Context?) :
    SQLiteAssetHelper(context, "AIJ_DB.s3db", "/data/data/me.mitul.aij/databases", null, 1) {
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
