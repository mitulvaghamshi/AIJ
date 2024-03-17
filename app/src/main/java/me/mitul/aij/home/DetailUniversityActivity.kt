package me.mitul.aij.home

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import me.mitul.aij.R
import me.mitul.aij.helper.HelperUniversity

class DetailUniversityActivity : Activity() {
    private val dbHelper = HelperUniversity(this@DetailUniversityActivity)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_university)

        val (universityID, universityName, universityShortName, universityAddress, universityWebsite, universityEmail, universityPhone, universityType)
                = dbHelper.selectUniversityByID(intent.getStringExtra(getString(R.string.id_university_to_find)))

        val tvUniversityId = findViewById<TextView>(R.id.university_detail_tv_university_id)
        tvUniversityId.text = universityID.toString()

        (findViewById<TextView>(R.id.university_detail_tv_university_shortname))
            .text = universityShortName
        (findViewById<TextView>(R.id.university_detail_tv_university_full_name))
            .text = universityName
        (findViewById<TextView>(R.id.university_detail_tv_university_address))
            .text = universityAddress
        (findViewById<TextView>(R.id.university_detail_tv_university_phone))
            .text = universityPhone
        (findViewById<TextView>(R.id.university_detail_tv_university_website))
            .text = universityWebsite
        (findViewById<TextView>(R.id.university_detail_tv_university_email))
            .text = universityEmail
        (findViewById<TextView>(R.id.university_detail_tv_university_type))
            .text = universityType

        val btnUniversity = findViewById<Button>(R.id.university_detail_tv_university_button)
        btnUniversity.text = String.format(getString(R.string.collage_in_ss), universityShortName)
        btnUniversity.setOnClickListener {
            startActivity(
                Intent(this@DetailUniversityActivity, CollageListActivity::class.java).putExtra(
                    getString(R.string.selected_or_all_collages),
                    getString(R.string.university_id_to_find_university)
                ).putExtra(
                    getString(R.string.id_university_to_find),
                    tvUniversityId.getText().toString()
                )
            )
        }
    }
}
