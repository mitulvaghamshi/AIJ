package me.mitul.aij.bankbranch

import android.content.Context
import me.mitul.aij.common.CommonModel
import me.mitul.aij.utils.Database

class BankBranchHelper(context: Context, private val db: Database = Database(context)) {
    fun selectCityForBankBranch(): ArrayList<String> {
        val list = ArrayList<String>()
        val database = db.readableDatabase
        val cursor = database.rawQuery("select distinct City from BankBranch order by City", null)
        if (cursor.moveToFirst()) do {
            list.add(cursor.getString(cursor.getColumnIndex("City")))
        } while (cursor.moveToNext())
        cursor.close()
        database.close()
        return list
    }

    fun getBankBranchesFor(name: String): ArrayList<CommonModel> {
        val list = ArrayList<CommonModel>()
        val database = db.readableDatabase
        val cursor = database.rawQuery(
            "select BankBranchID, BankBranchName, Address from BankBranch where City = '$name' order by BankBranchName",
            null
        )
        if (cursor.moveToFirst()) do list += CommonModel(
            id = cursor.getString(cursor.getColumnIndex("BankBranchID")).toInt(),
            name = cursor.getString(cursor.getColumnIndex("BankBranchName")),
            address = cursor.getString(cursor.getColumnIndex("Address")),
            city = cursor.getString(cursor.getColumnIndex("City")),
        ) while (cursor.moveToNext())
        cursor.close()
        database.close()
        return list
    }
}
