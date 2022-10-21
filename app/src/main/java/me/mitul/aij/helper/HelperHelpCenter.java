package me.mitul.aij.helper;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import java.util.ArrayList;

import me.mitul.aij.model.Common;

public class HelperHelpCenter extends SQLiteAssetHelper {
    @SuppressLint("SdCardPath")
    public HelperHelpCenter(Context context) {
        super(context, "AIJ_DB.s3db", "/data/data/me.mitul.aij/databases", null, 1);
    }

    public ArrayList<String> selectCityForHelpCenter() {
        ArrayList<String> list = new ArrayList<>();
        SQLiteDatabase database = getReadableDatabase();
        Cursor cursor = database.rawQuery("select distinct City from HelpCenter order by City", null);
        if (cursor.moveToFirst()) {
            do {
                list.add(cursor.getString(cursor.getColumnIndex("City")));
            } while (cursor.moveToNext());
        }
        cursor.close();
        database.close();
        return list;
    }

    public ArrayList<Common> selectHelpCenter(String name) {
        ArrayList<Common> list = new ArrayList<>();
        SQLiteDatabase database = getReadableDatabase();
        Cursor cursor = database.rawQuery("select HelpCenterID, HelpCenterName, Address from HelpCenter where City = '" + name + "' order by HelpCenterName", null);
        if (cursor.moveToFirst()) {
            do {
                Common common = new Common();
                common.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex("HelpCenterID"))));
                common.setName(cursor.getString(cursor.getColumnIndex("HelpCenterName")));
                common.setAddress(cursor.getString(cursor.getColumnIndex("Address")));
                list.add(common);
            } while (cursor.moveToNext());
        }
        cursor.close();
        database.close();
        return list;
    }
}
