package me.mitul.aij.branch

import android.app.Activity
import android.os.Bundle
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import me.mitul.aij.R
import me.mitul.aij.utils.MyWatcher

class BranchListActivity : Activity() {
    private lateinit var dbHelper: BranchHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recyclerview)

        dbHelper = BranchHelper(applicationContext)
        val branches = dbHelper.getAll()

        val listview = findViewById<RecyclerView>(R.id.recyclerview).apply {
            layoutManager = LinearLayoutManager(applicationContext)
            adapter = BranchAdapter(layoutInflater, branches)
        }

        findViewById<EditText>(R.id.common_ed_search).addTextChangedListener(
            MyWatcher(branches, object : MyWatcher.ListFilter<BranchModel> {
                override fun setList(items: List<BranchModel>) = listview.setAdapter(
                    BranchAdapter(this@BranchListActivity.layoutInflater, items)
                )

                override fun getText(item: BranchModel) = item.name
            })
        )
    }
}
