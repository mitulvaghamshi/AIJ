package me.mitul.aij.helpcenter

import android.app.Activity
import android.os.Bundle
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import me.mitul.aij.R
import me.mitul.aij.city.CityAdapter
import me.mitul.aij.city.CityModel
import me.mitul.aij.utils.MyWatcher

class HelpCenterListActivity : Activity() {
    private lateinit var dbHelper: HelpCenterHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recyclerview)

        dbHelper = HelpCenterHelper(applicationContext)
        val cities = dbHelper.getAll()

        val listview = findViewById<RecyclerView>(R.id.recyclerview).apply {
            layoutManager = LinearLayoutManager(applicationContext)
            adapter = CityAdapter(layoutInflater, cities)
        }

        findViewById<EditText>(R.id.common_ed_search).addTextChangedListener(
            MyWatcher(cities, object : MyWatcher.ListFilter<CityModel> {
                override fun setList(items: List<CityModel>) = listview.setAdapter(
                    CityAdapter(this@HelpCenterListActivity.layoutInflater, items)
                )

                override fun getText(item: CityModel) = item.city
            })
        )

//        dbHelper = HelpCenterHelper(applicationContext)
//        val cities = dbHelper.getCities()
//        val centers = HashMap<String, List<CityModel>>()
//        for (city in cities) centers[city] = dbHelper.getHelpCenterFor(city)
//
//        val listView = findViewById<ExpandableListView>(R.id.common_exp_lv).also {
//            it.visibility = View.VISIBLE
//            it.tag = -1
//            it.setAdapter(CommonAdapter(this.layoutInflater, cities, centers))
//            it.setOnGroupExpandListener { groupPos ->
//                val pos = it.tag as Int
//                if (pos != groupPos && pos != -1) it.collapseGroup(pos)
//                it.tag = pos
//            }
//        }
//
//        findViewById<EditText>(R.id.common_ed_search).addTextChangedListener(
//            MyWatcher(cities, object : MyWatcher.ListFilter<String> {
//                override fun setList(items: List<String>) = listView.setAdapter(
//                    CommonAdapter(this@HelpCenterListActivity.layoutInflater, items, centers)
//                )
//
//                override fun getText(item: String) = item
//            })
//        )
    }
}
