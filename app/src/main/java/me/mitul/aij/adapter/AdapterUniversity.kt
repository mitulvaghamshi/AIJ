package me.mitul.aij.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import me.mitul.aij.R
import me.mitul.aij.model.University

class AdapterUniversity(
    private val inflater: LayoutInflater,
    private val items: List<University>,
) : BaseAdapter() {
    override fun getCount() = items.size

    override fun getItem(index: Int) = items[index]

    override fun getItemId(index: Int) = 0L

    override fun getView(index: Int, view: View?, viewGroup: ViewGroup): View {
        var row = view
        if (row == null) {
            row = inflater.inflate(R.layout.list_item_1, null)
            ViewUniversityHolder(row).also { row.tag = it }
        } else {
            row.tag as ViewUniversityHolder
        }.also {
            it.tvUniversityName.text = items[index].name
            it.tvUniversityName.tag = items[index].id.toString()
        }
        return row!!
    }

    private class ViewUniversityHolder(view: View) {
        val tvUniversityName: TextView

        init {
            tvUniversityName = view.findViewById(R.id.list_item_tv_name)
        }
    }
}
