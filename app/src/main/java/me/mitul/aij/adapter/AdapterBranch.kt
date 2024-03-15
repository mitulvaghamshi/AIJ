package me.mitul.aij.adapter

import android.app.Activity
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import me.mitul.aij.R
import me.mitul.aij.model.Branch
import java.util.Locale

class AdapterBranch(
    private val activity: Activity,
    private val list: ArrayList<Branch>
) : BaseAdapter() {
    override fun getCount() = list.size

    override fun getItem(index: Int) = list[index]

    override fun getItemId(index: Int) = 0L

    override fun getView(index: Int, view: View?, viewGroup: ViewGroup): View {
        val holder: ViewBranchHolder

        var row = view
        if (row == null) {
            val layoutInflater = activity.layoutInflater
            row = layoutInflater.inflate(R.layout.branch_list_listview_items, null)
            holder = ViewBranchHolder(row)
            row.tag = holder
        } else {
            holder = row.tag as ViewBranchHolder
        }

        holder.txtBranchID.text = list[index].branchId.toString()
        holder.txtBranchNameName.text = list[index].branchName
        holder.txtCollageNum.text = String.format(
            Locale.getDefault(), "Found In %d Collages",
            list[index].collegeNumber
        )
        return row!!
    }

    private class ViewBranchHolder(view: View) {
        val txtBranchID: TextView
        val txtBranchNameName: TextView
        val txtCollageNum: TextView

        init {
            txtBranchID = view.findViewById(R.id.branch_list_item_branch_id)
            txtBranchNameName = view.findViewById(R.id.branch_list_item_branch_name)
            txtCollageNum = view.findViewById(R.id.branch_list_items_collage_count_number)
        }
    }
}
