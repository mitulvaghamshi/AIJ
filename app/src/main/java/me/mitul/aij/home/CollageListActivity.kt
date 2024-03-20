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
import me.mitul.aij.adapter.AdapterCollage
import me.mitul.aij.helper.HelperCollage
import me.mitul.aij.model.Collage
import me.mitul.aij.utils.ArrayListOps
import me.mitul.aij.utils.MyTextWatcher

class CollageListActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_common_listview)
        findViewById<View>(R.id.expandableListView).visibility = View.GONE

        val dbHelper = HelperCollage(this)
        val list: ArrayList<Collage> =
            when (intent.getStringExtra("selected_or_all")) {
                "ALL" -> dbHelper.selectAllCollage()

                "BRANCH" -> dbHelper.selectBranchWiseCollage(
                    intent.getStringExtra("id_branch_collage")
                )

                "UNIVERSITY" -> dbHelper.selectUniversityWiseCollage(
                    intent.getStringExtra("id_to_find_university")
                )

                else -> ArrayList()
            }

        val listview = findViewById<ListView>(R.id.common_listview)
        listview.visibility = View.VISIBLE
        listview.setAdapter(AdapterCollage(this, list))
        listview.isTextFilterEnabled = true
        listview.onItemClickListener = OnItemClickListener { _, view, _, _ ->
            startActivity(
                Intent(this, DetailCollageActivity::class.java)
                    .putExtra(
                        "id_to_find",
                        (view.findViewById<TextView>(R.id.collage_list_item_collage_id))
                            .getText().toString()
                    )
            )
        }

        findViewById<EditText>(R.id.edSearchCommon).addTextChangedListener(
            MyTextWatcher(list, object : ArrayListOps<Collage> {
                override fun onListSet(list: ArrayList<Collage>) =
                    listview.setAdapter(AdapterCollage(this@CollageListActivity, list))

                override fun getName(item: Collage) = item.collageName!!
            })
        )
    }
}
