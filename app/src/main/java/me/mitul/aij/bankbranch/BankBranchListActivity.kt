package me.mitul.aij.bankbranch

import android.app.Activity
import android.os.Bundle
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import me.mitul.aij.R
import me.mitul.aij.city.CityAdapter
import me.mitul.aij.city.CityModel
import me.mitul.aij.utils.MyWatcher

class BankBranchListActivity : Activity() {
    private lateinit var dbHelper: BankBranchHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_recyclerview)

        dbHelper = BankBranchHelper(applicationContext)
        val cities = dbHelper.getAll()

        val listview = findViewById<RecyclerView>(R.id.recyclerview).apply {
            layoutManager = LinearLayoutManager(applicationContext)
            adapter = CityAdapter(layoutInflater, cities)
        }

        findViewById<EditText>(R.id.common_ed_search).addTextChangedListener(
            MyWatcher(cities, object : MyWatcher.ListFilter<CityModel> {
                override fun setList(items: List<CityModel>) = listview.setAdapter(
                    CityAdapter(this@BankBranchListActivity.layoutInflater, items)
                )

                override fun getText(item: CityModel) = item.city
            })
        )

//        dbHelper = BankBranchHelper(applicationContext)
//        val cities = dbHelper.selectCityForBankBranch()
//        val branches = HashMap<String, List<CommonModel>>()
//        for (city in cities) branches[city] = dbHelper.getBankBranchesFor(city)

//        val listView = findViewById<ExpandableListView>(R.id.common_exp_lv).also {
//            it.visibility = View.VISIBLE
//            it.setAdapter(CommonAdapter(this.layoutInflater, cities, branches))
//        }

//        findViewById<EditText>(R.id.common_ed_search).addTextChangedListener(
//            MyWatcher(cities, object : MyWatcher.ListFilter<String> {
//                override fun setList(items: List<String>) = listView.setAdapter(
//                    CommonAdapter(this@BankBranchListActivity.layoutInflater, items, branches)
//                )
//
//                override fun getText(item: String) = item
//            })
//        )
    }
}
