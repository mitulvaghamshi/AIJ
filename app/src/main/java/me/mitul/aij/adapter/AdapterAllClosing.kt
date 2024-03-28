package me.mitul.aij.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import me.mitul.aij.R
import me.mitul.aij.model.Closing

class AdapterAllClosing(
    private val inflater: LayoutInflater,
    private val items: List<Closing>,
) : BaseAdapter() {
    override fun getCount() = items.size

    override fun getItemId(index: Int) = 0L

    override fun getItem(index: Int) = items[index]

    override fun getView(index: Int, view: View?, viewGroup: ViewGroup): View {
        var row = view
        if (row == null) {
            row = inflater.inflate(R.layout.list_item_closing, null)
            ViewHolder(row).also { row.tag = it }
        } else {
            row.tag as ViewHolder
        }.also {
            it.tvBranchName.text = items[index].branchName
            it.tvTFWSValue.text = items[index].tFWSValue.toString()
            it.tvOpenValue.text = items[index].openValue.toString()
            it.tvSEBCValue.text = items[index].sEBCValue.toString()
            it.tvEbcValue.text = items[index].oBCValue.toString()
            it.tvScValue.text = items[index].sCValue.toString()
            it.tvSTValue.text = items[index].sTValue.toString()
        }
        return row!!
    }

    private class ViewHolder(view: View) {
        val tvBranchName: TextView
        val tvTFWSValue: TextView
        val tvOpenValue: TextView
        val tvEbcValue: TextView
        val tvSEBCValue: TextView
        val tvScValue: TextView
        val tvSTValue: TextView

        init {
            tvBranchName = view.findViewById(R.id.closing_tv_branch)
            tvTFWSValue = view.findViewById(R.id.closing_tv_tfw)
            tvOpenValue = view.findViewById(R.id.closing_tv_open)
            tvSEBCValue = view.findViewById(R.id.closing_tv_sebc)
            tvEbcValue = view.findViewById(R.id.closing_tv_ebc)
            tvScValue = view.findViewById(R.id.closing_tv_sc)
            tvSTValue = view.findViewById(R.id.closing_tv_st)
        }
    }
}
