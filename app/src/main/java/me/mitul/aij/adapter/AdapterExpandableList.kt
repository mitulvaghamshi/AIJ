package me.mitul.aij.adapter

import android.app.Activity
import android.graphics.Typeface
import android.view.View
import android.view.ViewGroup
import android.widget.BaseExpandableListAdapter
import android.widget.TextView
import me.mitul.aij.R
import me.mitul.aij.model.Common

class AdapterExpandableList(
    private val activity: Activity,
    private val headerList: List<String>,
    private val childMap: HashMap<String, List<Common>>
) : BaseExpandableListAdapter() {
    override fun getChild(groupPosition: Int, childPosititon: Int) =
        childMap[headerList[groupPosition]]!![childPosititon]

    override fun getChildId(groupPosition: Int, childPosition: Int) = childPosition.toLong()

    override fun getChildView(
        groupPosition: Int,
        childPosition: Int,
        isLastChild: Boolean,
        convertView: View?,
        parent: ViewGroup
    ): View {
        val (_, name, address) = getChild(groupPosition, childPosition) as Common
        val holder: ViewExpHolder

        var row = convertView
        if (row == null) {
            row = activity.layoutInflater.inflate(R.layout.simple_two_tv_listview_items, null)
            holder = ViewExpHolder(row)
            row.tag = holder
        } else {
            holder = row.tag as ViewExpHolder
        }

        holder.txtListChild.text = name
        holder.txtListChild2.text = address
        return row!!
    }

    override fun getChildrenCount(groupPosition: Int) =
        childMap[headerList[groupPosition]]!!.size

    override fun getGroup(groupPosition: Int) = headerList[groupPosition]

    override fun getGroupCount() = headerList.size

    override fun getGroupId(groupPosition: Int) = groupPosition.toLong()

    override fun getGroupView(
        groupPosition: Int,
        isExpanded: Boolean,
        convertView: View?,
        parent: ViewGroup
    ): View {
        val headerTitle = getGroup(groupPosition)

        var row = convertView
        if (row == null) {
            row = activity.layoutInflater.inflate(R.layout.simple_one_tv_listview_items, null)
            val lblListHeader = row.findViewById<TextView>(R.id.list_simple_public_name)
            lblListHeader.setTypeface(null, Typeface.BOLD)
            lblListHeader.text = headerTitle
        }
        return row!!
    }

    override fun hasStableIds() = false

    override fun isChildSelectable(groupPosition: Int, childPosition: Int) = true

    private class ViewExpHolder(view: View) {
        val txtListChild: TextView
        val txtListChild2: TextView

        init {
            txtListChild = view.findViewById(R.id.list_simple_two_tv_listview_name)
            txtListChild2 = view.findViewById(R.id.list_simple_two_tv_listview_address)
        }
    }
}
