package me.mitul.aij.aij

import android.content.Context
import me.mitul.aij.utils.Database

class HelperAijExplorer(context: Context, private val db: Database = Database(context)) {
    fun selectHeader(id: Int): String {
        val database = db.readableDatabase
        val cursor = database.rawQuery("SELECT TitleText FROM MST_Aij WHERE _id = $id", null)
        if (cursor.moveToFirst()) {
            return cursor.getString(cursor.getColumnIndex("TitleText"))
        }
        cursor.close()
        database.close()
        return "AIJ - Under Construction"
    }

    fun selectDetail(id: Int): String {
        val database = db.readableDatabase
        val cursor = database.rawQuery("SELECT DetailText FROM MST_Aij WHERE _id = $id", null)
        if (cursor.moveToFirst()) {
            return cursor.getString(cursor.getColumnIndex("DetailText"))
        }
        cursor.close()
        database.close()
        return "AIJ - Under Construction"
    }
}
