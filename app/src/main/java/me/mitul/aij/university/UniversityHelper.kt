package me.mitul.aij.university

import android.content.Context
import me.mitul.aij.utils.Database

class UniversityHelper(context: Context, private val db: Database = Database(context)) {
    private companion object {
        const val TBL_UNIVERSITY = "University"

        const val COL_ID = "id"
        const val COL_NAME = "name"
        const val COL_ACRONYM = "acronym"
        const val COL_TYPE = "type"
        const val COL_PHONE = "phone"
        const val COL_ADDRESS = "address"
        const val COL_WEBSITE = "website"
        const val COL_EMAIL = "email"
        const val COL_ESTABLISHED = "established"
    }

    private object Sql {
        const val UNIVERSITIES = """
            SELECT   $COL_ID, $COL_NAME 
            FROM     $TBL_UNIVERSITY
            ORDER BY $COL_NAME;
        """

        const val UNIVERSITY_BY_ID = """
            SELECT $COL_ID, $COL_NAME, $COL_ACRONYM, $COL_TYPE, $COL_PHONE,
                   $COL_ADDRESS, $COL_WEBSITE, $COL_EMAIL, $COL_ESTABLISHED
            FROM   $TBL_UNIVERSITY
            WHERE  $COL_ID = ?;
        """
    }

    fun getAll() = arrayListOf<UniversityModel>().apply {
        val cursor = db.readableDatabase.rawQuery(Sql.UNIVERSITIES, null)
        if (cursor.moveToFirst()) do add(
            UniversityModel(
                id = cursor.getInt(cursor.getColumnIndexOrThrow(COL_ID)),
                name = cursor.getString(cursor.getColumnIndexOrThrow(COL_NAME)),
            )
        ) while (cursor.moveToNext())
        cursor.close()
    }

    fun getBy(id: String?): UniversityModel? {
        val cursor = db.readableDatabase.rawQuery(Sql.UNIVERSITY_BY_ID, arrayOf(id))
        val university = if (cursor.moveToFirst()) UniversityModel(
            id = cursor.getInt(cursor.getColumnIndexOrThrow(COL_ID)),
            name = cursor.getString(cursor.getColumnIndexOrThrow(COL_NAME)),
            acronym = cursor.getString(cursor.getColumnIndexOrThrow(COL_ACRONYM)),
            type = cursor.getString(cursor.getColumnIndexOrThrow(COL_TYPE)),
            phone = cursor.getString(cursor.getColumnIndexOrThrow(COL_PHONE)),
            address = cursor.getString(cursor.getColumnIndexOrThrow(COL_ADDRESS)),
            website = cursor.getString(cursor.getColumnIndexOrThrow(COL_WEBSITE)),
            email = cursor.getString(cursor.getColumnIndexOrThrow(COL_EMAIL)),
            established = cursor.getString(cursor.getColumnIndexOrThrow(COL_ESTABLISHED)),
        ) else null
        cursor.close()
        return university
    }
}
