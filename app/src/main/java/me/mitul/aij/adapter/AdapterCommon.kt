package me.mitul.aij.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseExpandableListAdapter
import android.widget.TextView
import me.mitul.aij.R
import me.mitul.aij.model.Common

class AdapterCommonList(
    private val inflater: LayoutInflater,
    private val list: List<String>,
    private val childMap: HashMap<String, List<Common>>,
) : BaseExpandableListAdapter() {
    override fun getChild(groupPosition: Int, childPosititon: Int) =
        childMap[list[groupPosition]]!![childPosititon]

    override fun getChildId(groupPosition: Int, childPosition: Int) = childPosition.toLong()

    override fun getChildView(
        groupPosition: Int,
        childPosition: Int,
        isLastChild: Boolean,
        view: View?,
        parent: ViewGroup,
    ): View {
        val (_, name, address) = getChild(groupPosition, childPosition)
        var row = view
        if (row == null) {
            row = inflater.inflate(R.layout.list_item_2, null)
            ViewExpHolder(row).also { row.tag = it }
        } else {
            row.tag as ViewExpHolder
        }.also {
            it.tvName.text = name
            it.tvAddress.text = address
        }
        return row!!
    }

    override fun getChildrenCount(groupPosition: Int) = childMap[list[groupPosition]]!!.size

    override fun getGroup(groupPosition: Int) = list[groupPosition]

    override fun getGroupCount() = list.size

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
            row.findViewById<TextView>(R.id.list_item_tv_name).text = getGroup(groupPosition)
        }
        return row!!
    }

    override fun hasStableIds() = false

    override fun isChildSelectable(groupPosition: Int, childPosition: Int) = true

    private class ViewExpHolder(view: View) {
        val tvName: TextView
        val tvAddress: TextView

        init {
            tvName = view.findViewById(R.id.list_item_2_tv_name)
            tvAddress = view.findViewById(R.id.list_item_2_tv_address)
        }
    }
}
