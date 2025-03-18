package me.mitul.aij.utils

import android.content.Context
import com.readystatesoftware.sqliteasset.SQLiteAssetHelper
import me.mitul.aij.utils.Keys.DB_NAME
import me.mitul.aij.utils.Keys.DB_VERSION

open class Database(context: Context) : SQLiteAssetHelper(context, DB_NAME, null, DB_VERSION)
