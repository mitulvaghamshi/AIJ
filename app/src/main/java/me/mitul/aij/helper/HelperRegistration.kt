package me.mitul.aij.helper

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import com.readystatesoftware.sqliteasset.SQLiteAssetHelper
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class HelperRegistration @SuppressLint("SdCardPath") constructor(var context: Context) :
    SQLiteAssetHelper(
        context, "AIJ_DB.s3db", "/data/data/me.mitul.aij/databases", null, 1
    ) {
    fun attemptReg(vararg strings: String): Boolean {
        val query =
            "SELECT * FROM MST_User WHERE UserName = '" + strings[0] + "' AND Password = '" + strings[4] + "'"
        val database = getWritableDatabase()
        val cursor = database.rawQuery(query, null)
        if (!cursor.moveToFirst()) {
            val values = ContentValues()
            //values.put("_id", "null");
            values.put("DeviceID", strings[6])
            values.put("UserName", strings[0])
            values.put("Email", strings[1])
            values.put("Mobile", strings[2])
            values.put("City", strings[3])
            values.put("Password", strings[4])
            values.put("DateOfReg", SimpleDateFormat("dd-MM-yyyy ", Locale.ENGLISH).format(Date()))
            values.put("Extra", "extra")
            database.insert("ACC_Registration", null, values)
            HelperLogin(context).insertUser(strings[0], strings[4])
            return true
        }
        cursor.close()
        database.close()
        return false
    }
}
