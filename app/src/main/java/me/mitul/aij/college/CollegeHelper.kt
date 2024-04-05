package me.mitul.aij.college

import android.content.Context
import me.mitul.aij.utils.Database
import me.mitul.aij.utils.Keys

class CollegeHelper(context: Context, private val db: Database = Database(context)) {
    private companion object {
        const val TBL_BRANCH = "Branch"
        const val TBL_COLLEGE = "College"
        const val TBL_UNIVERSITY = "University"
        const val TBL_COLLEGE_TYPE = "CollegeType"
        const val TBL_CB_COLLEGE_BRANCH = "CB_College_Branch"

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
        const val COL_UNIVERSITY_ID = "university_id"
        const val COL_COLLEGE_TYPE_ID = "college_type_id"

        const val COL_CT_TYPE = "type"
        const val COL_CB_BRANCH_ID = "branch_id"
        const val COL_CB_COLLEGE_ID = "college_id"
    }

    private object Sql {
        const val COLLEGE_LIST = """
            SELECT $COL_ID, $COL_NAME, $COL_FEES, $COL_HOSTEL 
            FROM   $TBL_COLLEGE
        """

        const val COLLEGE_DETAIL = """
            SELECT $COL_ID, $COL_NAME, $COL_CODE, $COL_ACRONYM, $COL_PHONE,
                   $COL_EMAIL, $COL_WEBSITE, $COL_ADDRESS, $COL_FEES, 
                   $COL_HOSTEL, $COL_ESTABLISHED, $COL_ADMISSION_CODE,
                   $COL_COLLEGE_TYPE_ID, $COL_UNIVERSITY_ID
            FROM   $TBL_COLLEGE 
            WHERE  $COL_ID = ?;
        """

        private const val COLLEGE_FILTER = """
            SELECT $COL_CB_BRANCH_ID 
            FROM   $TBL_CB_COLLEGE_BRANCH 
            WHERE  $COL_CB_COLLEGE_ID = ?
        """

        private const val BRANCH_FILTER = """
            SELECT $COL_CB_COLLEGE_ID 
            FROM   $TBL_CB_COLLEGE_BRANCH 
            WHERE  $COL_CB_BRANCH_ID = ?
        """

        const val BRANCHES_BY_COLLEGE = """
            SELECT $COL_ACRONYM 
            FROM   $TBL_BRANCH 
            WHERE  $COL_ID IN ($COLLEGE_FILTER);
        """

        const val COLLEGE_BY_BRANCH = """
            $COLLEGE_LIST 
            WHERE $COL_ID IN ($BRANCH_FILTER);
        """

        const val COLLEGE_BY_UNIVERSITY = """
            $COLLEGE_LIST 
            WHERE $COL_UNIVERSITY_ID = ?;
        """
    }

    fun getColleges(filter: String?, id: String?) = when (filter) {
        Keys.KEY_FILTER_BRANCH -> getByBranch(id)
        Keys.KEY_FILTER_UNIVERSITY -> getByUniversity(id)
        else -> get(Sql.COLLEGE_LIST)
    }

    fun getCollegeBy(id: String?, includeAllData: Boolean = true) =
        get(Sql.COLLEGE_DETAIL, arrayOf(id), includeAllData).first()

    private fun getByBranch(id: String?) = get(Sql.COLLEGE_BY_BRANCH, arrayOf(id))

    private fun getByUniversity(id: String?) = get(Sql.COLLEGE_BY_UNIVERSITY, arrayOf(id))

    private fun getCollegeTypeBy(id: String?) = getValueBy(TBL_COLLEGE_TYPE, COL_CT_TYPE, id)

    private fun getUniversityNameBy(id: String?) = getValueBy(TBL_UNIVERSITY, COL_ACRONYM, id)

    private fun getBranchesBy(id: String?): String? {
        val cursor = db.readableDatabase.rawQuery(Sql.BRANCHES_BY_COLLEGE, arrayOf(id))
        val branchString = if (cursor.moveToFirst()) StringBuilder().apply {
            do append(cursor.getString(cursor.getColumnIndexOrThrow(COL_ACRONYM)) + ", ")
            while (cursor.moveToNext())
        }.toString().dropLast(n = 2) else null
        cursor.close()
        return branchString
    }

    private fun getValueBy(tbl: String, col: String, arg: String?): String? {
        val cursor = db.readableDatabase
            .rawQuery("SELECT $col FROM $tbl WHERE $COL_ID = ?", arrayOf(arg))
        val value = if (cursor.moveToFirst()) cursor
            .getString(cursor.getColumnIndexOrThrow(col)) else null
        cursor.close()
        return value
    }

    private fun get(
        sql: String,
        selectionArgs: Array<String?>? = null,
        includeAllData: Boolean = false,
    ): List<CollegeModel> {
        val items = arrayListOf<CollegeModel>()
        val cursor = db.readableDatabase.rawQuery(sql, selectionArgs)
        if (cursor.moveToFirst()) do {
            val collegeId = cursor.getLong(cursor.getColumnIndexOrThrow(COL_ID))
            val college = CollegeModel(
                id = collegeId,
                name = cursor.getString(cursor.getColumnIndexOrThrow(COL_NAME)),
                fees = cursor.getInt(cursor.getColumnIndexOrThrow(COL_FEES)),
                hostel = cursor.getString(cursor.getColumnIndexOrThrow(COL_HOSTEL)),
                branches = getBranchesBy(collegeId.toString()),
            )
            if (includeAllData) {
                val typeId = cursor.getInt(cursor.getColumnIndexOrThrow(COL_COLLEGE_TYPE_ID))
                val universityId = cursor.getInt(cursor.getColumnIndexOrThrow(COL_UNIVERSITY_ID))
                college.apply {
                    code = cursor.getString(cursor.getColumnIndexOrThrow(COL_CODE))
                    acronym = cursor.getString(cursor.getColumnIndexOrThrow(COL_ACRONYM))
                    phone = cursor.getString(cursor.getColumnIndexOrThrow(COL_PHONE))
                    email = cursor.getString(cursor.getColumnIndexOrThrow(COL_EMAIL))
                    website = cursor.getString(cursor.getColumnIndexOrThrow(COL_WEBSITE))
                    address = cursor.getString(cursor.getColumnIndexOrThrow(COL_ADDRESS))
                    established = cursor.getString(cursor.getColumnIndexOrThrow(COL_ESTABLISHED))
                    admissionCode =
                        cursor.getString(cursor.getColumnIndexOrThrow(COL_ADMISSION_CODE))
                    type = getCollegeTypeBy(typeId.toString())
                    university = getUniversityNameBy(universityId.toString())
                }
            }
            items.add(college)
        } while (cursor.moveToNext())
        cursor.close()
        return items
    }
}
