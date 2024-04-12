package me.mitul.aij.screens.city

import android.app.Activity
import android.os.Bundle
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import me.mitul.aij.helpers.CityHelper
import me.mitul.aij.R
import me.mitul.aij.adapters.AddressAdapter
import me.mitul.aij.models.City
import me.mitul.aij.utils.Keys
import me.mitul.aij.utils.TextFilter

class AddressListActivity : Activity() {
    private lateinit var dbHelper: CityHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recyclerview)

        val filter = intent.getStringExtra(Keys.KEY_FILTER_OPTION) ?: return
        val city = intent.getStringExtra(Keys.KEY_FILTER_ID) ?: return

        dbHelper = CityHelper(applicationContext)
        val items = dbHelper.getByCity(filter, city)

        val listview = findViewById<RecyclerView>(R.id.recyclerview).apply {
            layoutManager = LinearLayoutManager(applicationContext)
            adapter = AddressAdapter(layoutInflater, items)
        }

        findViewById<EditText>(R.id.ed_search).addTextChangedListener(
            TextFilter(items, object : TextFilter.ListFilter<City> {
                override fun setList(items: List<City>) = listview.setAdapter(
                    AddressAdapter(this@AddressListActivity.layoutInflater, items)
                )

                override fun getText(item: City) = item.name
            })
        )
    }
}
