package me.mitul.aij.screens.college

import android.app.Activity
import android.os.Bundle
import android.widget.TextView
import me.mitul.aij.helpers.CollegeHelper
import me.mitul.aij.R
import me.mitul.aij.utils.Keys

class CollegeDetailActivity : Activity() {
    private lateinit var dbHelper: CollegeHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_college)

        val id = intent.getStringExtra(Keys.KEY_FILTER_ID) ?: return

        dbHelper = CollegeHelper(applicationContext)
        val college = dbHelper.getBy(id) ?: return

        findViewById<TextView>(R.id.cd_tv_name).text = college.name
        findViewById<TextView>(R.id.cd_tv_code).text = college.code
        findViewById<TextView>(R.id.cd_tv_acronym).text = college.acronym
        findViewById<TextView>(R.id.cd_tv_phone).text = college.phone
        findViewById<TextView>(R.id.cd_tv_email).text = college.email
        findViewById<TextView>(R.id.cd_tv_website).text = college.website
        findViewById<TextView>(R.id.cd_tv_address).text = college.address
        findViewById<TextView>(R.id.cd_tv_established).text = college.established
        findViewById<TextView>(R.id.cd_tv_fees).text = String.format("₹ %s/-", college.fees)
        findViewById<TextView>(R.id.cd_tv_type).text = college.type
        findViewById<TextView>(R.id.cd_tv_hostel).text = college.hostel
        findViewById<TextView>(R.id.cd_tv_university).text = college.university
        findViewById<TextView>(R.id.cd_tv_branches).text = college.branches
    }
}
