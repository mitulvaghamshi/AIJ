package me.mitul.aij.home

import android.app.Activity
import android.os.Bundle
import android.widget.TextView
import me.mitul.aij.R
import me.mitul.aij.helper.HelperCollage

class DetailCollageActivity : Activity() {
    private val dbHelper = HelperCollage(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_collage)

        val id = intent.getStringExtra("id_to_find")
        val collage = dbHelper.getCollageBy(id)
        this.setTitle("College Code: ${collage.code}")

        findViewById<TextView>(R.id.cd_tv_code).text = collage.code
        findViewById<TextView>(R.id.cd_tv_initials).text = collage.initials
        findViewById<TextView>(R.id.cd_tv_full_name).text = collage.fullName
        findViewById<TextView>(R.id.cd_tv_address).text = collage.address
        findViewById<TextView>(R.id.cd_tv_phone).text = collage.phone
        findViewById<TextView>(R.id.cd_tv_website).text = collage.web
        findViewById<TextView>(R.id.cd_tv_email).text = collage.email
        findViewById<TextView>(R.id.cd_tv_fees).text = String.format("%s/-", collage.fees)
        findViewById<TextView>(R.id.cd_tv_type).text = collage.type
        findViewById<TextView>(R.id.cd_tv_hostel).text = collage.hostel
        findViewById<TextView>(R.id.cd_tv_university).text = collage.university

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
