package me.mitul.aij.city

import android.app.Activity
import android.os.Bundle
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import me.mitul.aij.R
import me.mitul.aij.utils.Keys
import me.mitul.aij.utils.MyWatcher

class CityListActivity : Activity() {
    private lateinit var dbHelper: CityHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recyclerview)

        val filter = intent.getStringExtra(Keys.KEY_FILTER_OPTION) ?: return

        dbHelper = CityHelper(applicationContext)
        val cities = dbHelper.getAll(filter)

        val listview = findViewById<RecyclerView>(R.id.recyclerview).apply {
            layoutManager = LinearLayoutManager(applicationContext)
            adapter = CityAdapter(layoutInflater, cities, filter)
        }

        findViewById<EditText>(R.id.common_ed_search).addTextChangedListener(
            MyWatcher(cities, object : MyWatcher.ListFilter<CityModel> {
                override fun setList(items: List<CityModel>) = listview.setAdapter(
                    CityAdapter(this@CityListActivity.layoutInflater, items, filter)
                )

                override fun getText(item: CityModel) = item.city
            })
        )
    }
}
