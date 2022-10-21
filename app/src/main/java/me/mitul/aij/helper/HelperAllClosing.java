package me.mitul.aij.helper;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import java.util.ArrayList;

import me.mitul.aij.model.AllClosingList;

public class HelperAllClosing extends SQLiteAssetHelper {
    @SuppressLint("SdCardPath")
    public HelperAllClosing(Context context) {
        super(context, "AIJ_DB.s3db", "/data/data/me.mitul.aij/databases", null, 1);
    }

    @SuppressLint({"NewApi", "DefaultLocale"})
    public ArrayList<AllClosingList> selectClosingAll(int collegeId) {
        ArrayList<AllClosingList> list = new ArrayList<>();
        SQLiteDatabase database = getReadableDatabase();
        Cursor cursor = database.rawQuery("SELECT OPENClosing, SEBCClosing, SCClosing, STClosing, EBCClosing, TFWSClosing FROM INS_Cutoff WHERE CollegeId = " + collegeId + ";", null);
        Cursor branchName = database.rawQuery("SELECT BranchProperName FROM INS_Branch WHERE BranchID IN (SELECT BranchID FROM INS_Cutoff WHERE CollegeId = " + collegeId + ");", null);
        if (cursor.moveToFirst() && branchName.moveToFirst()) {
            do {
                AllClosingList closingList = new AllClosingList();
                closingList.setBranchName(branchName.getString(branchName.getColumnIndex("BranchProperName")));
                closingList.setTfwsValue(cursor.getInt(cursor.getColumnIndex("TFWSClosing")));
                closingList.setOpenValue(cursor.getInt(cursor.getColumnIndex("OPENClosing")));
                closingList.setSebcValue(cursor.getInt(cursor.getColumnIndex("SEBCClosing")));
                closingList.setEbcValue(cursor.getInt(cursor.getColumnIndex("EBCClosing")));
                closingList.setScValue(cursor.getInt(cursor.getColumnIndex("SCClosing")));
                closingList.setStValue(cursor.getInt(cursor.getColumnIndex("STClosing")));
                list.add(closingList);
            } while (cursor.moveToNext() && branchName.moveToNext());
        }
        branchName.close();
        cursor.close();
        database.close();
        return list;
    }
}
