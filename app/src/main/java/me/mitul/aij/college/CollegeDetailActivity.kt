package me.mitul.aij.college

import android.app.Activity
import android.os.Bundle
import android.widget.TextView
import me.mitul.aij.R
import me.mitul.aij.utils.Keys

class CollegeDetailActivity : Activity() {
    private lateinit var dbHelper: CollegeHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_college)

        val id = intent.getStringExtra(Keys.KEY_FILTER_ID) ?: return

        dbHelper = CollegeHelper(applicationContext)
        with(dbHelper.getCollegeBy(id)) {
            findViewById<TextView>(R.id.cd_tv_name).text = name
            findViewById<TextView>(R.id.cd_tv_code).text = code
            findViewById<TextView>(R.id.cd_tv_acronym).text = acronym
            findViewById<TextView>(R.id.cd_tv_phone).text = phone
            findViewById<TextView>(R.id.cd_tv_email).text = email
            findViewById<TextView>(R.id.cd_tv_website).text = website
            findViewById<TextView>(R.id.cd_tv_address).text = address
            findViewById<TextView>(R.id.cd_tv_established).text = established
            findViewById<TextView>(R.id.cd_tv_fees).text = String.format("₹ %s/-", fees)
            findViewById<TextView>(R.id.cd_tv_type).text = type
            findViewById<TextView>(R.id.cd_tv_hostel).text = hostel
            findViewById<TextView>(R.id.cd_tv_university).text = university
            findViewById<TextView>(R.id.cd_tv_branches).text = branches
        }
    }
}
