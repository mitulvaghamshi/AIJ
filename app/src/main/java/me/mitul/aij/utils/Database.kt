package me.mitul.aij.utils

import android.content.Context
import com.readystatesoftware.sqliteasset.SQLiteAssetHelper

private const val DB_NAME = "database.db"
private const val DB_VERSION = 1
private val FACTORY = null

class Database(context: Context) : SQLiteAssetHelper(context, DB_NAME, FACTORY, DB_VERSION)
