package me.mitul.aij.helpers

import android.content.Context
import androidx.core.database.getLongOrNull
import androidx.core.database.getStringOrNull
import me.mitul.aij.models.Branch
import me.mitul.aij.utils.Database

class BranchHelper(context: Context) : Database(context) {
    private companion object {
        const val TBL_BRANCH = "Branch"

        const val COL_ID = "id"
        const val COL_CODE = "code"
        const val COL_NAME = "name"
        const val COL_ACRONYM = "acronym"

        const val TBL_CB_COLLEGE_BRANCH = "CB_College_Branch"

        const val COL_BRANCH_ID = "branch_id"
    }

    private object Sql {
        const val BRANCHES = """
            SELECT $COL_ID, $COL_CODE, $COL_NAME, $COL_ACRONYM
            FROM   $TBL_BRANCH;
        """

        const val COLLEGE_COUNT = """
            SELECT COUNT(*) 
            FROM   $TBL_CB_COLLEGE_BRANCH 
            WHERE  $COL_BRANCH_ID = ?;
        """
    }

    fun getAll() = arrayListOf<Branch>().apply {
        val cursor = readableDatabase.rawQuery(Sql.BRANCHES, null)
        val stmt = readableDatabase.compileStatement(Sql.COLLEGE_COUNT)
        if (cursor.moveToFirst()) do {
            val id = cursor.getLongOrNull(cursor.getColumnIndex(COL_ID)) ?: -1L
            val name = cursor.getStringOrNull(cursor.getColumnIndex(COL_NAME))
            val acronym = cursor.getStringOrNull(cursor.getColumnIndex(COL_ACRONYM))
            stmt.bindLong(1, id)
            val count = stmt.simpleQueryForLong()
            val format = "Available in %d college${if (count == 1L) "" else "s"}."
            add(
                Branch(
                    id = id,
                    count = String.format(format, count),
                    name = String.format("%s (%s)", name, acronym)
                )
            )
        } while (cursor.moveToNext())
        cursor.close()
    }
}
