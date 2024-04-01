package me.mitul.aij.common

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseExpandableListAdapter
import android.widget.TextView
import me.mitul.aij.R

class CommonAdapter(
    private val inflater: LayoutInflater,
    private val items: List<String>,
    private val children: HashMap<String, List<CommonModel>>,
) : BaseExpandableListAdapter() {
    override fun getGroup(groupPosition: Int) = items[groupPosition]

    override fun getGroupCount() = items.size

    override fun getGroupId(groupPosition: Int) = groupPosition.toLong()

    override fun getGroupView(
        groupPosition: Int,
        isExpanded: Boolean,
        view: View?,
        parent: ViewGroup,
    ): View {
        var row = view
        if (row == null) {
            row = inflater.inflate(R.layout.list_item_1, null)
            ViewHolder(row).also { row.tag = it }
        } else {
            row.tag as ViewHolder
        }.also {
            it.tvName.text = getGroup(groupPosition)
        }
        return row!!
    }

    override fun hasStableIds() = false

    override fun isChildSelectable(groupPosition: Int, childPosition: Int) = true

    private class ViewHolder(view: View) {
        val tvName: TextView = view.findViewById(R.id.li_tv_name)
    }

    override fun getChild(groupPosition: Int, childPosititon: Int) =
        children[items[groupPosition]]!![childPosititon]

    override fun getChildId(groupPosition: Int, childPosition: Int) = childPosition.toLong()

    override fun getChildView(
        groupPosition: Int,
        childPosition: Int,
        isLastChild: Boolean,
        view: View?,
        parent: ViewGroup,
    ): View {
        var row = view
        if (row == null) {
            row = inflater.inflate(R.layout.list_item_2, null)
            ViewHolder2(row).also { row.tag = it }
        } else {
            row.tag as ViewHolder2
        }.also {
            val (_, name, address) = getChild(groupPosition, childPosition)
            it.tvName.text = name
            it.tvAddress.text = address
        }
        return row!!
    }

    override fun getChildrenCount(groupPosition: Int) = children[items[groupPosition]]!!.size

    private class ViewHolder2(view: View) {
        val tvName: TextView = view.findViewById(R.id.li2_tv_name)
        val tvAddress: TextView = view.findViewById(R.id.li2_tv_address)
    }
}
