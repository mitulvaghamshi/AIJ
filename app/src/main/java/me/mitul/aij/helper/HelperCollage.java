package me.mitul.aij.helper;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import java.util.ArrayList;

import me.mitul.aij.model.Collage;

public class HelperCollage extends SQLiteAssetHelper {
    @SuppressLint("SdCardPath")
    public HelperCollage(Context context) {
        super(context, "AIJ_DB.s3db", "/data/data/me.mitul.aij/databases", null, 1);
    }

    @SuppressLint({"NewApi", "DefaultLocale"})
    public ArrayList<Collage> selectAllCollage() {
        ArrayList<Collage> list = new ArrayList<>();
        SQLiteDatabase database = getReadableDatabase();
        Cursor cursor = database.rawQuery("Select CollegeID,CollegeShortName,Fees,Hostel from INS_College", null);
        if (cursor.moveToFirst()) do {
            Collage collage = new Collage();
            collage.setCollageId(cursor.getInt(cursor.getColumnIndex("CollegeID")));
            collage.setCollageName(cursor.getString(cursor.getColumnIndex("CollegeShortName")));
            collage.setFees(cursor.getInt(cursor.getColumnIndex("Fees")) + "/-");
            collage.setHostel(cursor.getString(cursor.getColumnIndex("Hostel")));
            Cursor cursor1 = database.rawQuery("SELECT BranchShortName FROM INS_Branch WHERE BranchID IN (SELECT BranchID FROM INS_Intake WHERE CollegeID = " + collage.getCollageId() + ");", null);
            if (cursor1.moveToFirst()) {
                String branches = "";
                do {
                    branches = branches.concat((cursor1.getString(cursor1.getColumnIndex("BranchShortName"))) + ",");
                } while (cursor1.moveToNext());
                collage.setBranches(branches.substring(0, branches.length() - 1));
            }
            cursor1.close();
            list.add(collage);
        } while (cursor.moveToNext());
        cursor.close();
        database.close();
        return list;
    }

    @SuppressLint({"NewApi", "DefaultLocale"})
    public ArrayList<Collage> selectBranchWiseCollage(int id) {
        ArrayList<Collage> list = new ArrayList<>();
        SQLiteDatabase database = getReadableDatabase();
        Cursor cursor = database.rawQuery("SELECT CollegeID,CollegeShortName,Fees,Hostel FROM INS_College WHERE CollegeID IN (SELECT CollegeID FROM INS_Cutoff WHERE BranchID = " + id + ");", null);
        if (cursor.moveToFirst()) do {
            Collage collage = new Collage();
            collage.setCollageId(cursor.getInt(cursor.getColumnIndex("CollegeID")));
            collage.setCollageName(cursor.getString(cursor.getColumnIndex("CollegeShortName")));
            collage.setFees(cursor.getInt(cursor.getColumnIndex("Fees")) + "/-");
            collage.setHostel(cursor.getString(cursor.getColumnIndex("Hostel")));
            Cursor cursor1 = database.rawQuery("SELECT BranchShortName FROM INS_Branch WHERE BranchID IN (SELECT BranchID FROM INS_Intake WHERE CollegeID = " + collage.getCollageId() + ");", null);
            if (cursor1.moveToFirst()) {
                String branches = "";
                do {
                    branches = branches.concat((cursor1.getString(cursor1.getColumnIndex("BranchShortName"))) + ",");
                } while (cursor1.moveToNext());
                collage.setBranches(branches.substring(0, branches.length() - 1));
            }
            cursor1.close();
            list.add(collage);
        } while (cursor.moveToNext());
        cursor.close();
        database.close();
        return list;
    }

    @SuppressLint({"NewApi", "DefaultLocale"})
    public ArrayList<Collage> selectUniversityWiseCollage(int id) {
        ArrayList<Collage> list = new ArrayList<>();
        SQLiteDatabase database = getReadableDatabase();
        Cursor cursor = database.rawQuery("SELECT CollegeID,CollegeShortName,Fees,Hostel FROM INS_College WHERE CollegeID IN (SELECT CollegeID FROM INS_College WHERE UniversityID = " + id + ");", null);

        if (cursor.moveToFirst()) do {
            Collage collage = new Collage();
            collage.setCollageId(cursor.getInt(cursor.getColumnIndex("CollegeID")));
            collage.setCollageName(cursor.getString(cursor.getColumnIndex("CollegeShortName")));
            collage.setFees(cursor.getInt(cursor.getColumnIndex("Fees")) + "/-");
            collage.setHostel(cursor.getString(cursor.getColumnIndex("Hostel")));
            Cursor cursor1 = database.rawQuery("SELECT BranchShortName FROM INS_Branch WHERE BranchID IN (SELECT BranchID FROM INS_Intake WHERE CollegeID = " + collage.getCollageId() + ");", null);
            if (cursor1.moveToFirst()) {
                String branches = "";
                do {
                    branches = branches.concat((cursor1.getString(cursor1.getColumnIndex("BranchShortName"))) + ",");
                } while (cursor1.moveToNext());
                collage.setBranches(branches.substring(0, branches.length() - 1));
            }
            cursor1.close();
            list.add(collage);
        } while (cursor.moveToNext());
        cursor.close();
        database.close();
        return list;
    }

