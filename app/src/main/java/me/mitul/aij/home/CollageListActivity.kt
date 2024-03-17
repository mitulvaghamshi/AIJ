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
    private val dbHelper = HelperCollage(this@CollageListActivity)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_common_listview)
        findViewById<View>(R.id.expandableListView).visibility = View.GONE

        val list: ArrayList<Collage> =
            when (intent.getStringExtra(getString(R.string.selected_or_all_collages))) {
                getString(R.string.retrieve_all_collages) -> dbHelper.selectAllCollage()

                getString(R.string.branch_id) -> dbHelper.selectBranchWiseCollage(
                    intent.getStringExtra(getString(R.string.id_branch_collage))
                )

                getString(R.string.university_id_to_find_university) -> dbHelper.selectUniversityWiseCollage(
                    intent.getStringExtra(getString(R.string.id_university_to_find))
                )

                else -> ArrayList()
            }

        val listview = findViewById<ListView>(R.id.common_listview)
        listview.visibility = View.VISIBLE
        listview.setAdapter(AdapterCollage(this@CollageListActivity, list))
        listview.isTextFilterEnabled = true
        listview.onItemClickListener = OnItemClickListener { _, view, _, _ ->
            startActivity(
                Intent(this@CollageListActivity, DetailCollageActivity::class.java)
                    .putExtra(
                        getString(R.string.id_to_find),
                        (view.findViewById<TextView>(R.id.collage_list_item_collage_id))
                            .getText().toString()
                    )
            )
        }

        (findViewById<EditText>(R.id.edSearchCommon)).addTextChangedListener(
            MyTextWatcher(list, object : ArrayListOps<Collage> {
                override fun onListSet(list: ArrayList<Collage>) =
                    listview.setAdapter(AdapterCollage(this@CollageListActivity, list))

                override fun getName(item: Collage) = item.collageName!!
            })
        )
    }
}
