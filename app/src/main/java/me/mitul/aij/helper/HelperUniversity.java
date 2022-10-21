package me.mitul.aij.helper;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import java.util.ArrayList;

import me.mitul.aij.model.University;

public class HelperUniversity extends SQLiteAssetHelper {
    @SuppressLint("SdCardPath")
    public HelperUniversity(Context context) {
        super(context, "AIJ_DB.s3db", "/data/data/me.mitul.aij/databases", null, 1);
    }

    @SuppressLint({"NewApi", "DefaultLocale"})
    public ArrayList<University> selectAllUniversity() {
        ArrayList<University> list = new ArrayList<University>();
        SQLiteDatabase database = getReadableDatabase();
        Cursor cursor = database.rawQuery("select UniversityID, UniversityName from MST_University order by UniversityName", null);
        if (cursor.moveToFirst()) do {
            University university = new University();
            university.setUniversityID(cursor.getInt(cursor.getColumnIndex("UniversityID")));
            university.setUniversityName(cursor.getString(cursor.getColumnIndex("UniversityName")));
            list.add(university);
        } while (cursor.moveToNext());
        cursor.close();
        database.close();
        return list;
    }

    @SuppressLint({"NewApi", "DefaultLocale"})
    public University selectUniversityByID(int id) {
        University university = new University();
        SQLiteDatabase database = getReadableDatabase();
        Cursor cursor = database.rawQuery("select UniversityID,UniversityName,UniversityShortName,Address,Website,Email,Phone,UniversityType from MST_University where UniversityID=" + id, null);
        if (cursor.moveToFirst()) {
            university.setUniversityID(cursor.getInt(cursor.getColumnIndex("UniversityID")));
            university.setUniversityShortName(cursor.getString(cursor.getColumnIndex("UniversityShortName")));
            university.setUniversityName(cursor.getString(cursor.getColumnIndex("UniversityName")));
            university.setUniversityAddress(cursor.getString(cursor.getColumnIndex("Address")));
            university.setUniversityWebsite(cursor.getString(cursor.getColumnIndex("Website")));
            university.setUniversityEmail(cursor.getString(cursor.getColumnIndex("Email")));
            university.setUniversityPhone(cursor.getString(cursor.getColumnIndex("Phone")));
            university.setUniversityType(cursor.getString(cursor.getColumnIndex("UniversityType")));
        }
        cursor.close();
        database.close();
        return university;
    }
}
