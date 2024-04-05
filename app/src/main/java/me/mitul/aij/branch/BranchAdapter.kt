package me.mitul.aij.branch

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import me.mitul.aij.R
import me.mitul.aij.college.CollegeListActivity
import me.mitul.aij.utils.Keys

class BranchAdapter(
    private val inflater: LayoutInflater,
    private val items: List<BranchModel>,
) : RecyclerView.Adapter<BranchAdapter.ViewHolder>() {
    override fun getItemCount() = items.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        inflater.inflate(R.layout.list_item_branch, parent, false)
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.apply {
            val item = items[position]
            tvName.text = item.name
            tvCollegeCount.text = item.count
            itemContainer.setOnClickListener {
                holder.onClick(it.context, item.id.toString())
            }
        }
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvName: TextView = view.findViewById(R.id.b_li_name)
        val tvCollegeCount: TextView = view.findViewById(R.id.b_li_college_count)
        val itemContainer: LinearLayout = view.findViewById(R.id.b_li_container)

        fun onClick(context: Context, id: String) = context.startActivity(
            Intent(context, CollegeListActivity::class.java)
                .putExtra(Keys.KEY_FILTER_OPTION, Keys.KEY_FILTER_BRANCH)
                .putExtra(Keys.KEY_FILTER_ID, id)
        )
    }
}
