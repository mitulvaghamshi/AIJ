package me.mitul.aij.utils

import android.view.View

fun View.animate(duration: Long, onComplete: (() -> Unit)? = null) =
    this.animate().setDuration(duration).alpha(0f).withEndAction {
        this.animate().alpha(1f)
        onComplete?.invoke()
    }
