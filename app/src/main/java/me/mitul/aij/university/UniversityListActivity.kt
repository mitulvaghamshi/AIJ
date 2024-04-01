package me.mitul.aij.university

import android.app.Activity
import android.os.Bundle
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import me.mitul.aij.R
import me.mitul.aij.utils.MyWatcher

class UniversityListActivity : Activity() {
    private lateinit var dbHelper: UniversityHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_common_listview)

        dbHelper = UniversityHelper(applicationContext)
        val universities = dbHelper.getAll()

        val listview = findViewById<RecyclerView>(R.id.recyclerview).apply {
            layoutManager = LinearLayoutManager(applicationContext)
            adapter = UniversityAdapter(layoutInflater, universities)
        }

        findViewById<EditText>(R.id.common_ed_search).addTextChangedListener(
            MyWatcher(universities, object : MyWatcher.ListFilter<UniversityModel> {
                override fun setList(items: List<UniversityModel>) = listview.setAdapter(
                    UniversityAdapter(this@UniversityListActivity.layoutInflater, items)
                )

                override fun getText(item: UniversityModel) = item.name
            })
        )
    }
}
