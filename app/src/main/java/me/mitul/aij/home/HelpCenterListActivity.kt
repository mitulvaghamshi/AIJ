package me.mitul.aij.home

import android.app.Activity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.ExpandableListView
import me.mitul.aij.R
import me.mitul.aij.adapter.AdapterExpandableList
import me.mitul.aij.helper.HelperHelpCenter
import me.mitul.aij.model.Common
import me.mitul.aij.utils.ArrayListOps
import me.mitul.aij.utils.MyTextWatcher

class HelpCenterListActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_common_listview)
        findViewById<View>(R.id.common_listview).visibility = View.GONE

        val dbHelper = HelperHelpCenter(this)
        val cities = dbHelper.getHelpCenterCities()
        val helpCenterMap = HashMap<String, List<Common>>()
        for (city in cities) helpCenterMap[city] = ArrayList(dbHelper.getHelpCenterFor(city))
        val listView = findViewById<ExpandableListView>(R.id.expandableListView)
        listView.visibility = View.VISIBLE
        listView.setAdapter(AdapterExpandableList(this, cities, helpCenterMap))
        listView.tag = -1
        listView.setOnGroupExpandListener {
            val pos = listView.tag as Int
            if (pos != it && pos != -1) listView.collapseGroup(pos)
            listView.tag = it
        }

        findViewById<EditText>(R.id.edSearchCommon).addTextChangedListener(
            MyTextWatcher(cities, object : ArrayListOps<String> {
                override fun onListSet(list: ArrayList<String>) = listView.setAdapter(
                    AdapterExpandableList(this@HelpCenterListActivity, list, helpCenterMap)
                )

                override fun getName(item: String) = item
            })
        )
    }
}
