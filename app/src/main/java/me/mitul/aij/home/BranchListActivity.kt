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
import me.mitul.aij.adapter.AdapterBranch
import me.mitul.aij.helper.HelperBranch
import me.mitul.aij.model.Branch
import me.mitul.aij.utils.ListFilter
import me.mitul.aij.utils.MyTextWatcher

class BranchListActivity : Activity() {
    private val dbHelper = HelperBranch(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_common_listview)

        val branches = dbHelper.getAllBranches()

        val listview = findViewById<ListView>(R.id.common_lv).also {
            it.visibility = View.VISIBLE
            it.isTextFilterEnabled = true
            it.setAdapter(AdapterBranch(this.layoutInflater, branches))
            it.onItemClickListener = OnItemClickListener { _, view, _, _ ->
                startActivity(
                    Intent(this, CollageListActivity::class.java)
                        .putExtra("selected_or_all", "BRANCH")
                        .putExtra(
                            "id_branch_collage",
                            view.findViewById<TextView>(R.id.b_li_name).tag.toString()
                        )
                )
            }
        }

        findViewById<EditText>(R.id.common_ed_search).addTextChangedListener(
            MyTextWatcher(branches, object : ListFilter<Branch> {
                override fun setList(list: List<Branch>) =
                    listview.setAdapter(AdapterBranch(this@BranchListActivity.layoutInflater, list))

                override fun getFilterText(item: Branch) = item.name
            })
        )
    }
}
