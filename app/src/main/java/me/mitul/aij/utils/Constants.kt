package me.mitul.aij.utils

import android.graphics.Color
import me.mitul.aij.R

class Constants {
    var colorList =
        intArrayOf(Color.RED, Color.GREEN, Color.BLUE, Color.YELLOW, Color.BLACK, Color.MAGENTA)
    var dB_NAME = "AIJ_DB.s3db"
    var DB_PATH = "/data/data/me.mitul.dankawala/databases"
    var DATABASE_VERSION = 1
    var dIRECTORY_SDCARD = "aij_db"
    var pACKAGE_NAME = "me.mitul.aij"

    // public static void setTheme(Context context, int theme) {
    var dEVICE_ID: String? = null

    //     SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
    //     prefs.edit().putInt(context.getString(R.string.prefs_theme_key), theme).apply();
    // }
    //
    // public static int getTheme(Context context) {
    //     SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
    //     return prefs.getInt(context.getString(R.string.prefs_theme_key), -1);
    // }
    companion object {
        var drawableIcons = arrayOf(
            R.drawable.ic_brightness_1_black_24dp,
            R.drawable.ic_brightness_1_blue_24dp,
            R.drawable.ic_brightness_1_green_24dp,
            R.drawable.ic_brightness_1_orange_24dp,
            R.drawable.ic_brightness_1_pink_24dp,
            R.drawable.ic_brightness_1_red_24dp,
            R.drawable.ic_brightness_1_teal_24dp,
            R.drawable.ic_brightness_1_yellow_24dp,
            R.drawable.ic_brightness_1_brown_24dp
        )
        var KEY_ID = "id"
        var KEY_NAME = "name"
        var KEY_THUMB_URL = "thumb_url"
        var KEY_ENROLL = "enroll"
        var KEY_SERIAL = "serial"
    }
}
