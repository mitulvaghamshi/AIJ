package me.mitul.aij.screens.college

import android.app.Activity
import android.os.Bundle
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import me.mitul.aij.helpers.CollegeHelper
import me.mitul.aij.R
import me.mitul.aij.adapters.CollegeAdapter
import me.mitul.aij.models.College
import me.mitul.aij.utils.Keys
import me.mitul.aij.utils.TextFilter

class CollegeListActivity : Activity() {
    private lateinit var dbHelper: CollegeHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recyclerview)

        val filter = intent.getStringExtra(Keys.KEY_FILTER_OPTION)
        val id = intent.getStringExtra(Keys.KEY_FILTER_ID)

        dbHelper = CollegeHelper(applicationContext)
        val items = dbHelper.getAll(filter = filter, id = id)

        val listview = findViewById<RecyclerView>(R.id.recyclerview).apply {
            layoutManager = LinearLayoutManager(applicationContext)
            adapter = CollegeAdapter(layoutInflater, items)
        }

        findViewById<EditText>(R.id.ed_search).addTextChangedListener(
            TextFilter(items, object : TextFilter.ListFilter<College> {
                override fun setList(items: List<College>) = listview.setAdapter(
                    CollegeAdapter(this@CollegeListActivity.layoutInflater, items)
                )

                override fun getText(item: College) = item.name
            })
        )
    }
}
