package me.mitul.aij.splash

import android.content.Context
import android.widget.Scroller

class SmoothScroller(context: Context?) : Scroller(context) {
    private val duration = 5000

    override fun startScroll(startX: Int, startY: Int, dx: Int, dy: Int) =
        super.startScroll(startX, startY, dx, dy, duration)

    override fun startScroll(startX: Int, startY: Int, dx: Int, dy: Int, duration: Int) =
        super.startScroll(startX, startY, dx, dy, this.duration)
}
