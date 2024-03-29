package me.mitul.aij.helper

import android.content.Context
import com.readystatesoftware.sqliteasset.SQLiteAssetHelper
import me.mitul.aij.model.AllClosingList
import me.mitul.aij.utils.Constants

class HelperAllClosing(context: Context?) : SQLiteAssetHelper(
    context, Constants.DB_NAME, Constants.DB_PATH, null, Constants.DB_VERSION
) {
    fun selectClosingAll(collegeId: Int): ArrayList<AllClosingList> {
        val list = ArrayList<AllClosingList>()
        val database = getReadableDatabase()
        val cursor = database.rawQuery(
            "SELECT OPENClosing, SEBCClosing, SCClosing, STClosing, EBCClosing, TFWSClosing FROM INS_Cutoff WHERE CollegeId = $collegeId;",
            null
        )
        val branchName = database.rawQuery(
            "SELECT BranchProperName FROM INS_Branch WHERE BranchID IN (SELECT BranchID FROM INS_Cutoff WHERE CollegeId = $collegeId);",
            null
        )
        if (cursor.moveToFirst() && branchName.moveToFirst()) do {
            val closingList = AllClosingList()
            closingList.branchName =
                branchName.getString(branchName.getColumnIndex("BranchProperName"))
            closingList.tfwsValue = cursor.getInt(cursor.getColumnIndex("TFWSClosing"))
            closingList.openValue = cursor.getInt(cursor.getColumnIndex("OPENClosing"))
            closingList.sebcValue = cursor.getInt(cursor.getColumnIndex("SEBCClosing"))
            closingList.ebcValue = cursor.getInt(cursor.getColumnIndex("EBCClosing"))
            closingList.scValue = cursor.getInt(cursor.getColumnIndex("SCClosing"))
            closingList.stValue = cursor.getInt(cursor.getColumnIndex("STClosing"))
            list.add(closingList)
        } while (cursor.moveToNext() && branchName.moveToNext())
        branchName.close()
        cursor.close()
        database.close()
        return list
    }
}
