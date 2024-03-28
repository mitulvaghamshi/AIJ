package me.mitul.aij.helper

import android.content.Context
import com.readystatesoftware.sqliteasset.SQLiteAssetHelper
import me.mitul.aij.model.Branch
import me.mitul.aij.utils.Consts

class HelperBranch(context: Context?) : SQLiteAssetHelper(
    context, Consts.DB_NAME, Consts.DB_PATH, null, Consts.DB_VERSION
) {
    fun getAllBranches(): ArrayList<Branch> {
        val list = ArrayList<Branch>()
        val database = getReadableDatabase()
        val cursor = database.rawQuery(
            "Select BranchID,BranchName,BranchShortName FROM INS_Branch ORDER BY Sequence",
            null
        )
        if (cursor.moveToFirst()) do {
            val id = cursor.getInt(cursor.getColumnIndex("BranchID"))
            val cursor1 = database.rawQuery(
                "SELECT CollegeID FROM INS_Cutoff WHERE BranchID = $id;", null
            )
            if (cursor1.moveToFirst()) {
                var count = 0
                do count++ while (cursor1.moveToNext())

                list += Branch(
                    id = id, collegeNumber = count,
                    name = cursor.getString(cursor.getColumnIndex("BranchName")) + "(" +
                            cursor.getString(cursor.getColumnIndex("BranchShortName")) + ")",
                )
            }
            cursor1.close()
        } while (cursor.moveToNext())

        cursor.close()
        return list
    }
}
