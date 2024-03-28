package me.mitul.aij.aij

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import me.mitul.aij.R
import me.mitul.aij.adapter.AdapterViewPager
import me.mitul.aij.utils.FragmentCommon.Companion.newInstance

class CourseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cources)

        val viewPager = findViewById<ViewPager>(R.id.common_viewpager).also { pager ->
            pager.setAdapter(AdapterViewPager(supportFragmentManager).also {
                it.add(title = "Architect", newInstance(6))
                it.add(title = "Automobile", newInstance(7))
                it.add(title = "Civil", newInstance(8))
                it.add(title = "Computer", newInstance(9))
                it.add(title = "Electrical", newInstance(10))
                it.add(title = "Inst. & Control", newInstance(11))
                it.add(title = "Mechanical", newInstance(12))
            })
        }
        findViewById<TabLayout>(R.id.tabs_courses).setupWithViewPager(viewPager)
    }
}
