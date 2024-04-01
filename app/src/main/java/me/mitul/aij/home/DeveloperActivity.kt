package me.mitul.aij.home

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import me.mitul.aij.R
import me.mitul.aij.college.CollegeDetailActivity
import me.mitul.aij.utils.Keys

class DeveloperActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_developer)

        findViewById<View>(R.id.dev_btn_aij).setOnClickListener {
            startActivity(
                Intent(applicationContext, CollegeDetailActivity::class.java)
                    .putExtra(Keys.KEY_FILTER_ID, 58)
            )
        }
    }
}
