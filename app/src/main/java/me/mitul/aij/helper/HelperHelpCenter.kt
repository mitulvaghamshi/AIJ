package me.mitul.aij.helper

import android.annotation.SuppressLint
import android.content.Context
import com.readystatesoftware.sqliteasset.SQLiteAssetHelper
import me.mitul.aij.model.Common

class HelperHelpCenter @SuppressLint("SdCardPath") constructor(context: Context?) :
    SQLiteAssetHelper(context, "AIJ_DB.s3db", "/data/data/me.mitul.aij/databases", null, 1) {
    fun selectCityForHelpCenter(): ArrayList<String> {
        val list = ArrayList<String>()
        val database = getReadableDatabase()
        val cursor = database.rawQuery("select distinct City from HelpCenter order by City", null)
        if (cursor.moveToFirst()) {
            do {
                list.add(cursor.getString(cursor.getColumnIndex("City")))
            } while (cursor.moveToNext())
        }
        cursor.close()
        database.close()
        return list
    }

    fun selectHelpCenter(name: String): ArrayList<Common> {
        val list = ArrayList<Common>()
        val database = getReadableDatabase()
        val cursor = database.rawQuery(
            "select HelpCenterID, HelpCenterName, Address from HelpCenter where City = '$name' order by HelpCenterName",
            null
        )
        if (cursor.moveToFirst()) {
            do {
                val common = Common()
                common.id = cursor.getString(cursor.getColumnIndex("HelpCenterID")).toInt()
                common.name = cursor.getString(cursor.getColumnIndex("HelpCenterName"))
                common.address = cursor.getString(cursor.getColumnIndex("Address"))
                list.add(common)
            } while (cursor.moveToNext())
        }
        cursor.close()
        database.close()
        return list
    }
}
