package me.mitul.aij.bankbranch

import android.app.Activity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.ExpandableListView
import me.mitul.aij.R
import me.mitul.aij.common.CommonAdapter
import me.mitul.aij.common.CommonModel
import me.mitul.aij.utils.MyWatcher

class BankBranchListActivity : Activity() {
    private lateinit var dbHelper: BankBranchHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_common_listview)

        dbHelper = BankBranchHelper(applicationContext)
        val cities = dbHelper.selectCityForBankBranch()
        val branches = HashMap<String, List<CommonModel>>()
        for (city in cities) branches[city] = dbHelper.getBankBranchesFor(city)

        val listView = findViewById<ExpandableListView>(R.id.common_exp_lv).also {
            it.visibility = View.VISIBLE
            it.setAdapter(CommonAdapter(this.layoutInflater, cities, branches))
        }

        findViewById<EditText>(R.id.common_ed_search).addTextChangedListener(
            MyWatcher(cities, object : MyWatcher.ListFilter<String> {
                override fun setList(items: List<String>) = listView.setAdapter(
                    CommonAdapter(this@BankBranchListActivity.layoutInflater, items, branches)
                )

                override fun getText(item: String) = item
            })
        )
    }
}
