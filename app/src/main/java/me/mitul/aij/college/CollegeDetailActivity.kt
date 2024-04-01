package me.mitul.aij.college

import android.app.Activity
import android.os.Bundle
import android.widget.TextView
import me.mitul.aij.R
import me.mitul.aij.utils.Database
import me.mitul.aij.utils.Keys

class CollegeDetailActivity : Activity() {
    private lateinit var dbHelper: CollegeHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_college)

        dbHelper = CollegeHelper(applicationContext)
        val id = intent.getStringExtra(Keys.KEY_FILTER_ID)
        val college = dbHelper.getCollegeBy(id)
        this.title = "College Code: " + college.code

        findViewById<TextView>(R.id.cd_tv_code).text = college.code
        findViewById<TextView>(R.id.cd_tv_initials).text = college.initials
        findViewById<TextView>(R.id.cd_tv_full_name).text = college.fullName
        findViewById<TextView>(R.id.cd_tv_address).text = college.address
        findViewById<TextView>(R.id.cd_tv_phone).text = college.phone
        findViewById<TextView>(R.id.cd_tv_website).text = college.web
        findViewById<TextView>(R.id.cd_tv_email).text = college.email
        findViewById<TextView>(R.id.cd_tv_fees).text = String.format("₹ %s/-", college.fees)
        findViewById<TextView>(R.id.cd_tv_type).text = college.type
        findViewById<TextView>(R.id.cd_tv_hostel).text = college.hostel
        findViewById<TextView>(R.id.cd_tv_university).text = college.university

//        findViewById<TextView>(R.id.college_detail_btn_intake).setOnClickListener {
//            startActivity(
//                Intent(this@DetailCollegeActivity, IntakeDetailActivity::class.java)
//                    .putExtra("id_intake_to_find", id)
//            )
//        }
//
//        findViewById<View>(R.id.college_detail_btn_closing).setOnClickListener {
//            startActivity(
//                Intent(this@DetailCollegeActivity, AllClosingActivity::class.java)
//                    .putExtra("id_to_find_closing", id)
//                    .putExtra("closing_college_name", clgFullName)
//            )
//        }
    }
}
