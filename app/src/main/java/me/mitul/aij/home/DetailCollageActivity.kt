package me.mitul.aij.home

import android.app.Activity
import android.os.Bundle
import android.widget.TextView
import me.mitul.aij.R
import me.mitul.aij.helper.HelperCollage

class DetailCollageActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_collage)

        val id = intent.getStringExtra("id_to_find")
        val (_, _, _, _, hostel, label, _, clgShortName, clgFullName, clgAddress, phone, web, email, fees, type, university)
                = HelperCollage(this).selectCollageByID(id)

        findViewById<TextView>(R.id.college_detail_tv_college_label)
            .text = label
        findViewById<TextView>(R.id.college_detail_tv_colleges_hort_name)
            .text = clgShortName
        findViewById<TextView>(R.id.college_detail_tv_college_full_name)
            .text = clgFullName
        findViewById<TextView>(R.id.college_detail_tv_college_address)
            .text = clgAddress
        findViewById<TextView>(R.id.college_detail_tv_phone_value)
            .text = phone
        findViewById<TextView>(R.id.college_detail_tv_website_value)
            .text = web
        findViewById<TextView>(R.id.college_detail_tv_email_value)
            .text = email
        findViewById<TextView>(R.id.college_detail_tv_fees_value)
            .text = String.format("%s/-", fees)
        findViewById<TextView>(R.id.college_detail_tv_college_type_value)
            .text = type
        findViewById<TextView>(R.id.college_detail_tv_college_type_hostel)
            .text = hostel
        findViewById<TextView>(R.id.college_detail_tv_college_type_university)
            .text = university

        this.setTitle(
            "College Code: ${(findViewById<TextView>(R.id.college_detail_tv_college_label)).getText()}"
        )

//        findViewById<TextView>(R.id.college_detail_btn_intake).setOnClickListener {
//            startActivity(
//                Intent(this@DetailCollageActivity, IntakeDetailActivity::class.java)
//                    .putExtra("getString(R.string.id_intake_to_find)", id)
//            )
//        }
//
//        findViewById<View>(R.id.college_detail_btn_closing).setOnClickListener {
//            startActivity(
//                Intent(this@DetailCollageActivity, AllClosingActivity::class.java)
//                    .putExtra("getString(R.string.id_to_find_closing)", id)
//                    .putExtra("getString(R.string.closing_collage_name)", clgFullName)
//            )
//        }
    }
}
