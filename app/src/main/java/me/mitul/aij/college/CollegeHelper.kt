package me.mitul.aij.college

import android.content.Context
import android.database.Cursor
import androidx.core.database.getIntOrNull
import androidx.core.database.getLongOrNull
import androidx.core.database.getStringOrNull
import me.mitul.aij.utils.Database

class CollegeHelper(context: Context, private val db: Database = Database(context)) {
    private companion object {
        const val TBL_BRANCH = "Branch"
        const val TBL_COLLEGE = "College"
        const val TBL_UNIVERSITY = "University"
        const val TBL_COLLEGE_TYPE = "CollegeType"
        const val TBL_CB_COLLEGE_BRANCH = "cb_college_branch"

        const val COL_ID = "id"
        const val COL_NAME = "name"
        const val COL_CODE = "code"
        const val COL_ACRONYM = "acronym"
        const val COL_PHONE = "phone"
        const val COL_EMAIL = "email"
        const val COL_WEBSITE = "website"
        const val COL_ADDRESS = "address"
        const val COL_FEES = "fees"
        const val COL_HOSTEL = "hostel"
        const val COL_ESTABLISHED = "established"
        const val COL_ADMISSION_CODE = "admission_code"

        const val COL_BRANCH_ID = "branch_id"
        const val COL_COLLEGE_ID = "college_id"
        const val COL_UNIVERSITY_ID = "university_id"
        const val COL_COLLEGE_TYPE_ID = "college_type_id"

        const val COL_COLLEGE_TYPE_TYPE = "type"

        const val SQL_COLLEGE =
            "SELECT $COL_ID, $COL_ACRONYM, $COL_FEES, $COL_HOSTEL FROM $TBL_COLLEGE"
        const val SQL_COLLEGE_BY_ID = ""
        const val SQL_BRANCH_FILTER =
            "SELECT $COL_COLLEGE_ID FROM $TBL_CB_COLLEGE_BRANCH WHERE $COL_BRANCH_ID = ?"
        const val SQL_COLLEGE_FILTER =
            "SELECT $COL_BRANCH_ID FROM $TBL_CB_COLLEGE_BRANCH WHERE $COL_COLLEGE_ID = ?"
        const val SQL_COLLEGE_BY_BRANCH = "$SQL_COLLEGE WHERE $COL_ID IN ($SQL_BRANCH_FILTER);"
        const val SQL_BRANCH_BY_COLLEGE =
            "SELECT $COL_ACRONYM FROM $TBL_BRANCH WHERE $COL_ID IN ($SQL_COLLEGE_FILTER);"
        const val SQL_COLLEGE_BY_UNIVERSITY = "$SQL_COLLEGE WHERE $COL_UNIVERSITY_ID = ?;"
    }

    private fun get(sql: String, selectionArgs: Array<String?>? = null): List<CollegeModel> {
        val items = arrayListOf<CollegeModel>()
        val cursor = db.readableDatabase.rawQuery(sql, selectionArgs)
        if (cursor.moveToFirst()) do {
            val college = CollegeModel(
                id = cursor.getLongOrNull(cursor.getColumnIndex(COL_ID)) ?: -1L,
                name = cursor.getStringOrNull(cursor.getColumnIndex(COL_ACRONYM)) ?: "",
                fees = cursor.getIntOrNull(cursor.getColumnIndex(COL_FEES)).toString() + "/-",
                hostel = cursor.getStringOrNull(cursor.getColumnIndex(COL_HOSTEL)),
            )
            val cursorBranches = db.readableDatabase
                .rawQuery(SQL_BRANCH_BY_COLLEGE, arrayOf(college.id.toString()))
            if (cursorBranches.moveToFirst()) college.branches = StringBuilder().apply {
                do append(
                    cursorBranches.getStringOrNull(cursorBranches.getColumnIndex(COL_ACRONYM)) + ","
                ) while (cursorBranches.moveToNext())
            }.toString().dropLast(1)
            cursorBranches.close()
            items.add(college)
        } while (cursor.moveToNext())
        cursor.close()
        return items
    }

    fun getAll() = get(SQL_COLLEGE)

    fun getByBranch(id: String?) = get(SQL_COLLEGE_BY_BRANCH, arrayOf(id))

    fun getByUniversity(id: String?) = get(SQL_COLLEGE_BY_UNIVERSITY, arrayOf(id))

    fun getCollegeBy(id: String?): CollegeModel {
        val college = CollegeModel()
        val database = db.readableDatabase
        val cursor = database.rawQuery("Select * from INS_College where CollegeID = $id", null)
        if (cursor.moveToFirst()) {
            college.code = cursor.getString(cursor.getColumnIndex("CollegeCode"))
            college.clgCollegeId = cursor.getInt(cursor.getColumnIndex("CollegeID"))
            college.initials = cursor.getString(cursor.getColumnIndex("CollegeShortName"))
            college.fullName = cursor.getString(cursor.getColumnIndex("CollegeName"))
            college.address = cursor.getString(cursor.getColumnIndex("Address"))
            college.phone = cursor.getString(cursor.getColumnIndex("Phone"))
            college.web = cursor.getString(cursor.getColumnIndex("Website"))
            college.email = cursor.getString(cursor.getColumnIndex("Email"))
            college.fees = cursor.getString(cursor.getColumnIndex("Fees"))
            college.type = cursor.getString(cursor.getColumnIndex("CollegeTypeID"))
            college.hostel = cursor.getString(cursor.getColumnIndex("Hostel"))
            college.university = cursor.getString(cursor.getColumnIndex("UniversityID"))
        }
        val cursor1 = database.rawQuery(
            "Select CollegeTypeName from MST_CollegeType where CollegeTypeID = " + college.type,
            null
        )
        if (cursor1.moveToFirst()) {
            college.type = cursor1.getString(cursor1.getColumnIndex("CollegeTypeName"))
        }
        val cursor2 = database.rawQuery(
            "Select UniversityShortName from MST_University where UniversityID = " + college.university,
            null
        )
        if (cursor2.moveToFirst()) {
            college.university = cursor2.getString(cursor2.getColumnIndex("UniversityShortName"))
        }
        cursor2.close()
        cursor1.close()
        cursor.close()
        database.close()
        return college
    }
}
