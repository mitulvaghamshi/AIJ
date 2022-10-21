package me.mitul.aij.helper;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import me.mitul.aij.model.SplashScreen;

public class HelperSplashScreen extends SQLiteAssetHelper {
    public HelperSplashScreen(Context context) {
        super(context, "AIJ_DB.s3db", "/data/data/me.mitul.aij/databases", null, 1);
    }

    public SplashScreen selectSplashTextData(int id) {
        SQLiteDatabase database = getReadableDatabase();
        Cursor cursor = database.rawQuery("SELECT SplashText, TextSize, TextColor FROM MST_Splash WHERE _id = " + id, null);
        SplashScreen splashScreen = new SplashScreen();
        if (cursor.moveToFirst()) {
            splashScreen.setText(cursor.getString(cursor.getColumnIndex("SplashText")));
            splashScreen.setTextSize(cursor.getString(cursor.getColumnIndex("TextSize")));
            splashScreen.setTextColor(cursor.getString(cursor.getColumnIndex("TextColor")));
            return splashScreen;
        }
        cursor.close();
        database.close();
        return null;
    }
}
