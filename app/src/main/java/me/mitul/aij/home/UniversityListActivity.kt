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
import me.mitul.aij.utils.ListFilter
import me.mitul.aij.utils.MyTextWatcher

class UniversityListActivity : Activity() {
    private val dbHelper = HelperUniversity(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_common_listview)

        val universities = dbHelper.getUniversities()
        val listview = findViewById<ListView>(R.id.common_lv).also {
            it.visibility = View.VISIBLE
            it.isTextFilterEnabled = true
            it.setAdapter(AdapterUniversity(this.layoutInflater, universities))
            it.onItemClickListener = OnItemClickListener { _, view, _, _ ->
                startActivity(
                    Intent(applicationContext, DetailUniversityActivity::class.java)
                        .putExtra(
                            "id_to_find_university",
                            view.findViewById<TextView>(R.id.list_item_tv_name).tag.toString()
                        )
                )
            }
        }

        findViewById<EditText>(R.id.common_ed_search).addTextChangedListener(
            MyTextWatcher(universities, object : ListFilter<University> {
                override fun setList(list: List<University>) = listview.setAdapter(
                    AdapterUniversity(this@UniversityListActivity.layoutInflater, list)
                )

                override fun getFilterText(item: University) = item.name
            })
        )
    }
}
