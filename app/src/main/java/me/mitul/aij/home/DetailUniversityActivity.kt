package me.mitul.aij.home

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import me.mitul.aij.R
import me.mitul.aij.helper.HelperUniversity

class DetailUniversityActivity : Activity() {
    private val dbHelper = HelperUniversity(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_university)

        val id = intent.getStringExtra("id_to_find_university")
        val university = dbHelper.getUniversityBy(id)

        findViewById<TextView>(R.id.ud_tv_initials).text = university.initials
        findViewById<TextView>(R.id.ud_tv_full_name).text = university.name
        findViewById<TextView>(R.id.ud_tv_address).text = university.address
        findViewById<TextView>(R.id.ud_tv_phone).text = university.phone
        findViewById<TextView>(R.id.ud_tv_website).text = university.website
        findViewById<TextView>(R.id.ud_tv_email).text = university.email
        findViewById<TextView>(R.id.ud_tv_type).text = university.type

        findViewById<Button>(R.id.ud_btn_collages).also {
            it.text = String.format("Collages in %s", university.initials)
            it.setOnClickListener {
                startActivity(
                    Intent(this, CollageListActivity::class.java)
                        .putExtra("selected_or_all", "UNIVERSITY")
                        .putExtra("id_to_find_university", id)
                )
            }
        }
    }
}
