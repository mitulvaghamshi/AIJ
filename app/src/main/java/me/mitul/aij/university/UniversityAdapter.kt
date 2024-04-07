package me.mitul.aij.university

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import me.mitul.aij.R
import me.mitul.aij.utils.Keys

class UniversityAdapter(
    private val inflater: LayoutInflater,
    private val items: List<UniversityModel>,
) : RecyclerView.Adapter<UniversityAdapter.ViewHolder>() {
    override fun getItemCount() = items.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        inflater.inflate(R.layout.list_item_1, parent, false)
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.label.apply {
            val item = items[position]
            text = item.name
            setOnClickListener {
                holder.onClick(it.context, item.id.toString())
            }
        }
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val label: TextView = view.findViewById(R.id.li_tv_label)

        fun onClick(context: Context, id: String) = context.startActivity(
            Intent(context, UniversityDetailActivity::class.java).putExtra(Keys.KEY_FILTER_ID, id)
        )
    }
}
