package me.mitul.aij.helpcenter

import android.app.Activity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.ExpandableListView
import me.mitul.aij.R
import me.mitul.aij.common.CommonAdapter
import me.mitul.aij.common.CommonModel
import me.mitul.aij.utils.Database
import me.mitul.aij.utils.MyWatcher

class HelpCenterListActivity : Activity() {
    private lateinit var dbHelper: HelpCenterHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_common_listview)

        dbHelper = HelpCenterHelper(applicationContext)
        val cities = dbHelper.getCities()
        val centers = HashMap<String, List<CommonModel>>()
        for (city in cities) centers[city] = dbHelper.getHelpCenterFor(city)

        val listView = findViewById<ExpandableListView>(R.id.common_exp_lv).also {
            it.visibility = View.VISIBLE
            it.tag = -1
            it.setAdapter(CommonAdapter(this.layoutInflater, cities, centers))
            it.setOnGroupExpandListener { groupPos ->
                val pos = it.tag as Int
                if (pos != groupPos && pos != -1) it.collapseGroup(pos)
                it.tag = pos
            }
        }

        findViewById<EditText>(R.id.common_ed_search).addTextChangedListener(
            MyWatcher(cities, object : MyWatcher.ListFilter<String> {
                override fun setList(items: List<String>) = listView.setAdapter(
                    CommonAdapter(this@HelpCenterListActivity.layoutInflater, items, centers)
                )

                override fun getText(item: String) = item
            })
        )
    }
}
