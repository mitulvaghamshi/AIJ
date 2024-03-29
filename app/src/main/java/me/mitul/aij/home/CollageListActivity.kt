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
import me.mitul.aij.utils.ListFilter
import me.mitul.aij.utils.MyTextWatcher

class CollageListActivity : Activity() {
    private val dbHelper = HelperCollage(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_common_listview)

        val collages = when (intent.getStringExtra("selected_or_all")) {
            "ALL" -> dbHelper.getAllCollage()
            "BRANCH" -> dbHelper.getCollageForBranch(intent.getStringExtra("id_branch_collage"))
            "UNIVERSITY" -> dbHelper.getCollageForUniversity(
                intent.getStringExtra("id_to_find_university")
            )

            else -> ArrayList()
        }

        val listview = findViewById<ListView>(R.id.common_lv).also {
            it.visibility = View.VISIBLE
            it.isTextFilterEnabled = true
            it.setAdapter(AdapterCollage(this.layoutInflater, collages))
            it.onItemClickListener = OnItemClickListener { _, view, _, _ ->
                startActivity(
                    Intent(this, DetailCollageActivity::class.java)
                        .putExtra(
                            "id_to_find",
                            view.findViewById<TextView>(R.id.cl_li_name).tag.toString()
                        )
                )
            }
        }

        findViewById<EditText>(R.id.common_ed_search).addTextChangedListener(
            MyTextWatcher(collages, object : ListFilter<Collage> {
                override fun setList(list: List<Collage>) = listview.setAdapter(
                    AdapterCollage(this@CollageListActivity.layoutInflater, list)
                )

                override fun getFilterText(item: Collage) = item.name
            })
        )
    }
}
