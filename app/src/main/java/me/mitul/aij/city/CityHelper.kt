package me.mitul.aij.city

import android.content.Context
import me.mitul.aij.utils.Database
import me.mitul.aij.utils.Keys

class CityHelper(context: Context, private val db: Database = Database(context)) {
    private companion object {
        const val TBL_BANK_BRANCH = "BankBranch"
        const val TBL_HELP_CENTER = "HelpCenter"

        const val COL_ID = "id"
        const val COL_NAME = "name"
        const val COL_CITY = "city"
        const val COL_ADDRESS = "address"
    }

    private object Sql {
        const val BANK_BRANCH_CITIES = """
            SELECT   $COL_ID, $COL_CITY
            FROM     $TBL_BANK_BRANCH
            GROUP BY $COL_CITY
            ORDER BY $COL_CITY;
        """

        const val BANK_BRANCHES_BY_CITY = """
            SELECT   $COL_ID, $COL_NAME, $COL_CITY, $COL_ADDRESS
            FROM     $TBL_BANK_BRANCH
            WHERE    $COL_CITY = ?
            ORDER BY $COL_NAME;
        """

        const val HELP_CENTER_CITIES = """
            SELECT   $COL_ID, $COL_CITY
            FROM     $TBL_HELP_CENTER
            GROUP BY $COL_CITY
            ORDER BY $COL_CITY;
        """

        const val HELP_CENTERS_BY_CITY = """
            SELECT   $COL_ID, $COL_NAME, $COL_CITY, $COL_ADDRESS
            FROM     $TBL_HELP_CENTER
            WHERE    $COL_CITY = ?
            ORDER BY $COL_NAME;
        """
    }

    private fun getSql(filter: String, city: String? = null) =
        if (city.isNullOrBlank()) when (filter) {
            Keys.KEY_FILTER_BANK_BRANCH -> Sql.BANK_BRANCH_CITIES
            Keys.KEY_FILTER_HELP_CENTER -> Sql.HELP_CENTER_CITIES
            else -> null
        } else when (filter) {
            Keys.KEY_FILTER_BANK_BRANCH -> Sql.BANK_BRANCHES_BY_CITY
            Keys.KEY_FILTER_HELP_CENTER -> Sql.HELP_CENTERS_BY_CITY
            else -> null
        }

    fun getAll(filter: String) = arrayListOf<CityModel>().apply {
        val cursor = db.readableDatabase.rawQuery(getSql(filter), null)
        if (cursor.moveToFirst()) do add(
            CityModel(
                id = cursor.getInt(cursor.getColumnIndexOrThrow(COL_ID)),
                city = cursor.getString(cursor.getColumnIndexOrThrow(COL_CITY)),
            )
        ) while (cursor.moveToNext())
        cursor.close()
    }

    fun getByCity(filter: String, city: String) = arrayListOf<CityModel>().apply {
        val cursor = db.readableDatabase.rawQuery(getSql(filter, city), arrayOf(city))
        if (cursor.moveToFirst()) do add(
            CityModel(
                id = cursor.getInt(cursor.getColumnIndexOrThrow(COL_ID)),
                city = cursor.getString(cursor.getColumnIndexOrThrow(COL_CITY)),
                name = cursor.getString(cursor.getColumnIndexOrThrow(COL_NAME)),
                address = cursor.getString(cursor.getColumnIndexOrThrow(COL_ADDRESS)),
            )
        ) while (cursor.moveToNext())
        cursor.close()
    }
}
