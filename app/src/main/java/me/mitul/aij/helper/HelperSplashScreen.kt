package me.mitul.aij.helper

import android.content.Context
import com.readystatesoftware.sqliteasset.SQLiteAssetHelper
import me.mitul.aij.model.SplashScreen
import me.mitul.aij.utils.Constants

class HelperSplashScreen(context: Context?) : SQLiteAssetHelper(
    context, Constants.DB_NAME, Constants.DB_PATH, null, Constants.DB_VERSION
) {
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
