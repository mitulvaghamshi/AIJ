package me.mitul.aij.college

import android.app.Activity
import android.os.Bundle
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import me.mitul.aij.R
import me.mitul.aij.utils.Keys
import me.mitul.aij.utils.MyWatcher

class CollegeListActivity : Activity() {
    private lateinit var dbHelper: CollegeHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recyclerview)

        val filter = intent.getStringExtra(Keys.KEY_FILTER_OPTION)
        val id = intent.getStringExtra(Keys.KEY_FILTER_ID)
        dbHelper = CollegeHelper(applicationContext)
        val colleges = dbHelper.getColleges(filter = filter, id = id)

        val listview = findViewById<RecyclerView>(R.id.recyclerview).apply {
            layoutManager = LinearLayoutManager(applicationContext)
            adapter = CollegeAdapter(layoutInflater, colleges)
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
