package me.mitul.aij.aij

import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import me.mitul.aij.R

class AijActivity : FragmentActivity() {
    private val items = listOf(1, 16, 21, 20, 22)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.aij_activity)

        findViewById<ViewPager2>(R.id.aij_viewpager).apply {
            adapter = object : FragmentStateAdapter(supportFragmentManager, lifecycle) {
                override fun getItemCount() = items.size
                override fun createFragment(position: Int) =
                    AijFragment.Instance.new(items[position])
            }
        }
    }
}
