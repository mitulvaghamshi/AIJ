package me.mitul.aij.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import me.mitul.aij.R
import me.mitul.aij.model.Collage

class AdapterIntake(
    private val inflater: LayoutInflater,
    private val items: List<Collage>,
) : BaseAdapter() {
    override fun getCount() = items.size

    override fun getItem(index: Int) = items[index]

    override fun getItemId(index: Int) = 0L

    override fun getView(index: Int, view: View?, viewGroup: ViewGroup): View {
        var row = view
        if (row == null) {
            row = inflater.inflate(R.layout.list_item_intake, null)
            ViewHolder(row).also { row.tag = it }
        } else {
            row.tag as ViewHolder
        }.also {
            it.tvBranch.text = items[index].branch
            it.tvSeats.text = items[index].seat.toString()
            it.tvVacant.text = items[index].vacant.toString()
        }
        return row!!
    }

    private class ViewHolder(view: View) {
        val tvBranch: TextView
        val tvSeats: TextView
        val tvVacant: TextView

        init {
            tvBranch = view.findViewById(R.id.intake_branch)
            tvSeats = view.findViewById(R.id.intake_seat)
            tvVacant = view.findViewById(R.id.intake_vacant)
        }
    }
}
