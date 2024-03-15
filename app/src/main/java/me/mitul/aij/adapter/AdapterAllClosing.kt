package me.mitul.aij.adapter

import android.app.Activity
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import me.mitul.aij.R
import me.mitul.aij.model.AllClosingList

class AdapterAllClosing(
    private val activity: Activity,
    private val list: ArrayList<AllClosingList>
) : BaseAdapter() {
    override fun getCount() = list.size

    override fun getItemId(index: Int) = 0L

    override fun getItem(index: Int) = list[index]

    override fun getView(index: Int, view: View?, viewGroup: ViewGroup): View {
        val holder: ViewAllClosingHolder

        var row = view
        if (row == null) {
            val inflater = activity.layoutInflater
            row = inflater.inflate(R.layout.closing_list_listview_items, null)
            holder = ViewAllClosingHolder(row)
            row.tag = holder
        } else {
            holder = row.tag as ViewAllClosingHolder
        }

        holder.closingTextViewBranchName.text = list[index].branchName
        holder.closingTextViewTfwsValue.text = list[index].tfwsValue.toString()
        holder.closingTextViewOpenValue.text = list[index].openValue.toString()
        holder.closingTextViewSebcValue.text = list[index].sebcValue.toString()
        holder.closingTextViewEbcValue.text = list[index].ebcValue.toString()
        holder.closingTextViewScValue.text = list[index].scValue.toString()
        holder.closingTextViewStValue.text = list[index].stValue.toString()
        return row!!
    }

    private class ViewAllClosingHolder(view: View?) {
        val closingTextViewBranchName: TextView
        val closingTextViewTfwsValue: TextView
        val closingTextViewOpenValue: TextView
        val closingTextViewEbcValue: TextView
        val closingTextViewSebcValue: TextView
        val closingTextViewScValue: TextView
        val closingTextViewStValue: TextView

        init {
            closingTextViewBranchName = view!!.findViewById(R.id.all_closing_tv_branch)
            closingTextViewTfwsValue = view.findViewById(R.id.all_closing_tv_tfw)
            closingTextViewOpenValue = view.findViewById(R.id.all_closing_tv_open)
            closingTextViewSebcValue = view.findViewById(R.id.all_closing_tv_sebc)
            closingTextViewEbcValue = view.findViewById(R.id.all_closing_tv_ebc)
            closingTextViewScValue = view.findViewById(R.id.all_closing_tv_sc)
            closingTextViewStValue = view.findViewById(R.id.all_closing_tv_st1)
        }
    }
}
