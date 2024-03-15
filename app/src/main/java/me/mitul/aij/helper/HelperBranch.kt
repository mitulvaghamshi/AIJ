package me.mitul.aij.helper

import android.annotation.SuppressLint
import android.content.Context
import com.readystatesoftware.sqliteasset.SQLiteAssetHelper
import me.mitul.aij.model.Branch

class HelperBranch @SuppressLint("SdCardPath") constructor(context: Context?) :
    SQLiteAssetHelper(context, "AIJ_DB.s3db", "/data/data/me.mitul.aij/databases", null, 1) {
    @SuppressLint("NewApi", "DefaultLocale")
    fun selectAllBranch(): ArrayList<Branch> {
        val list = ArrayList<Branch>()
        val database = getReadableDatabase()
        val cursor = database.rawQuery(
            "Select BranchID,BranchName,BranchShortName FROM INS_Branch ORDER BY Sequence",
            null
        )
        if (cursor.moveToFirst()) do {
            val branch = Branch()
            branch.branchId = cursor.getInt(cursor.getColumnIndex("BranchID"))
            branch.branchName =
                cursor.getString(cursor.getColumnIndex("BranchName")) + "(" + cursor.getString(
                    cursor.getColumnIndex("BranchShortName")
                ) + ")"
            val cursor1 = database.rawQuery(
                "SELECT CollegeID FROM INS_Cutoff WHERE BranchID = " + branch.branchId + ";",
                null
            )
            if (cursor1.moveToFirst()) {
                var count = 0
                do {
                    count++
                } while (cursor1.moveToNext())
                branch.collegeNumber = count
            }
            cursor1.close()
            list.add(branch)
        } while (cursor.moveToNext())
        cursor.close()
        database.close()
        return list
    }
}
