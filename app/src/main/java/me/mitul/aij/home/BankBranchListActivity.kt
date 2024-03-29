package me.mitul.aij.home

import android.app.Activity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.ExpandableListView
import me.mitul.aij.R
import me.mitul.aij.adapter.AdapterCommon
import me.mitul.aij.helper.HelperBankBranch
import me.mitul.aij.model.Common
import me.mitul.aij.utils.ListFilter
import me.mitul.aij.utils.MyTextWatcher

class BankBranchListActivity : Activity() {
    private val dbHelper = HelperBankBranch(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_common_listview)

        val cities = dbHelper.selectCityForBankBranch()
        val branches = HashMap<String, List<Common>>()
        for (city in cities) branches[city] = dbHelper.getBankBranchesFor(city)

        val listView = findViewById<ExpandableListView>(R.id.common_exp_lv).also {
            it.visibility = View.VISIBLE
            it.setAdapter(AdapterCommon(this.layoutInflater, cities, branches))
        }

        findViewById<EditText>(R.id.common_ed_search).addTextChangedListener(
            MyTextWatcher(cities, object : ListFilter<String> {
                override fun setList(list: List<String>) = listView.setAdapter(
                    AdapterCommon(
                        this@BankBranchListActivity.layoutInflater,
                        list, branches
                    )
                )

                override fun getFilterText(item: String) = item
            })
        )
    }
}
