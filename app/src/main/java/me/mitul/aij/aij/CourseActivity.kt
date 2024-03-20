package me.mitul.aij.aij

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import me.mitul.aij.R
import me.mitul.aij.adapter.AdapterViewPager
import me.mitul.aij.utils.PlaceHolderFragment.Companion.newInstance

class CourseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cources)

        val viewPager = findViewById<ViewPager>(R.id.viewpager_home_screen_aij)
        viewPager?.let { pager ->
            pager.setAdapter(AdapterViewPager(supportFragmentManager).also {
                it.addFragment(newInstance(6), "Architect")
                it.addFragment(newInstance(7), "Automobile")
                it.addFragment(newInstance(8), "Civil")
                it.addFragment(newInstance(9), "Computer")
                it.addFragment(newInstance(10), "Electrical")
                it.addFragment(newInstance(11), "Inst. & Control")
                it.addFragment(newInstance(12), "Mechanical")
            })
        }
        (findViewById<TabLayout>(R.id.tabs_courses)).setupWithViewPager(viewPager)
    }
}
