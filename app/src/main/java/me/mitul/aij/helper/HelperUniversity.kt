package me.mitul.aij.helper

import android.annotation.SuppressLint
import android.content.Context
import com.readystatesoftware.sqliteasset.SQLiteAssetHelper
import me.mitul.aij.model.University

class HelperUniversity @SuppressLint("SdCardPath") constructor(context: Context?) :
    SQLiteAssetHelper(context, "AIJ_DB.s3db", "/data/data/me.mitul.aij/databases", null, 1) {
    @SuppressLint("NewApi", "DefaultLocale")
    fun selectAllUniversity(): ArrayList<University> {
        val list = ArrayList<University>()
        val database = getReadableDatabase()
        val cursor = database.rawQuery(
            "select UniversityID, UniversityName from MST_University order by UniversityName",
            null
        )
        if (cursor.moveToFirst()) do {
            val university = University()
            university.universityID = cursor.getInt(cursor.getColumnIndex("UniversityID"))
            university.universityName = cursor.getString(cursor.getColumnIndex("UniversityName"))
            list.add(university)
        } while (cursor.moveToNext())
        cursor.close()
        database.close()
        return list
    }

    @SuppressLint("NewApi", "DefaultLocale")
    fun selectUniversityByID(id: Int): University {
        val university = University()
        val database = getReadableDatabase()
        val cursor = database.rawQuery(
            "select UniversityID,UniversityName,UniversityShortName,Address,Website,Email,Phone,UniversityType from MST_University where UniversityID=$id",
            null
        )
        if (cursor.moveToFirst()) {
            university.universityID = cursor.getInt(cursor.getColumnIndex("UniversityID"))
            university.universityShortName =
                cursor.getString(cursor.getColumnIndex("UniversityShortName"))
            university.universityName = cursor.getString(cursor.getColumnIndex("UniversityName"))
            university.universityAddress = cursor.getString(cursor.getColumnIndex("Address"))
            university.universityWebsite = cursor.getString(cursor.getColumnIndex("Website"))
            university.universityEmail = cursor.getString(cursor.getColumnIndex("Email"))
            university.universityPhone = cursor.getString(cursor.getColumnIndex("Phone"))
            university.universityType = cursor.getString(cursor.getColumnIndex("UniversityType"))
        }
        cursor.close()
        database.close()
        return university
    }
}
