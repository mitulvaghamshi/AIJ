package me.mitul.aij.helper

import android.content.Context
import com.readystatesoftware.sqliteasset.SQLiteAssetHelper
import me.mitul.aij.model.Closing
import me.mitul.aij.utils.Consts

class HelperAllClosing(context: Context?) : SQLiteAssetHelper(
    context, Consts.DB_NAME, Consts.DB_PATH, null, Consts.DB_VERSION
) {
    fun selectClosingAll(collegeId: Int): ArrayList<Closing> {
        val cursor = readableDatabase.rawQuery(
            "SELECT OPENClosing, SEBCClosing, SCClosing, STClosing, EBCClosing, TFWSClosing FROM INS_Cutoff WHERE CollegeId = $collegeId;",
            null
        )
        val branchName = readableDatabase.rawQuery(
            "SELECT BranchProperName FROM INS_Branch WHERE BranchID IN (SELECT BranchID FROM INS_Cutoff WHERE CollegeId = $collegeId);",
            null
        )

        val list = ArrayList<Closing>()
        if (cursor.moveToFirst() && branchName.moveToFirst()) do {
            list += Closing(
                branchName = branchName.getString(branchName.getColumnIndex("BranchProperName")),
                tFWSValue = cursor.getInt(cursor.getColumnIndex("TFWSClosing")),
                openValue = cursor.getInt(cursor.getColumnIndex("OPENClosing")),
                sEBCValue = cursor.getInt(cursor.getColumnIndex("SEBCClosing")),
                oBCValue = cursor.getInt(cursor.getColumnIndex("EBCClosing")),
                sCValue = cursor.getInt(cursor.getColumnIndex("SCClosing")),
                sTValue = cursor.getInt(cursor.getColumnIndex("STClosing")),
            )
        } while (cursor.moveToNext() && branchName.moveToNext())

        branchName.close()
        cursor.close()
        return list
    }
}
