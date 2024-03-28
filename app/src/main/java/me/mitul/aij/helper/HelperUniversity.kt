package me.mitul.aij.helper

import android.annotation.SuppressLint
import android.content.Context
import com.readystatesoftware.sqliteasset.SQLiteAssetHelper
import me.mitul.aij.model.University
import me.mitul.aij.utils.Consts

class HelperUniversity(context: Context?) : SQLiteAssetHelper(
    context, Consts.DB_NAME, Consts.DB_PATH, null, Consts.DB_VERSION
) {
    @SuppressLint("NewApi", "DefaultLocale")
    fun getUniversities(): ArrayList<University> {
        val list = ArrayList<University>()
        val database = getReadableDatabase()
        val cursor = database.rawQuery(
            "select UniversityID, UniversityName from MST_University order by UniversityName",
            null
        )
        if (cursor.moveToFirst()) do {
            val university = University()
            university.id = cursor.getInt(cursor.getColumnIndex("UniversityID"))
            university.name = cursor.getString(cursor.getColumnIndex("UniversityName"))
            list.add(university)
        } while (cursor.moveToNext())
        cursor.close()
        database.close()
        return list
    }

    @SuppressLint("NewApi", "DefaultLocale")
    fun getUniversityBy(id: String?): University {
        val university = University()
        val database = getReadableDatabase()
        val cursor = database.rawQuery(
            "select UniversityID,UniversityName,UniversityShortName,Address,Website,Email,Phone,UniversityType from MST_University where UniversityID=$id",
            null
        )
        if (cursor.moveToFirst()) {
            university.id = cursor.getInt(cursor.getColumnIndex("UniversityID"))
            university.initials =
                cursor.getString(cursor.getColumnIndex("UniversityShortName"))
            university.name = cursor.getString(cursor.getColumnIndex("UniversityName"))
            university.address = cursor.getString(cursor.getColumnIndex("Address"))
            university.website = cursor.getString(cursor.getColumnIndex("Website"))
            university.email = cursor.getString(cursor.getColumnIndex("Email"))
            university.phone = cursor.getString(cursor.getColumnIndex("Phone"))
            university.type = cursor.getString(cursor.getColumnIndex("UniversityType"))
        }
        cursor.close()
        database.close()
        return university
    }
}
