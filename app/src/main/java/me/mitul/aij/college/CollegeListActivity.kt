package me.mitul.aij.college

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView.OnItemClickListener
import android.widget.EditText
import android.widget.ListView
import android.widget.TextView
import me.mitul.aij.R
import me.mitul.aij.utils.Keys
import me.mitul.aij.utils.MyWatcher

class CollegeListActivity : Activity() {
    private lateinit var dbHelper: CollegeHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_common_listview)

        dbHelper = CollegeHelper(applicationContext)
        val colleges = when (intent.getStringExtra(Keys.KEY_FILTER_OPTION)) {
            Keys.KEY_FILTER_BRANCH ->
                dbHelper.getByBranch(intent.getStringExtra(Keys.KEY_FILTER_ID))

            Keys.KEY_FILTER_UNIVERSITY ->
                dbHelper.getByUniversity(intent.getStringExtra(Keys.KEY_FILTER_ID))

            else -> dbHelper.getAll()
        }

        val listview = findViewById<ListView>(R.id.common_lv).also {
            it.visibility = View.VISIBLE
            it.isTextFilterEnabled = true
            it.setAdapter(CollegeAdapter(this.layoutInflater, colleges))
            it.onItemClickListener = OnItemClickListener { _, view, _, _ ->
                startActivity(
                    Intent(applicationContext, CollegeDetailActivity::class.java).putExtra(
                        Keys.KEY_FILTER_ID,
                        view.findViewById<TextView>(R.id.cl_li_name).tag.toString()
                    )
                )
            }
        }

        findViewById<EditText>(R.id.common_ed_search).addTextChangedListener(
            MyWatcher(colleges, object : MyWatcher.ListFilter<CollegeModel> {
                override fun setList(items: List<CollegeModel>) = listview.setAdapter(
                    CollegeAdapter(this@CollegeListActivity.layoutInflater, items)
                )

                override fun getText(item: CollegeModel) = item.name
            })
        )
    }
}
