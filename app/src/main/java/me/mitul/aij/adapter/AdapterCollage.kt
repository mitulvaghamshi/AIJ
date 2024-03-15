package me.mitul.aij.adapter

import android.app.Activity
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import me.mitul.aij.R
import me.mitul.aij.model.Collage

class AdapterCollage(
    private val activity: Activity,
    private val list: ArrayList<Collage>
) : BaseAdapter() {
    override fun getCount() = list.size

    override fun getItem(index: Int) = list[index]

    override fun getItemId(index: Int) = 0L

    override fun getView(index: Int, view: View?, viewGroup: ViewGroup): View {
        val holder: ViewCollageHolder

        var row = view
        if (row == null) {
            val inflater = activity.layoutInflater
            row = inflater.inflate(R.layout.collage_list_listview_items, null)
            holder = ViewCollageHolder(row)
            row.tag = holder
        } else {
            holder = row.tag as ViewCollageHolder
        }

        holder.txtID.text = list[index].collageId.toString()
        holder.txtName.text = list[index].collageName.toString()
        holder.txtCollageFees.text = list[index].fees.toString()
        holder.txtCollageHostel.text = list[index].hostel
        holder.txtCollageBranches.text = list[index].branches
        return row!!
    }

    private class ViewCollageHolder(view: View) {
        val txtID: TextView
        val txtName: TextView
        val txtCollageFees: TextView
        val txtCollageHostel: TextView
        val txtCollageBranches: TextView

        init {
            txtID = view.findViewById(R.id.collage_list_item_collage_id)
            txtName = view.findViewById(R.id.collage_list_item_collage_name)
            txtCollageFees = view.findViewById(R.id.collage_list_item_collage_fees)
            txtCollageHostel = view.findViewById(R.id.collage_list_item_collage_hostel)
            txtCollageBranches = view.findViewById(R.id.collage_list_item_collage_branches)
        }
    }
}
