package me.mitul.aij.splash

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
import me.mitul.aij.model.Splash
import me.mitul.aij.reg.LoginActivity
import kotlin.coroutines.EmptyCoroutineContext

class SplashPagerActivity : FragmentActivity() {
    private lateinit var dbHelper: HelperSplashScreen

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash_screen)

        dbHelper = HelperSplashScreen(applicationContext)

        findViewById<ViewPager>(R.id.splash_viewpager).start()
        findViewById<FloatingActionButton>(R.id.splash_fab_login).setOnClickListener {
            startActivity(Intent(applicationContext, LoginActivity::class.java))
            finish()
        }
    }

    private fun ViewPager.start() {
        this@start.adapter = AdapterViewPager(supportFragmentManager).also {
            it.add(title = "Frag0", SplashFragment(Splash(id = 0, text = "")))
            dbHelper.getSplashContent().forEach { item ->
                it.add(title = "Frag${item.id}", SplashFragment(item))
            }
        }

        try {
            ViewPager::class.java.getDeclaredField("mScroller").also {
                it.isAccessible = true
                it[this@start] = SmoothScroller(context)
            }
        } catch (ignored: NoSuchFieldException) {
            return
        }

        CoroutineScope(EmptyCoroutineContext).launch {
            val handler = Handler(mainLooper)
            var index = 0
            while (true) {
                try {
                    delay(timeMillis = 2000L)
                    val page = if (index <= 7) index++ else 0.also { index = 0 }
                    handler.post { this@start.setCurrentItem(page, true) }
                } catch (ignored: InterruptedException) {
                }
            }
        }
    }
}
