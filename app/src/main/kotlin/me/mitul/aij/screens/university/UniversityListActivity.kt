package me.mitul.aij.screens.university

import android.app.Activity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.textfield.TextInputEditText
import me.mitul.aij.R
import me.mitul.aij.adapters.UniversityAdapter
import me.mitul.aij.helpers.UniversityHelper
import me.mitul.aij.models.University
import me.mitul.aij.utils.TextFilter

class UniversityListActivity : Activity() {
    private lateinit var dbHelper: UniversityHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recyclerview)

        dbHelper = UniversityHelper(applicationContext)
        val items = dbHelper.getAll()

        val listview = findViewById<RecyclerView>(R.id.recyclerview).apply {
            layoutManager = LinearLayoutManager(applicationContext)
            adapter = UniversityAdapter(layoutInflater, items)
        }

        findViewById<TextInputEditText>(R.id.ed_search).addTextChangedListener(
            TextFilter(items, object : TextFilter.ListFilter<University> {
                override fun setList(items: List<University>) = listview.setAdapter(
                    UniversityAdapter(this@UniversityListActivity.layoutInflater, items)
                )

                override fun getText(item: University) = item.name
            })
        )
    }
}
