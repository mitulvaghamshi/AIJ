package me.mitul.aij.splash

import android.content.Context
import android.content.Intent
import android.graphics.Typeface
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Scroller
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager.widget.ViewPager
import me.mitul.aij.R
import me.mitul.aij.adapter.AdapterViewPager
import me.mitul.aij.helper.HelperSplashScreen
import me.mitul.aij.reg.LoginActivity

class SplashPagerActivity : FragmentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.spalash)

        val pager = findViewById<ViewPager>(R.id.pager)
        setAdapter(pager)
        startPager(pager)

        findViewById<View>(R.id.splash_fab_login_go).setOnClickListener {
            startActivity(Intent(applicationContext, LoginActivity::class.java))
            finish()
        }
    }

    private fun startPager(pager: ViewPager) {
        try {
            val field = ViewPager::class.java.getDeclaredField("mScroller")
            field.isAccessible = true
            field[pager] = FixedSpeedScroller(pager.context)
        } finally {
            Thread(MyRunnable(pager)).start()
        }
    }

    private fun setAdapter(pager: ViewPager) {
        val adapter = AdapterViewPager(supportFragmentManager)
        repeat(times = 7) {
            adapter.addFragment(SplashHolderFragment.newInstance(it), "Fragment-$it")
        }
        pager.setAdapter(adapter)
    }
}

private class MyRunnable(val pager: ViewPager) : Runnable {
    var value = 0
    val handler = Handler(Looper.getMainLooper())

    override fun run() {
        while (true) {
            try {
                Thread.sleep(2000L)
                val page = if (value <= 7) value++ else 0.also { value = 0 }
                handler.post { pager.setCurrentItem(page, true) }
            } catch (ignored: InterruptedException) {
            }
        }
    }
}

private class FixedSpeedScroller(context: Context?) : Scroller(context) {
    private val duration = 5000

    override fun startScroll(startX: Int, startY: Int, dx: Int, dy: Int) =
        super.startScroll(startX, startY, dx, dy, duration)

    override fun startScroll(startX: Int, startY: Int, dx: Int, dy: Int, duration: Int) =
        super.startScroll(startX, startY, dx, dy, this.duration)
}

class SplashHolderFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_splash, container, false)
        val argItemId = requireArguments().getInt("ARG_String_ID")
        if (argItemId == 0) return view
        val tv = view.findViewById<TextView>(R.id.fragment_tv_splash_text)
        val splashScreen = HelperSplashScreen(context).selectSplashTextData(argItemId)
        tv.text = splashScreen?.text
        //tv.setTextColor(getResources().getColor(getResources().getIdentifier(splashScreen.getTextColor(), "color", getContext().getPackageName())));
        tv.setTextSize(TypedValue.COMPLEX_UNIT_SP, 32f)
        /*Float.valueOf(splashScreen.getSplashTextSize())*/ /*getResources().getDimension(getResources().getIdentifier(splashScreen.getSplashTextSize(), "dimen", getContext().getPackageName()))*/
        tv.setTypeface(Typeface.createFromAsset(requireActivity().assets, "font/Precious.ttf"))
        return view
    }

    companion object {
        @JvmStatic
        fun newInstance(itemId: Int): SplashHolderFragment {
            val fragment = SplashHolderFragment()
            val args = Bundle()
            args.putInt("ARG_String_ID", itemId)
            fragment.setArguments(args)
            return fragment
        }
    }
}
