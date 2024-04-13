package me.mitul.aij.screens.main

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import me.mitul.aij.R
import me.mitul.aij.screens.auth.AuthActivity
import kotlin.coroutines.EmptyCoroutineContext

class MainActivity : FragmentActivity() {
    private val coroutineScope: CoroutineScope = CoroutineScope(EmptyCoroutineContext)
    private val mainScope: CoroutineScope = MainScope()
    private val duration = 2000L
    private val pageCount = 8

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.main_btn_continue).setOnClickListener { gotoLogin() }

        findViewById<ViewPager2>(R.id.main_viewpager).apply {
            adapter = object : FragmentStateAdapter(supportFragmentManager, lifecycle) {
                override fun getItemCount(): Int = pageCount
                override fun createFragment(position: Int) = MainFragment.Instance.new(position)
            }
        }.start()
    }

    private val gotoLogin = {
        startActivity(Intent(applicationContext, AuthActivity::class.java))
        finish()
    }

    private fun ViewPager2.start() = coroutineScope.launch {
        var index = 0
        while (true) try {
            val page = if (index < pageCount) index++ else 0.also { index = 0 }
            mainScope.launch { this@start.currentItem = page }
            delay(duration)
        } catch (ignored: InterruptedException) {
            gotoLogin()
        }
    }
}
