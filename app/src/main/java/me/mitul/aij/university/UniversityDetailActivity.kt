package me.mitul.aij.university

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import me.mitul.aij.R
import me.mitul.aij.college.CollegeListActivity
import me.mitul.aij.utils.Keys

class UniversityDetailActivity : Activity() {
    private lateinit var dbHelper: UniversityHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_university)

        dbHelper = UniversityHelper(applicationContext)
        val id = intent.getStringExtra(Keys.KEY_FILTER_ID)
        val university = dbHelper.getBy(id) ?: return

        findViewById<TextView>(R.id.ud_tv_initials).text = university.acronym
        findViewById<TextView>(R.id.ud_tv_full_name).text = university.name
        findViewById<TextView>(R.id.ud_tv_address).text = university.address
        findViewById<TextView>(R.id.ud_tv_phone).text = university.phone
        findViewById<TextView>(R.id.ud_tv_website).text = university.website
        findViewById<TextView>(R.id.ud_tv_email).text = university.email
        findViewById<TextView>(R.id.ud_tv_type).text = university.type

        findViewById<Button>(R.id.ud_btn_colleges).apply {
            text = String.format("See colleges affiliated with %s", university.acronym)
            setOnClickListener {
                startActivity(Intent(applicationContext, CollegeListActivity::class.java).apply {
                    putExtra(Keys.KEY_FILTER_OPTION, Keys.KEY_FILTER_UNIVERSITY)
                    putExtra(Keys.KEY_FILTER_ID, id)
                })
            }
        }
    }
}
