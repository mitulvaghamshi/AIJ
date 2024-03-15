package me.mitul.aij.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class AdapterViewPager(manager: FragmentManager?) : FragmentPagerAdapter(manager!!) {
    private val titleList = ArrayList<String>()
    private val fragmentList = ArrayList<Fragment>()

    fun addFragment(fragment: Fragment, title: String) {
        fragmentList.add(fragment)
        titleList.add(title)
    }

    override fun getCount() = fragmentList.size

    override fun getItem(index: Int) = fragmentList[index]

    override fun getPageTitle(index: Int) = titleList[index]
}
