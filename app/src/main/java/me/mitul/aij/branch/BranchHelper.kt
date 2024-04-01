package me.mitul.aij.branch

import android.content.Context
import androidx.core.database.getLongOrNull
import androidx.core.database.getStringOrNull
import me.mitul.aij.utils.Database

class BranchHelper(context: Context, private val db: Database = Database(context)) {
    private companion object {
        const val TBL_BRANCH = "Branch"

        const val COL_ID = "id"
        const val COL_CODE = "code"
        const val COL_NAME = "name"
        const val COL_ACRONYM = "acronym"

        const val TBL_CB_COLLEGE_BRANCH = "CB_College_Branch"

        const val COL_BRANCH_ID = "branch_id"
    }

    fun getAll(): List<BranchModel> {
        val items = arrayListOf<BranchModel>()
        val cursor = db.readableDatabase.query(
            TBL_BRANCH, arrayOf(COL_ID, COL_CODE, COL_NAME, COL_ACRONYM),
            null, null, null, null, null
        )
        val sqlCollegeCount =
            "SELECT COUNT(*) FROM $TBL_CB_COLLEGE_BRANCH WHERE $COL_BRANCH_ID = ?;"
        val stmtCollegeCount = db.readableDatabase.compileStatement(sqlCollegeCount)
        if (cursor.moveToFirst()) do {
            val id = cursor.getLongOrNull(cursor.getColumnIndex(COL_ID)) ?: -1L
            val name = cursor.getStringOrNull(cursor.getColumnIndex(COL_NAME))
            val acronym = cursor.getStringOrNull(cursor.getColumnIndex(COL_ACRONYM))
            stmtCollegeCount.bindLong(1, id)
            val count = stmtCollegeCount.simpleQueryForLong()
            val format = "Available in %d college${if (count == 1L) "" else "s"}."
            items += BranchModel(
                id = id,
                count = String.format(format, count),
                name = String.format("%s (%s)", name, acronym)
            )
        } while (cursor.moveToNext())
        cursor.close()
        return items
    }
}
