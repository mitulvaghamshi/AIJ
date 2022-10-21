package me.mitul.aij.helper;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

public class HelperLogin extends SQLiteAssetHelper {
    @SuppressLint("SdCardPath")
    public HelperLogin(Context context) {
        super(context, "AIJ_DB.s3db", "/data/data/me.mitul.aij/databases", null, 1);
        getReadableDatabase().close();
    }

    public int attemptLogin(String email, String pass) {
        String query = "SELECT * FROM MST_User WHERE UserName = '" + email + "' AND Password = '" + pass + "'";
        SQLiteDatabase database = getReadableDatabase();
        Cursor cursor = database.rawQuery(query, null);
        if (cursor.getCount() < 0) return 999;
        if (cursor.moveToFirst()) {
            String userName = cursor.getString(cursor.getColumnIndex("UserName"));
            String password = cursor.getString(cursor.getColumnIndex("Password"));
            return userName.equals(email) && password.equals(pass) ? cursor.getInt(cursor.getColumnIndex("UserID")) : 999;
        }
        cursor.close();
        database.close();
        return 999;
    }

    public void insertUser(String email, String pass) {
        SQLiteDatabase database = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("UserName", email);
        values.put("Password", pass);
        database.insert("MST_User", null, values);
        database.close();
    }
}
