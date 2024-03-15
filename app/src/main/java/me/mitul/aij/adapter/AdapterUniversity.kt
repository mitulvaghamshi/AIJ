package me.mitul.aij.adapter

import android.app.Activity
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import me.mitul.aij.R
import me.mitul.aij.model.University

class AdapterUniversity(
    private val activity: Activity,
    private val list: ArrayList<University>
) : BaseAdapter() {
    override fun getCount() = list.size

    override fun getItem(index: Int) = list[index]

    override fun getItemId(index: Int) = 0L

    override fun getView(index: Int, view: View?, viewGroup: ViewGroup): View {
        val holder: ViewUniversityHolder

        var row = view
        if (row == null) {
            val inflater = activity.layoutInflater
            row = inflater.inflate(R.layout.simple_one_tv_listview_items, null)
            holder = ViewUniversityHolder(row)
            row.tag = holder
        } else {
            holder = row.tag as ViewUniversityHolder
        }

        holder.universityID.text = list[index].universityID.toString()
        holder.universityName.text = list[index].universityName
        return row!!
    }

    private class ViewUniversityHolder(view: View) {
        val universityID: TextView
        val universityName: TextView

        init {
            universityID = view.findViewById(R.id.list_simple_public_id)
            universityName = view.findViewById(R.id.list_simple_public_name)
        }
    }
}
