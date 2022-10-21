package me.mitul.aij.helper;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import java.util.ArrayList;

import me.mitul.aij.model.Branch;

public class HelperBranch extends SQLiteAssetHelper {
    @SuppressLint("SdCardPath")
    public HelperBranch(Context context) {
        super(context, "AIJ_DB.s3db", "/data/data/me.mitul.aij/databases", null, 1);
    }

    @SuppressLint({"NewApi", "DefaultLocale"})
    public ArrayList<Branch> selectAllBranch() {
        ArrayList<Branch> list = new ArrayList<>();
        SQLiteDatabase database = getReadableDatabase();
        Cursor cursor = database.rawQuery("Select BranchID,BranchName,BranchShortName FROM INS_Branch ORDER BY Sequence", null);

        if (cursor.moveToFirst()) do {
            Branch branch = new Branch();
            branch.setBranchId(cursor.getInt(cursor.getColumnIndex("BranchID")));
            branch.setBranchName(cursor.getString(cursor.getColumnIndex("BranchName")) + "(" + cursor.getString(cursor.getColumnIndex("BranchShortName")) + ")");
            Cursor cursor1 = database.rawQuery("SELECT CollegeID FROM INS_Cutoff WHERE BranchID = " + branch.getBranchId() + ";", null);
            if (cursor1.moveToFirst()) {
                int count = 0;
                do {
                    count++;
                } while (cursor1.moveToNext());
                branch.setCollegeNumber(count);
            }
            cursor1.close();
            list.add(branch);
        } while (cursor.moveToNext());
        cursor.close();
        database.close();
        return list;
    }
}
