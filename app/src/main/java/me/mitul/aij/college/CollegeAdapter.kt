package me.mitul.aij.college

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import me.mitul.aij.R
import me.mitul.aij.utils.Keys

class CollegeAdapter(
    private val inflater: LayoutInflater,
    private val items: List<CollegeModel>,
) : RecyclerView.Adapter<CollegeAdapter.ViewHolder>() {
    override fun getItemCount() = items.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        inflater.inflate(R.layout.list_item_college, parent, false)
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.apply {
            val item = items[position]
            tvName.text = item.name
            tvFees.text = String.format("Fees: ₹ %s/-", item.fees)
            tcHostel.text = item.hostel
            tvBranches.text = item.branches
            itemContainer.setOnClickListener {
                holder.onClick(it.context, item.id.toString())
            }
        }
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvName: TextView = view.findViewById(R.id.cl_li_name)
        val tvFees: TextView = view.findViewById(R.id.cl_li_fees)
        val tcHostel: TextView = view.findViewById(R.id.cl_li_hostel)
        val tvBranches: TextView = view.findViewById(R.id.cl_li_branches)
        val itemContainer: LinearLayout = view.findViewById(R.id.cl_li_container)

        fun onClick(context: Context, id: String) = context.startActivity(
            Intent(context, CollegeDetailActivity::class.java).putExtra(Keys.KEY_FILTER_ID, id)
        )
    }
}
