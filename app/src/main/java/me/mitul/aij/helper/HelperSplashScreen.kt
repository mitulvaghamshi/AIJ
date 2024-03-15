package me.mitul.aij.helper

import android.content.Context
import com.readystatesoftware.sqliteasset.SQLiteAssetHelper
import me.mitul.aij.model.SplashScreen

class HelperSplashScreen(context: Context?) :
    SQLiteAssetHelper(context, "AIJ_DB.s3db", "/data/data/me.mitul.aij/databases", null, 1) {
    fun selectSplashTextData(id: Int): SplashScreen? {
        val database = getReadableDatabase()
        val cursor = database.rawQuery(
            "SELECT SplashText, TextSize, TextColor FROM MST_Splash WHERE _id = $id",
            null
        )
        val splashScreen = SplashScreen()
        if (cursor.moveToFirst()) {
            splashScreen.text = cursor.getString(cursor.getColumnIndex("SplashText"))
            splashScreen.textSize = cursor.getString(cursor.getColumnIndex("TextSize"))
            splashScreen.textColor = cursor.getString(cursor.getColumnIndex("TextColor"))
            return splashScreen
        }
        cursor.close()
        database.close()
        return null
    }
}
