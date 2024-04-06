package me.mitul.aij.aij

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import me.mitul.aij.R
import me.mitul.aij.aij.FragmentCommon.Companion.newInstance

class CourseActivity : FragmentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.aij_activity_cources)

        val pager = findViewById<ViewPager>(R.id.splash_viewpager)
        pager.adapter = buildAdapter()

        findViewById<TabLayout>(R.id.tabs_courses).setupWithViewPager(pager)
    }

    private fun buildAdapter() = object : FragmentPagerAdapter(supportFragmentManager) {
        val fragments = buildList<Fragment>(capacity = 7) {
            repeat(times = 7) { add(newInstance(id = it + 6)) }
        }

        override fun getCount() = fragments.size
        override fun getItem(index: Int) = fragments[index]
    }
}
