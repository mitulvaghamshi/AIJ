package me.mitul.aij.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class AdapterViewPager(
    manager: FragmentManager,
    private val list: ArrayList<String> = ArrayList(),
    private val fragments: ArrayList<Fragment> = ArrayList(),
) : FragmentPagerAdapter(manager) {
    override fun getCount() = fragments.size

    override fun getItem(index: Int) = fragments[index]

    override fun getPageTitle(index: Int) = list[index]

    fun add(title: String, fragment: Fragment) {
        list.add(title)
        fragments.add(fragment)
    }
}
