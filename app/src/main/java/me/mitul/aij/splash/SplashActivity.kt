package me.mitul.aij.splash

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.FragmentActivity
import androidx.viewpager.widget.ViewPager
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import me.mitul.aij.R
import me.mitul.aij.adapter.AdapterViewPager
import me.mitul.aij.helper.HelperSplashScreen
import me.mitul.aij.reg.LoginActivity
import kotlin.coroutines.EmptyCoroutineContext

@SuppressLint("CustomSplashScreen")
class SplashActivity : FragmentActivity() {
    private val dbHelper = HelperSplashScreen(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val items = dbHelper.getSplashContent()
        val adapter = AdapterViewPager(supportFragmentManager)
        adapter.add(title = "Frag0", SplashFragment.getInstance())
        items.forEach { adapter.add(title = "Splash${it}", SplashFragment.getInstance(it)) }

        val pager = findViewById<ViewPager>(R.id.common_viewpager).also { it.adapter = adapter }
        try {
            val field = ViewPager::class.java.getDeclaredField("mScroller")
            field.isAccessible = true
            field[pager] = SmoothScroller(applicationContext)
            pager.start()
        } catch (ignored: NoSuchFieldException) {
        }

        findViewById<FloatingActionButton>(R.id.splash_fab_login).setOnClickListener {
            startActivity(Intent(applicationContext, LoginActivity::class.java))
            finish()
        }
    }

    private fun ViewPager.start() = CoroutineScope(EmptyCoroutineContext).launch {
        val handler = Handler(mainLooper)
        var index = 0
        while (true) try {
            val page = if (index <= 7) index++ else 0.also { index = 0 }
            handler.post { this@start.currentItem = page }
            delay(timeMillis = 3000L)
        } catch (ignored: InterruptedException) {
        }
    }
}
