package me.mitul.aij.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import me.mitul.aij.R
import me.mitul.aij.models.City

class AddressAdapter(
    private val inflater: LayoutInflater,
    private val items: List<City>,
) : RecyclerView.Adapter<AddressAdapter.ViewHolder>() {
    override fun getItemCount() = items.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        inflater.inflate(R.layout.list_item_2, parent, false)
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.apply {
            val item = items[position]
            name.text = item.name
            address.text = item.address
        }
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val name: TextView = view.findViewById(R.id.li2_tv_name)
        val address: TextView = view.findViewById(R.id.li2_tv_address)
    }
}
