package me.mitul.aij.utils

import android.graphics.Color
import me.mitul.aij.R

object Constants {
    var DB_VERSION = 1
    var DB_NAME = "AIJ_DB.s3db"
    var DB_PATH = "/data/data/me.mitul.aij/databases"

    var colorList =
        intArrayOf(Color.RED, Color.GREEN, Color.BLUE, Color.YELLOW, Color.BLACK, Color.MAGENTA)

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
}
