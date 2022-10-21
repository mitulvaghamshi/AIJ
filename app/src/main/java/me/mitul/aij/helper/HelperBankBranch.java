package me.mitul.aij.helper;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import java.util.ArrayList;

import me.mitul.aij.model.Common;

public class HelperBankBranch extends SQLiteAssetHelper {
    @SuppressLint("SdCardPath")
    public HelperBankBranch(Context context) {
        super(context, "AIJ_DB.s3db", "/data/data/me.mitul.dankawala/databases", null, 1);
    }

    public ArrayList<String> selectCityForBankBranch() {
        ArrayList<String> list = new ArrayList<String>();
        SQLiteDatabase database = getReadableDatabase();
        Cursor cursor = database.rawQuery("select distinct City from BankBranch order by City", null);
        if (cursor.moveToFirst()) {
            do {
                list.add(cursor.getString(cursor.getColumnIndex("City")));
            } while (cursor.moveToNext());
        }
        cursor.close();
        database.close();
        return list;
    }

    public ArrayList<Common> selectBankBranch(String name) {
        ArrayList<Common> list = new ArrayList<Common>();
        SQLiteDatabase database = getReadableDatabase();
        Cursor cursor = database.rawQuery("select BankBranchID, BankBranchName, Address from BankBranch where City = '" + name + "' order by BankBranchName", null);
        if (cursor.moveToFirst()) do {
            Common common = new Common();
            common.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex("BankBranchID"))));
            common.setName(cursor.getString(cursor.getColumnIndex("BankBranchName")));
            common.setAddress(cursor.getString(cursor.getColumnIndex("Address")));
            list.add(common);
        } while (cursor.moveToNext());
        cursor.close();
        database.close();
        return list;
    }
}
