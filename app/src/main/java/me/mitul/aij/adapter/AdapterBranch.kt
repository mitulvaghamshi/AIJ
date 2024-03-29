package me.mitul.aij.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import me.mitul.aij.R
import me.mitul.aij.model.Branch
import java.util.Locale

class AdapterBranch(
    private val inflater: LayoutInflater,
    private val items: List<Branch>,
) : BaseAdapter() {
    override fun getCount() = items.size

    override fun getItem(index: Int) = items[index]

    override fun getItemId(index: Int) = 0L

    override fun getView(index: Int, view: View?, viewGroup: ViewGroup): View {
        var row = view
        if (row == null) {
            row = inflater.inflate(R.layout.list_item_branch, null)
            ViewHolder(row).also { row.tag = it }
        } else {
            row.tag as ViewHolder
        }.also {
            it.tvName.tag = items[index].id.toString()
            it.tvName.text = items[index].name
            it.tvCollages.text = String.format(
                Locale.getDefault(),
                "Found in %d collages",
                items[index].collegeNumber
            )
        }
        return row!!
    }

    private class ViewHolder(view: View) {
        val tvName: TextView = view.findViewById(R.id.b_li_name)
        val tvCollages: TextView = view.findViewById(R.id.b_li_collages)
    }
}
