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
import me.mitul.aij.utils.ArrayListOps
import me.mitul.aij.utils.MyTextWatcher

class BranchListActivity : Activity() {
    private val dbHelper = HelperBranch(this@BranchListActivity)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_common_listview)
        findViewById<View>(R.id.expandableListView).visibility = View.GONE

        val branches = dbHelper.selectAllBranch()
        val listview = findViewById<ListView>(R.id.common_listview)
        listview.visibility = View.VISIBLE
        listview.setAdapter(AdapterBranch(this@BranchListActivity, branches))
        listview.isTextFilterEnabled = true
        listview.onItemClickListener = OnItemClickListener { _, view, _, _ ->
            startActivity(
                Intent(this@BranchListActivity, CollageListActivity::class.java)
                    .putExtra(
                        getString(R.string.selected_or_all_collages),
                        getString(R.string.branch_id)
                    )
                    .putExtra(
                        getString(R.string.id_branch_collage),
                        (view.findViewById<TextView>(R.id.branch_list_item_branch_id)).getText()
                            .toString()
                    )
            )
        }

        (findViewById<EditText>(R.id.edSearchCommon)).addTextChangedListener(
            MyTextWatcher(branches, object : ArrayListOps<Branch> {
                override fun onListSet(list: ArrayList<Branch>) =
                    listview.setAdapter(AdapterBranch(this@BranchListActivity, list))

                override fun getName(item: Branch) = item.branchName!!
            })
        )
    }
}
