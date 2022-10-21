package me.mitul.aij.helper;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

public class HelperAijExplorer extends SQLiteAssetHelper {
    @SuppressLint("SdCardPath")
    public HelperAijExplorer(Context context) {
        super(context, "AIJ_DB.s3db", "/data/data/me.mitul.aij/databases", null, 1);
    }

    @SuppressLint({"NewApi", "DefaultLocale"})
    public String selectHeader(int id) {
        SQLiteDatabase database = getReadableDatabase();
        Cursor cursor = database.rawQuery("SELECT TitleText FROM MST_Aij WHERE _id = " + id, null);
        if (cursor.moveToFirst()) {
            return cursor.getString(cursor.getColumnIndex("TitleText"));
        }
        cursor.close();
        database.close();
        return "AIJ - Under Construction";
    }

    @SuppressLint({"NewApi", "DefaultLocale"})
    public String selectDetail(int id) {
        SQLiteDatabase database = getReadableDatabase();
        Cursor cursor = database.rawQuery("SELECT DetailText FROM MST_Aij WHERE _id = " + id, null);
        if (cursor.moveToFirst()) {
            return cursor.getString(cursor.getColumnIndex("DetailText"));
        }
        cursor.close();
        database.close();
        return "AIJ - Under Construction";
    }
}
