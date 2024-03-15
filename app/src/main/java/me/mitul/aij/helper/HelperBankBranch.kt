package me.mitul.aij.helper

import android.annotation.SuppressLint
import android.content.Context
import com.readystatesoftware.sqliteasset.SQLiteAssetHelper
import me.mitul.aij.model.Common

class HelperBankBranch @SuppressLint("SdCardPath") constructor(context: Context?) :
    SQLiteAssetHelper(context, "AIJ_DB.s3db", "/data/data/me.mitul.dankawala/databases", null, 1) {
    fun selectCityForBankBranch(): ArrayList<String> {
        val list = ArrayList<String>()
        val database = getReadableDatabase()
        val cursor = database.rawQuery("select distinct City from BankBranch order by City", null)
        if (cursor.moveToFirst()) {
            do {
                list.add(cursor.getString(cursor.getColumnIndex("City")))
            } while (cursor.moveToNext())
        }
        cursor.close()
        database.close()
        return list
    }

    fun selectBankBranch(name: String): ArrayList<Common> {
        val list = ArrayList<Common>()
        val database = getReadableDatabase()
        val cursor = database.rawQuery(
            "select BankBranchID, BankBranchName, Address from BankBranch where City = '$name' order by BankBranchName",
            null
        )
        if (cursor.moveToFirst()) do {
            val common = Common()
            common.id = cursor.getString(cursor.getColumnIndex("BankBranchID")).toInt()
            common.name = cursor.getString(cursor.getColumnIndex("BankBranchName"))
            common.address = cursor.getString(cursor.getColumnIndex("Address"))
            list.add(common)
        } while (cursor.moveToNext())
        cursor.close()
        database.close()
        return list
    }
}
