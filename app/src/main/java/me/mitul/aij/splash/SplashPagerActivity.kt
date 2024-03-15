package me.mitul.aij.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.fragment.app.FragmentActivity
import androidx.viewpager.widget.ViewPager
import me.mitul.aij.R
import me.mitul.aij.adapter.AdapterViewPager
import me.mitul.aij.reg.LoginActivity
import me.mitul.aij.utils.FixedSpeedScroller
import me.mitul.aij.utils.SplashHolderFragment

class SplashScreenActivity : FragmentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.spalash)
        findViewById<ViewPager>(R.id.pager)?.let { pager ->
            val adapter = AdapterViewPager(supportFragmentManager)
            repeat(times = 7) {
                adapter.addFragment(SplashHolderFragment.newInstance(it), "fragment")
            }
            pager.setAdapter(adapter)
            try {
                val field = ViewPager::class.java.getDeclaredField("mScroller")
                field.isAccessible = true
                try {
                    field[pager] = FixedSpeedScroller(pager.context)
                } catch (ignored: IllegalAccessException) {
                } catch (ignored: IllegalArgumentException) {
                }
            } catch (ignored: NoSuchFieldException) {
            }
            Thread(object : Runnable {
                var value = 0
                val handler = Handler(Looper.getMainLooper())
                override fun run() {
                    while (true) {
                        try {
                            Thread.sleep(2000L)
                            handler.post {
                                pager.setCurrentItem(
                                    if (value <= 7) {
                                        value++
                                    } else {
                                        0.also { value = 0 }
                                    }, true
                                )
                            }
                        } catch (ignored: InterruptedException) {
                        }
                    }
                }
            }).start()
        }
        findViewById<View>(R.id.splash_fab_login_go).setOnClickListener { _ ->
            startActivity(Intent(applicationContext, LoginActivity::class.java))
            finish()
        }
    }
}
