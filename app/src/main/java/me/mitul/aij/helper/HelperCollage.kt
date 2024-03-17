package me.mitul.aij.helper

import android.annotation.SuppressLint
import android.content.Context
import android.database.Cursor
import com.readystatesoftware.sqliteasset.SQLiteAssetHelper
import me.mitul.aij.model.Collage
import me.mitul.aij.utils.Constants

class HelperCollage(context: Context?) : SQLiteAssetHelper(
    context, Constants.DB_NAME, Constants.DB_PATH, null, Constants.DB_VERSION
) {
    @SuppressLint("NewApi", "DefaultLocale")
    fun selectAllCollage(): ArrayList<Collage> {
        val list = ArrayList<Collage>()
        val database = getReadableDatabase()
        val cursor = database.rawQuery(
            "Select CollegeID,CollegeShortName,Fees,Hostel from INS_College",
            null
        )
        if (cursor.moveToFirst()) do {
            val collage = Collage()
            collage.collageId = cursor.getInt(cursor.getColumnIndex("CollegeID"))
            collage.collageName = cursor.getString(cursor.getColumnIndex("CollegeShortName"))
            collage.fees = cursor.getInt(cursor.getColumnIndex("Fees")).toString() + "/-"
            collage.hostel = cursor.getString(cursor.getColumnIndex("Hostel"))
            val cursor1 = database.rawQuery(
                "SELECT BranchShortName FROM INS_Branch WHERE BranchID IN (SELECT BranchID FROM INS_Intake WHERE CollegeID = " + collage.collageId + ");",
                null
            )
            if (cursor1.moveToFirst()) {
                var branches = ""
                do {
                    branches += cursor1
                        .getString(cursor1.getColumnIndex("BranchShortName")) + ","
                } while (cursor1.moveToNext())
                collage.branches = branches.substring(0, branches.length - 1)
            }
            cursor1.close()
            list.add(collage)
        } while (cursor.moveToNext())
        cursor.close()
        database.close()
        return list
    }

    @SuppressLint("NewApi", "DefaultLocale")
    fun selectBranchWiseCollage(id: String?): ArrayList<Collage> {
        val list = ArrayList<Collage>()
        val database = getReadableDatabase()
        val cursor = database.rawQuery(
            "SELECT CollegeID,CollegeShortName,Fees,Hostel FROM INS_College WHERE CollegeID IN (SELECT CollegeID FROM INS_Cutoff WHERE BranchID = $id);",
            null
        )
        if (cursor.moveToFirst()) do {
            val collage = Collage()
            collage.collageId = cursor.getInt(cursor.getColumnIndex("CollegeID"))
            collage.collageName = cursor.getString(cursor.getColumnIndex("CollegeShortName"))
            collage.fees = cursor.getInt(cursor.getColumnIndex("Fees")).toString() + "/-"
            collage.hostel = cursor.getString(cursor.getColumnIndex("Hostel"))
            val cursor1 = database.rawQuery(
                "SELECT BranchShortName FROM INS_Branch WHERE BranchID IN (SELECT BranchID FROM INS_Intake WHERE CollegeID = " + collage.collageId + ");",
                null
            )
            if (cursor1.moveToFirst()) {
                var branches = ""
                do {
                    branches += cursor1
                        .getString(cursor1.getColumnIndex("BranchShortName")) + ","
                } while (cursor1.moveToNext())
                collage.branches = branches.substring(0, branches.length - 1)
            }
            cursor1.close()
            list.add(collage)
        } while (cursor.moveToNext())
        cursor.close()
        database.close()
        return list
    }

    @SuppressLint("NewApi", "DefaultLocale")
    fun selectUniversityWiseCollage(id: String?): ArrayList<Collage> {
        val list = ArrayList<Collage>()
        val database = getReadableDatabase()
        val cursor = database.rawQuery(
            "SELECT CollegeID,CollegeShortName,Fees,Hostel FROM INS_College WHERE CollegeID IN (SELECT CollegeID FROM INS_College WHERE UniversityID = $id);",
            null
        )
        if (cursor.moveToFirst()) do {
            val collage = Collage()
            collage.collageId = cursor.getInt(cursor.getColumnIndex("CollegeID"))
            collage.collageName = cursor.getString(cursor.getColumnIndex("CollegeShortName"))
            collage.fees = cursor.getInt(cursor.getColumnIndex("Fees")).toString() + "/-"
            collage.hostel = cursor.getString(cursor.getColumnIndex("Hostel"))
            val cursor1 = database.rawQuery(
                "SELECT BranchShortName FROM INS_Branch WHERE BranchID IN (SELECT BranchID FROM INS_Intake WHERE CollegeID = " + collage.collageId + ");",
                null
            )
            if (cursor1.moveToFirst()) {
                var branches = ""
                do {
                    branches += cursor1
                        .getString(cursor1.getColumnIndex("BranchShortName")) + ","
                } while (cursor1.moveToNext())
                collage.branches = branches.substring(0, branches.length - 1)
            }
            cursor1.close()
            list.add(collage)
        } while (cursor.moveToNext())
        cursor.close()
        database.close()
        return list
    }

    @SuppressLint("NewApi", "DefaultLocale")
    fun selectIntakeById(collegeId: Int): ArrayList<Collage> {
        val list = ArrayList<Collage>()
        val database = getReadableDatabase()
        var cursorSeat: Cursor
        var cursorVecent: Cursor
        val cursorIntake = database.rawQuery(
            "select INS_Intake.CollegeID,INS_Intake.Shift,INS_Branch.BranchProperName,INS_Intake.Intake,CASE WHEN INS_Intake.Vacant is null then ' ' ELSE INS_Intake.Vacant END as Vacant from INS_Intake Inner join INS_Branch on INS_Intake.BranchID=INS_Branch.BranchID Where INS_Intake.CollegeID = $collegeId AND INS_Intake.Shift = 1 order by INS_Branch.BranchProperName",
            null
        )
        if (cursorIntake.moveToFirst()) {
            do {
                val collage = Collage()
                collage.lvBranch =
                    cursorIntake.getString(cursorIntake.getColumnIndex("BranchProperName"))
                collage.lvSeat = cursorIntake.getInt(cursorIntake.getColumnIndex("Intake"))
                collage.lvVacant = cursorIntake.getInt(cursorIntake.getColumnIndex("Vacant"))
                cursorSeat = database.rawQuery(
                    "SELECT Intake,Vacant from INS_Intake WHERE BranchID = (SELECT BranchID FROM INS_Intake WHERE CollegeID = $collegeId);",
                    null
                )
                cursorVecent = database.rawQuery(
                    "SELECT Vacant from INS_Intake WHERE BranchID = (SELECT BranchID FROM INS_Intake WHERE CollegeID = $collegeId);",
                    null
                )
                if (cursorSeat.moveToFirst()) {
                    collage.lvSeat = cursorSeat.getInt(cursorSeat.getColumnIndex("Intake"))
                }
                collage.lvVacant = cursorSeat.getInt(cursorSeat.getColumnIndex("Vacant"))
                if (cursorVecent.moveToFirst()) {
                    collage.lvVacant = cursorVecent.getInt(cursorVecent.getColumnIndex("Vacant"))
                }
                list.add(collage)
                cursorSeat.moveToNext()
                cursorVecent.moveToNext()
            } while (cursorIntake.moveToNext())
            cursorSeat.close()
            cursorVecent.close()
        }
        cursorIntake.close()
        database.close()
        return list
    }

    @SuppressLint("NewApi", "DefaultLocale")
    fun selectCollageByID(id: String?): Collage {
        val collage = Collage()
        val database = getReadableDatabase()
        val cursor = database.rawQuery("Select * from INS_College where CollegeID = $id", null)
        if (cursor.moveToFirst()) {
            collage.label = cursor.getString(cursor.getColumnIndex("CollegeCode"))
            collage.clgCollageID = cursor.getInt(cursor.getColumnIndex("CollegeID"))
            collage.clgShortName = cursor.getString(cursor.getColumnIndex("CollegeShortName"))
            collage.clgFullName = cursor.getString(cursor.getColumnIndex("CollegeName"))
            collage.clgAddress = cursor.getString(cursor.getColumnIndex("Address"))
            collage.phone = cursor.getString(cursor.getColumnIndex("Phone"))
            collage.web = cursor.getString(cursor.getColumnIndex("Website"))
            collage.email = cursor.getString(cursor.getColumnIndex("Email"))
            collage.fees = cursor.getString(cursor.getColumnIndex("Fees"))
            collage.type = cursor.getString(cursor.getColumnIndex("CollegeTypeID"))
            collage.hostel = cursor.getString(cursor.getColumnIndex("Hostel"))
            collage.university = cursor.getString(cursor.getColumnIndex("UniversityID"))
        }
        val cursor1 = database.rawQuery(
            "Select CollegeTypeName from MST_CollegeType where CollegeTypeID = " + collage.type,
            null
        )
        if (cursor1.moveToFirst()) {
            collage.type = cursor1.getString(cursor1.getColumnIndex("CollegeTypeName"))
        }
        val cursor2 = database.rawQuery(
            "Select UniversityShortName from MST_University where UniversityID = " + collage.university,
            null
        )
        if (cursor2.moveToFirst()) {
            collage.university = cursor2.getString(cursor2.getColumnIndex("UniversityShortName"))
        }
        cursor2.close()
        cursor1.close()
        cursor.close()
        database.close()
        return collage
    }
}
