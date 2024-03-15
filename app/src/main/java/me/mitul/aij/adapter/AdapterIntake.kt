package me.mitul.aij.adapter

import android.app.Activity
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import me.mitul.aij.R
import me.mitul.aij.model.Collage

class AdapterIntake(
    private val activity: Activity,
    private val list: ArrayList<Collage>
) :
    BaseAdapter() {
    override fun getCount() = list.size

    override fun getItem(index: Int) = list[index]

    override fun getItemId(index: Int) = 0L

    override fun getView(index: Int, view: View?, viewGroup: ViewGroup): View {
        val holder: ViewIntakeHolder

        var row = view
        if (view == null) {
            val inflater = activity.layoutInflater
            row = inflater.inflate(R.layout.intake_list_listview_items, null)
            holder = ViewIntakeHolder(row)
            row.tag = holder
        } else {
            holder = view.tag as ViewIntakeHolder
        }

        holder.txtClgDetailListBranch.text = list[index].lvBranch
        holder.txtClgDetailListSeat.text = list[index].lvSeat.toString()
        holder.txtClgDetailListVecent.text = list[index].lvVacant.toString()
        return row!!
    }

    private class ViewIntakeHolder(view: View) {
        val txtClgDetailListBranch: TextView
        val txtClgDetailListSeat: TextView
        val txtClgDetailListVecent: TextView

        init {
            txtClgDetailListBranch = view.findViewById(R.id.intake_detail_listview_branch)
            txtClgDetailListSeat = view.findViewById(R.id.intake_detail_listview_seat)
            txtClgDetailListVecent = view.findViewById(R.id.intake_detail_listview_vacant)
        }
    }
}
