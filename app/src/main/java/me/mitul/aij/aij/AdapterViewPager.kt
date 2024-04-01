package me.mitul.aij.aij

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class AdapterViewPager(
    manager: FragmentManager,
    private val fragments: ArrayList<Fragment> = ArrayList(),
) : FragmentPagerAdapter(manager) {
    override fun getCount() = fragments.size

    override fun getItem(index: Int) = fragments[index]

    fun add(fragment: Fragment) { fragments.add(fragment) }
}
