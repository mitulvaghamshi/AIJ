package me.mitul.aij.utils

import android.graphics.Color
import me.mitul.aij.R

object Constants {
    const val DB_VERSION = 1
    const val DB_NAME = "AIJ_DB.s3db"
    const val DB_PATH = "/data/data/me.mitul.aij/databases"

    const val KEY_LOGIN_INFO = "login_info"
    const val KEY_IS_REGISTERED = "is_registered"
    const val KEY_USERNAME = "username"
    const val KEY_PASSWORD = "password"
    const val KEY_KEEP_SIGNED = "keep_me_signed"

    var colorList =
        intArrayOf(Color.RED, Color.GREEN, Color.BLUE, Color.YELLOW, Color.MAGENTA, Color.CYAN)
}
