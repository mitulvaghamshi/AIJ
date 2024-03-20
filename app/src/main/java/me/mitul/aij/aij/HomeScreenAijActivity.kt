package me.mitul.aij.aij

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.viewpager.widget.ViewPager
import com.google.android.material.navigation.NavigationView
import com.google.android.material.tabs.TabLayout
import me.mitul.aij.R
import me.mitul.aij.adapter.AdapterViewPager
import me.mitul.aij.utils.PlaceHolderFragment.Companion.newInstance

class HomeScreenAijActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_aij)

        val drawer = findViewById<DrawerLayout>(R.id.drawer_layout_home_screen_aij)
        findViewById<NavigationView>(R.id.navigation_drawer_home_screen_aij)?.let {
            it.setNavigationItemSelectedListener { item ->
                item.setChecked(true)
                drawer!!.closeDrawers()
                val id = findItemId(item.itemId)
                if (id == 0) return@setNavigationItemSelectedListener false
                val intent = Intent(this, AijExplorerActivity::class.java)
                startActivity(intent.putExtra("item_id", id))
                true
            }
        }
        val viewPager = findViewById<ViewPager>(R.id.viewpager_home_screen_aij)
        viewPager?.let { pager ->
            pager.setAdapter(AdapterViewPager(supportFragmentManager).also {
                it.addFragment(newInstance(1), "Welcome")
                it.addFragment(newInstance(16), "About")
                it.addFragment(newInstance(21), "Vision")
                it.addFragment(newInstance(20), "Mission")
                it.addFragment(newInstance(22), "Why AIJ")
            })
        }
        (findViewById<TabLayout>(R.id.tabs11)).setupWithViewPager(viewPager)
    }

    private fun findItemId(id: Int) = when (id) {
        R.id.nav_aij_faqs -> 1
        R.id.nav_aij_carrier -> 3
        R.id.nav_aij_admission_procedure -> 4
        R.id.nav_aij_eligibility_criteria -> 99
        R.id.nav_aij_facilities -> 13
        R.id.nav_aij_Activities -> 14
        R.id.nav_aij_policy -> 15
        R.id.nav_aij_about_shect -> 17
        R.id.nav_aij_infrastructure -> 18
        R.id.nav_placement -> 19
        R.id.nav_courses -> {
            startActivity(Intent(this, CourseActivity::class.java))
            0
        }

        else -> 0
    }
}
