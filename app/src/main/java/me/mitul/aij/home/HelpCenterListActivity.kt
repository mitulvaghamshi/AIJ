package me.mitul.aij.home

import android.app.Activity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.ExpandableListView
import me.mitul.aij.R
import me.mitul.aij.adapter.AdapterCommon
import me.mitul.aij.helper.HelperHelpCenter
import me.mitul.aij.model.Common
import me.mitul.aij.utils.ListFilter
import me.mitul.aij.utils.MyTextWatcher

class HelpCenterListActivity : Activity() {
    private val dbHelper = HelperHelpCenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_common_listview)

        val cities = dbHelper.getCities()
        val centers = HashMap<String, List<Common>>()
        for (city in cities) centers[city] = dbHelper.getHelpCenterFor(city)

        val listView = findViewById<ExpandableListView>(R.id.common_exp_lv).also {
            it.visibility = View.VISIBLE
            it.tag = -1
            it.setAdapter(AdapterCommon(this.layoutInflater, cities, centers))
            it.setOnGroupExpandListener { groupPos ->
                val pos = it.tag as Int
                if (pos != groupPos && pos != -1) it.collapseGroup(pos)
                it.tag = pos
            }
        }

        findViewById<EditText>(R.id.common_ed_search).addTextChangedListener(
            MyTextWatcher(cities, object : ListFilter<String> {
                override fun setList(list: List<String>) = listView.setAdapter(
                    AdapterCommon(this@HelpCenterListActivity.layoutInflater, list, centers)
                )

                override fun getFilterText(item: String) = item
            })
        )
    }
}
