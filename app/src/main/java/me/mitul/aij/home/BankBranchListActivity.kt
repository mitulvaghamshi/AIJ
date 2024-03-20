package me.mitul.aij.home

import android.app.Activity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.ExpandableListView
import me.mitul.aij.R
import me.mitul.aij.adapter.AdapterExpandableList
import me.mitul.aij.helper.HelperBankBranch
import me.mitul.aij.model.Common
import me.mitul.aij.utils.ArrayListOps
import me.mitul.aij.utils.MyTextWatcher

class BankBranchListActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_common_listview)
        findViewById<View>(R.id.common_listview).visibility = View.GONE

        val mDbHelper = HelperBankBranch(this)
        val list = mDbHelper.selectCityForBankBranch()
        val bankBranchMap = HashMap<String, List<Common>>()
        for (name in list) bankBranchMap[name] = ArrayList(mDbHelper.selectBankBranch(name))

        val listView = findViewById<ExpandableListView>(R.id.expandableListView)
        listView.visibility = View.VISIBLE
        listView.setAdapter(AdapterExpandableList(this, list, bankBranchMap))

        findViewById<EditText>(R.id.edSearchCommon).addTextChangedListener(
            MyTextWatcher(list, object : ArrayListOps<String> {
                override fun onListSet(list: ArrayList<String>) = listView.setAdapter(
                    AdapterExpandableList(this@BankBranchListActivity, list, bankBranchMap)
                )

                override fun getName(item: String) = item
            })
        )
    }
}
