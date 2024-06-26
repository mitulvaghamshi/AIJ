package me.mitul.aij.screens.university

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import me.mitul.aij.helpers.UniversityHelper
import me.mitul.aij.R
import me.mitul.aij.screens.college.CollegeListActivity
import me.mitul.aij.utils.Keys

class UniversityDetailActivity : Activity() {
    private lateinit var dbHelper: UniversityHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_university)

        val id = intent.getStringExtra(Keys.KEY_FILTER_ID) ?: return

        dbHelper = UniversityHelper(applicationContext)
        val university = dbHelper.getBy(id) ?: return

        findViewById<TextView>(R.id.ud_tv_initials).text = university.acronym
        findViewById<TextView>(R.id.ud_tv_full_name).text = university.name
        findViewById<TextView>(R.id.ud_tv_address).text = university.address
        findViewById<TextView>(R.id.ud_tv_phone).text = university.phone
        findViewById<TextView>(R.id.ud_tv_website).text = university.website
        findViewById<TextView>(R.id.ud_tv_email).text = university.email
        findViewById<TextView>(R.id.ud_tv_type).text = university.type

        findViewById<Button>(R.id.ud_btn_colleges).setOnClickListener {
            startActivity(Intent(applicationContext, CollegeListActivity::class.java).apply {
                putExtra(Keys.KEY_FILTER_OPTION, Keys.KEY_FILTER_UNIVERSITY)
                putExtra(Keys.KEY_FILTER_ID, id)
            })
        }
    }
}
