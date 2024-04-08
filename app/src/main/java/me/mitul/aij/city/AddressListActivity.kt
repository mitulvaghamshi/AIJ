package me.mitul.aij.city

import android.app.Activity
import android.os.Bundle
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import me.mitul.aij.R
import me.mitul.aij.utils.Keys
import me.mitul.aij.utils.MyWatcher

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

        findViewById<EditText>(R.id.common_ed_search).addTextChangedListener(
            MyWatcher(items, object : MyWatcher.ListFilter<CityModel> {
                override fun setList(items: List<CityModel>) = listview.setAdapter(
                    AddressAdapter(this@AddressListActivity.layoutInflater, items)
                )

                override fun getText(item: CityModel) = item.name
            })
        )
    }
}
