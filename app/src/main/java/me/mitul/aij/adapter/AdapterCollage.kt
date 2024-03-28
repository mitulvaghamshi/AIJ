package me.mitul.aij.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import me.mitul.aij.R
import me.mitul.aij.model.Collage

class AdapterCollage(
    private val inflater: LayoutInflater,
    private val items: List<Collage>,
) : BaseAdapter() {
    override fun getCount() = items.size

    override fun getItem(index: Int) = items[index]

    override fun getItemId(index: Int) = 0L

    override fun getView(index: Int, view: View?, viewGroup: ViewGroup): View {
        var row = view
        if (row == null) {
            row = inflater.inflate(R.layout.collage_list_listview_items, null)
            ViewHolder(row).also { row.tag = it }
        } else {
            row.tag as ViewHolder
        }.also {
            it.tvId.text = items[index].id.toString()
            it.tvName.text = items[index].name.toString()
            it.tvFees.text = String.format("₹%s", items[index].fees)
            it.tcHostel.text = String.format("Hostel: %s", items[index].hostel)
            it.tvBranches.text = items[index].branches
        }
        return row!!
    }

    private class ViewHolder(view: View) {
        val tvId: TextView
        val tvName: TextView
        val tvFees: TextView
        val tcHostel: TextView
        val tvBranches: TextView

        init {
            tvId = view.findViewById(R.id.cl_li_id)
            tvName = view.findViewById(R.id.cl_li_name)
            tvFees = view.findViewById(R.id.cl_li_fees)
            tcHostel = view.findViewById(R.id.cl_li_hostel)
            tvBranches = view.findViewById(R.id.cl_li_branches)
        }
    }
}
