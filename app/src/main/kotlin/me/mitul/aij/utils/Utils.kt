package me.mitul.aij.utils

import android.view.View

object Keys {
    const val KEY_FILTER_ID = "filter_id"
    const val KEY_FILTER_OPTION = "filter_option"
    const val KEY_FILTER_BRANCH = "filter_branch"
    const val KEY_FILTER_UNIVERSITY = "filter_university"
    const val KEY_FILTER_BANK_BRANCH = "filter_bank_branch"
    const val KEY_FILTER_HELP_CENTER = "filter_help_center"

    const val DB_VERSION = 1
    const val DB_NAME = "database.sqlite"
}

fun View.animate(duration: Long, onComplete: (() -> Unit)? = null) =
    animate().setDuration(duration).alpha(0f).withEndAction {
        animate().alpha(1f)
        onComplete?.invoke()
    }
