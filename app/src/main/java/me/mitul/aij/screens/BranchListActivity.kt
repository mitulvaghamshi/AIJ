package me.mitul.aij.screens

import android.app.Activity
import android.os.Bundle
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import me.mitul.aij.R
import me.mitul.aij.adapters.BranchAdapter
import me.mitul.aij.helpers.BranchHelper
import me.mitul.aij.models.Branch
import me.mitul.aij.utils.TextFilter

class BranchListActivity : Activity() {
    private lateinit var dbHelper: BranchHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recyclerview)

        dbHelper = BranchHelper(applicationContext)
        val items = dbHelper.getAll()

        val listview = findViewById<RecyclerView>(R.id.recyclerview).apply {
            layoutManager = LinearLayoutManager(applicationContext)
            adapter = BranchAdapter(layoutInflater, items)
        }

        findViewById<EditText>(R.id.ed_search).addTextChangedListener(
            TextFilter(items, object : TextFilter.ListFilter<Branch> {
                override fun setList(items: List<Branch>) = listview.setAdapter(
                    BranchAdapter(this@BranchListActivity.layoutInflater, items)
                )

                override fun getText(item: Branch) = item.name
            })
        )
    }
}
