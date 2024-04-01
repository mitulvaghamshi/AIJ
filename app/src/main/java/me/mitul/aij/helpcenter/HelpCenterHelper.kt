package me.mitul.aij.helpcenter

import android.content.Context
import me.mitul.aij.common.CommonModel
import me.mitul.aij.utils.Database

class HelpCenterHelper(context: Context, private val db: Database = Database(context)) {
    fun getCities(): ArrayList<String> {
        val list = ArrayList<String>()
        val cursor = db.readableDatabase
            .rawQuery("select distinct City from HelpCenter order by City", null)
        if (cursor.moveToFirst()) do {
            list.add(cursor.getString(cursor.getColumnIndex("City")))
        } while (cursor.moveToNext())
        cursor.close()
        return list
    }

    fun getHelpCenterFor(name: String): ArrayList<CommonModel> {
        val list = ArrayList<CommonModel>()
        val cursor = db.readableDatabase
            .rawQuery(
                "select HelpCenterID, HelpCenterName, Address, City from HelpCenter where City = '$name' order by HelpCenterName",
                null
            )
        if (cursor.moveToFirst()) do {
            val common = CommonModel(
                id = cursor.getString(cursor.getColumnIndex("HelpCenterID")).toInt(),
                name = cursor.getString(cursor.getColumnIndex("HelpCenterName")),
                address = cursor.getString(cursor.getColumnIndex("Address")),
                city = cursor.getString(cursor.getColumnIndex("City"))
            )
            list.add(common)
        } while (cursor.moveToNext())
        cursor.close()
        return list
    }
}