    @SuppressLint({"NewApi", "DefaultLocale"})
    public ArrayList<Collage> selectIntakeById(int collegeId) {
        ArrayList<Collage> list = new ArrayList<>();
        SQLiteDatabase database = getReadableDatabase();
        Cursor cursorSeat;
        Cursor cursorVecent;
        Cursor cursorIntake = database.rawQuery("select INS_Intake.CollegeID,INS_Intake.Shift,INS_Branch.BranchProperName,INS_Intake.Intake,CASE WHEN INS_Intake.Vacant is null then ' ' ELSE INS_Intake.Vacant END as Vacant from INS_Intake Inner join INS_Branch on INS_Intake.BranchID=INS_Branch.BranchID Where INS_Intake.CollegeID = " + collegeId + " AND INS_Intake.Shift = 1 order by INS_Branch.BranchProperName", null);
        if (cursorIntake.moveToFirst()) {
            do {
                Collage collage = new Collage();
                collage.setLvBranch(cursorIntake.getString(cursorIntake.getColumnIndex("BranchProperName")));
                collage.setLvSeat(cursorIntake.getInt(cursorIntake.getColumnIndex("Intake")));
                collage.setLvVacant(cursorIntake.getInt(cursorIntake.getColumnIndex("Vacant")));
                cursorSeat = database.rawQuery("SELECT Intake,Vacant from INS_Intake WHERE BranchID = (SELECT BranchID FROM INS_Intake WHERE CollegeID = " + collegeId + ");", null);
                cursorVecent = database.rawQuery("SELECT Vacant from INS_Intake WHERE BranchID = (SELECT BranchID FROM INS_Intake WHERE CollegeID = " + collegeId + ");", null);
                if (cursorSeat.moveToFirst()) {
                    collage.setLvSeat(cursorSeat.getInt(cursorSeat.getColumnIndex("Intake")));
                }
                collage.setLvVacant(cursorSeat.getInt(cursorSeat.getColumnIndex("Vacant")));
                if (cursorVecent.moveToFirst()) {
                    collage.setLvVacant(cursorVecent.getInt(cursorVecent.getColumnIndex("Vacant")));
                }
                list.add(collage);
                cursorSeat.moveToNext();
                cursorVecent.moveToNext();
            } while (cursorIntake.moveToNext());
            cursorSeat.close();
            cursorVecent.close();
        }
        cursorIntake.close();
        database.close();
        return list;
    }

    @SuppressLint({"NewApi", "DefaultLocale"})
    public Collage selectCollageByID(int id) {
        Collage collage = new Collage();
        SQLiteDatabase database = getReadableDatabase();
        Cursor cursor = database.rawQuery("Select * from INS_College where CollegeID = " + id, null);
        if (cursor.moveToFirst()) {
            collage.setLabel(cursor.getString(cursor.getColumnIndex("CollegeCode")));
            collage.setClgCollageID(cursor.getInt(cursor.getColumnIndex("CollegeID")));
            collage.setClgShortName(cursor.getString(cursor.getColumnIndex("CollegeShortName")));
            collage.setClgFullName(cursor.getString(cursor.getColumnIndex("CollegeName")));
            collage.setClgAddress(cursor.getString(cursor.getColumnIndex("Address")));
            collage.setPhone(cursor.getString(cursor.getColumnIndex("Phone")));
            collage.setWeb(cursor.getString(cursor.getColumnIndex("Website")));
            collage.setEmail(cursor.getString(cursor.getColumnIndex("Email")));
            collage.setFees(cursor.getString(cursor.getColumnIndex("Fees")));
            collage.setType(cursor.getString(cursor.getColumnIndex("CollegeTypeID")));
            collage.setHostel(cursor.getString(cursor.getColumnIndex("Hostel")));
            collage.setUniversity(cursor.getString(cursor.getColumnIndex("UniversityID")));
        }
        Cursor cursor1 = database.rawQuery("Select CollegeTypeName from MST_CollegeType where CollegeTypeID = " + collage.getType(), null);
        if (cursor1.moveToFirst()) {
            collage.setType(cursor1.getString(cursor1.getColumnIndex("CollegeTypeName")));
        }
        Cursor cursor2 = database.rawQuery("Select UniversityShortName from MST_University where UniversityID = " + collage.getUniversity(), null);
        if (cursor2.moveToFirst()) {
            collage.setUniversity(cursor2.getString(cursor2.getColumnIndex("UniversityShortName")));
        }
        cursor2.close();
        cursor1.close();
        cursor.close();
        database.close();
        return collage;
    }
}
