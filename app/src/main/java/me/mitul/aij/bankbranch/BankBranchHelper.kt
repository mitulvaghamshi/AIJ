package me.mitul.aij.bankbranch

import android.content.Context
import me.mitul.aij.city.CityModel
import me.mitul.aij.utils.Database

class BankBranchHelper(context: Context, private val db: Database = Database(context)) {
    private companion object {
        const val TBL_BANK_BRANCH = "BankBranch"

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
    }

    fun getAll() = arrayListOf<CityModel>().apply {
        val cursor = db.readableDatabase.rawQuery(Sql.BANK_BRANCH_CITIES, null)
        if (cursor.moveToFirst()) do add(
            CityModel(
                id = cursor.getInt(cursor.getColumnIndexOrThrow(COL_ID)),
                city = cursor.getString(cursor.getColumnIndexOrThrow(COL_CITY)),
            )
        ) while (cursor.moveToNext())
        cursor.close()
    }

    fun getByCity(city: String) = arrayListOf<CityModel>().apply {
        val cursor = db.readableDatabase.rawQuery(Sql.BANK_BRANCHES_BY_CITY, arrayOf(city))
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
