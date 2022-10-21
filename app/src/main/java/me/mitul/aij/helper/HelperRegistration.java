package me.mitul.aij.helper;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class HelperRegistration extends SQLiteAssetHelper {
    Context context;

    @SuppressLint("SdCardPath")
    public HelperRegistration(Context context) {
        super(context, "AIJ_DB.s3db", "/data/data/me.mitul.aij/databases", null, 1);
        this.context = context;
    }

    public boolean attemptReg(String... strings) {
        String query = "SELECT * FROM MST_User WHERE UserName = '" + strings[0] + "' AND Password = '" + strings[4] + "'";
        SQLiteDatabase database = getWritableDatabase();
        final Cursor cursor = database.rawQuery(query, null);
        if (!cursor.moveToFirst()) {
            ContentValues values = new ContentValues();
            //values.put("_id", "null");
            values.put("DeviceID", strings[6]);
            values.put("UserName", strings[0]);
            values.put("Email", strings[1]);
            values.put("Mobile", strings[2]);
            values.put("City", strings[3]);
            values.put("Password", strings[4]);
            values.put("DateOfReg", new SimpleDateFormat("dd-MM-yyyy ", Locale.ENGLISH).format(new Date()));
            values.put("Extra", "extra");
            database.insert("ACC_Registration", null, values);
            new HelperLogin(context).insertUser(strings[0], strings[4]);
            return true;
        }
        cursor.close();
        database.close();
        return false;
    }
}
