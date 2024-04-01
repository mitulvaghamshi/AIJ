package me.mitul.aij.university

import android.content.Context
import androidx.core.database.getIntOrNull
import androidx.core.database.getStringOrNull
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

    fun getAll(): List<UniversityModel> {
        val cursor = db.readableDatabase.query(
            TBL_UNIVERSITY, arrayOf(COL_ID, COL_NAME),
            null, null, null, null, COL_NAME
        )
        val items = arrayListOf<UniversityModel>()
        if (cursor.moveToFirst()) do {
            items += UniversityModel(
                id = cursor.getIntOrNull(cursor.getColumnIndex(COL_ID)) ?: -1,
                name = cursor.getStringOrNull(cursor.getColumnIndex(COL_NAME)) ?: ""
            )
        } while (cursor.moveToNext())
        cursor.close()
        return items
    }

    fun getBy(id: String?): UniversityModel? {
        if (id.isNullOrBlank()) return null
        val cursor = db.readableDatabase.query(
            TBL_UNIVERSITY, arrayOf(
                COL_ID, COL_NAME, COL_ACRONYM, COL_TYPE, COL_PHONE,
                COL_ADDRESS, COL_WEBSITE, COL_EMAIL, COL_ESTABLISHED,
            ), "$COL_ID = ?", arrayOf(id), null, null, null
        )
        var university: UniversityModel? = null
        if (cursor.count > 0 && cursor.moveToFirst()) {
            university = UniversityModel(
                id = cursor.getIntOrNull(cursor.getColumnIndex(COL_ID)) ?: -1,
                name = cursor.getStringOrNull(cursor.getColumnIndex(COL_NAME)) ?: "",
                acronym = cursor.getStringOrNull(cursor.getColumnIndex(COL_ACRONYM)),
                type = cursor.getStringOrNull(cursor.getColumnIndex(COL_TYPE)),
                phone = cursor.getStringOrNull(cursor.getColumnIndex(COL_PHONE)),
                address = cursor.getStringOrNull(cursor.getColumnIndex(COL_ADDRESS)),
                website = cursor.getStringOrNull(cursor.getColumnIndex(COL_WEBSITE)),
                email = cursor.getStringOrNull(cursor.getColumnIndex(COL_EMAIL)),
                established = cursor.getStringOrNull(cursor.getColumnIndex(COL_ESTABLISHED)),
            )
        }
        cursor.close()
        return university
    }
}
