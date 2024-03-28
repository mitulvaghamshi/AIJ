package me.mitul.aij.helper

import android.content.Context
import com.readystatesoftware.sqliteasset.SQLiteAssetHelper
import me.mitul.aij.model.Common
import me.mitul.aij.utils.Consts

class HelperBankBranch(context: Context?) : SQLiteAssetHelper(
    context, Consts.DB_NAME, Consts.DB_PATH, null, Consts.DB_VERSION
) {
    fun selectCityForBankBranch(): ArrayList<String> {
        val list = ArrayList<String>()
        val database = getReadableDatabase()
        val cursor = database.rawQuery("select distinct City from BankBranch order by City", null)
        if (cursor.moveToFirst()) do {
            list.add(cursor.getString(cursor.getColumnIndex("City")))
        } while (cursor.moveToNext())
        cursor.close()
        database.close()
        return list
    }

    fun getBankBranchesFor(name: String): ArrayList<Common> {
        val list = ArrayList<Common>()
        val database = getReadableDatabase()
        val cursor = database.rawQuery(
            "select BankBranchID, BankBranchName, Address from BankBranch where City = '$name' order by BankBranchName",
            null
        )
        if (cursor.moveToFirst()) do list += Common(
            cursor.getString(cursor.getColumnIndex("BankBranchID")).toInt(),
            cursor.getString(cursor.getColumnIndex("BankBranchName")),
            cursor.getString(cursor.getColumnIndex("Address")),
        ) while (cursor.moveToNext())
        cursor.close()
        database.close()
        return list
    }
}
