package me.mitul.aij.home

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import me.mitul.aij.R
import me.mitul.aij.helper.HelperUniversity

class DetailUniversityActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_university)

        val (universityID, universityName, universityShortName, universityAddress, universityWebsite, universityEmail, universityPhone, universityType)
                = HelperUniversity(this).selectUniversityByID(intent.getStringExtra("id_to_find_university"))

        val tvUniversityId = findViewById<TextView>(R.id.university_detail_tv_university_id)
        tvUniversityId.text = universityID.toString()
        findViewById<TextView>(R.id.university_detail_tv_university_shortname).text =
            universityShortName
        findViewById<TextView>(R.id.university_detail_tv_university_full_name).text = universityName
        findViewById<TextView>(R.id.university_detail_tv_university_address).text =
            universityAddress
        findViewById<TextView>(R.id.university_detail_tv_university_phone).text = universityPhone
        findViewById<TextView>(R.id.university_detail_tv_university_website).text =
            universityWebsite
        findViewById<TextView>(R.id.university_detail_tv_university_email).text = universityEmail
        findViewById<TextView>(R.id.university_detail_tv_university_type).text = universityType

        val btnUniversity = findViewById<Button>(R.id.university_detail_tv_university_button)
        btnUniversity.text = String.format("Collages in %s", universityShortName)
        btnUniversity.setOnClickListener {
            startActivity(
                Intent(this, CollageListActivity::class.java)
                    .putExtra("selected_or_all", "UNIVERSITY")
                    .putExtra("id_to_find_university", tvUniversityId.getText().toString())
            )
        }
    }
}
