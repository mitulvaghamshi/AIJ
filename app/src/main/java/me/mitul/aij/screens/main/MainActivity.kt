package me.mitul.aij.screens.main

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.widget.Button
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import me.mitul.aij.R
import me.mitul.aij.screens.auth.AuthActivity
import me.mitul.aij.utils.animate
import kotlin.coroutines.EmptyCoroutineContext

class MainActivity : FragmentActivity() {
    private val duration = 2000L
    private val pageCount = 8

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnContinue = findViewById<Button>(R.id.main_btn_continue)
        btnContinue.setOnClickListener {
            it.animate(duration = 300L)
            startActivity(Intent(applicationContext, AuthActivity::class.java))
            finish()
        }

        findViewById<ViewPager2>(R.id.main_viewpager).apply {
            adapter = object : FragmentStateAdapter(supportFragmentManager, lifecycle) {
                override fun getItemCount(): Int = pageCount
                override fun createFragment(position: Int) = MainFragment.Instance.new(position)
            }
            start { btnContinue.callOnClick() }
        }
    }

    private fun ViewPager2.start(onFail: () -> Unit) =
        CoroutineScope(EmptyCoroutineContext).launch {
            val handler = Handler(mainLooper)
            var index = 0
            while (true) try {
                val page = if (index < pageCount) index++ else 0.also { index = 0 }
                handler.post { this@start.currentItem = page }
                delay(duration)
            } catch (ignored: InterruptedException) {
                onFail()
            }
        }
}
