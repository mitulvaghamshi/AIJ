package me.mitul.aij.home

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView.OnItemClickListener
import android.widget.EditText
import android.widget.ListView
import android.widget.TextView
import me.mitul.aij.R
import me.mitul.aij.adapter.AdapterUniversity
import me.mitul.aij.helper.HelperUniversity
import me.mitul.aij.model.University
import me.mitul.aij.utils.ArrayListOps
import me.mitul.aij.utils.MyTextWatcher

class UniversityListActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_common_listview)
        findViewById<View>(R.id.expandableListView).visibility = View.GONE

        val dbHelper = HelperUniversity(this)
        val universities = dbHelper.selectAllUniversity()
        val listview = findViewById<ListView>(R.id.common_listview)
        listview.visibility = View.VISIBLE
        listview.setAdapter(AdapterUniversity(this, universities))
        listview.isTextFilterEnabled = true
        listview.onItemClickListener = OnItemClickListener { _, view, _, _ ->
            startActivity(
                Intent(this, DetailUniversityActivity::class.java)
                    .putExtra(
                        "id_to_find_university",
                        (view.findViewById<TextView>(R.id.list_simple_public_id)).getText()
                            .toString()
                    )
            )
        }

        findViewById<EditText>(R.id.edSearchCommon).addTextChangedListener(
            MyTextWatcher(universities, object : ArrayListOps<University> {
                override fun onListSet(list: ArrayList<University>) =
                    listview.setAdapter(AdapterUniversity(this@UniversityListActivity, list))

                override fun getName(item: University) = item.universityName!!
            })
        )
    }
}
