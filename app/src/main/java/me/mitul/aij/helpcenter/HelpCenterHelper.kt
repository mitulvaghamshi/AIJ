package me.mitul.aij.helpcenter

import android.content.Context
import me.mitul.aij.city.CityModel
import me.mitul.aij.utils.Database

class HelpCenterHelper(context: Context, private val db: Database = Database(context)) {
    private companion object {
        const val TBL_HELP_CENTER = "HelpCenter"

        const val COL_ID = "id"
        const val COL_NAME = "name"
        const val COL_CITY = "city"
        const val COL_ADDRESS = "address"
    }

    private object Sql {
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

    fun getAll() = arrayListOf<CityModel>().apply {
        val cursor = db.readableDatabase.rawQuery(Sql.HELP_CENTER_CITIES, null)
        if (cursor.moveToFirst()) do add(
            CityModel(
                id = cursor.getInt(cursor.getColumnIndexOrThrow(COL_ID)),
                city = cursor.getString(cursor.getColumnIndexOrThrow(COL_CITY)),
            )
        ) while (cursor.moveToNext())
        cursor.close()
    }

    fun getByCity(city: String) = arrayListOf<CityModel>().apply {
        val cursor = db.readableDatabase.rawQuery(Sql.HELP_CENTERS_BY_CITY, arrayOf(city))
        if (cursor.moveToFirst()) do add(
            CityModel(
                id = cursor.getInt(cursor.getColumnIndexOrThrow(COL_ID)),
                city = cursor.getString(cursor.getColumnIndexOrThrow(COL_NAME)),
                name = cursor.getString(cursor.getColumnIndexOrThrow(COL_CITY)),
                address = cursor.getString(cursor.getColumnIndexOrThrow(COL_ADDRESS)),
            )
        ) while (cursor.moveToNext())
        cursor.close()
    }
}
