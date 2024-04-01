package me.mitul.aij.college

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import me.mitul.aij.R

class CollegeAdapter(
    private val inflater: LayoutInflater,
    private val items: List<CollegeModel>,
) : BaseAdapter() {
    override fun getCount() = items.size

    override fun getItem(index: Int) = items[index]

    override fun getItemId(index: Int) = index.toLong()

    override fun getView(index: Int, view: View?, viewGroup: ViewGroup): View {
        var row = view
        if (row == null) {
            row = inflater.inflate(R.layout.list_item_college, null)
            ViewHolder(row).also { row.tag = it }
        } else {
            row.tag as ViewHolder
        }.also {
            it.tvName.tag = items[index].id.toString()
            it.tvName.text = items[index].name
            it.tvFees.text = String.format("Fees: ₹%s", items[index].fees)
            it.tcHostel.text = String.format("Hostel option: %s", items[index].hostel)
            it.tvBranches.text = items[index].branches
        }
        return row!!
    }

    private class ViewHolder(view: View) {
        val tvName: TextView = view.findViewById(R.id.cl_li_name)
        val tvFees: TextView = view.findViewById(R.id.cl_li_fees)
        val tcHostel: TextView = view.findViewById(R.id.cl_li_hostel)
        val tvBranches: TextView = view.findViewById(R.id.cl_li_branches)
    }
}
