package me.mitul.aij.home

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import me.mitul.aij.R
import me.mitul.aij.aij.HomeScreenAijActivity
import me.mitul.aij.utils.Consts

class HomeScreenActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val userId = intent.getIntExtra(Consts.KEY_USER_ID, -1)

        findViewById<Button>(R.id.btn_find).setOnClickListener { it.onClick() }
        findViewById<Button>(R.id.btn_collage).setOnClickListener {
            it.onClick(
                Intent(applicationContext, CollageListActivity::class.java)
                    .putExtra("selected_or_all", "ALL")
            )
        }
        findViewById<Button>(R.id.btn_branches).setOnClickListener {
            it.onClick(Intent(applicationContext, BranchListActivity::class.java))
        }
        findViewById<Button>(R.id.btn_university).setOnClickListener {
            it.onClick(Intent(applicationContext, UniversityListActivity::class.java))
        }
        findViewById<Button>(R.id.btn_help_centers).setOnClickListener {
            it.onClick(Intent(applicationContext, HelpCenterListActivity::class.java))
        }
        findViewById<Button>(R.id.btn_bank).setOnClickListener {
            it.onClick(Intent(applicationContext, BankBranchListActivity::class.java))
        }
        findViewById<Button>(R.id.btn_developer).setOnClickListener {
            it.onClick(Intent(applicationContext, DeveloperActivity::class.java))
        }
        findViewById<Button>(R.id.btn_scholarship).setOnClickListener { it.onClick() }
        findViewById<Button>(R.id.btn_qa).setOnClickListener { it.onClick() }
        findViewById<Button>(R.id.btn_admission).setOnClickListener { it.onClick() }
        findViewById<Button>(R.id.btn_2).setOnClickListener { it.onClick() }
        findViewById<Button>(R.id.btn_3).setOnClickListener { it.onClick() }
        findViewById<Button>(R.id.btn_4).setOnClickListener { it.onClick() }
        findViewById<Button>(R.id.btn_5).setOnClickListener { it.onClick() }
        findViewById<Button>(R.id.btn_6).setOnClickListener { it.onClick() }
        findViewById<Button>(R.id.btn_aij).setOnClickListener {
            it.onClick(Intent(applicationContext, HomeScreenAijActivity::class.java))
        }
        findViewById<Button>(R.id.btn_students).setOnClickListener { it.onClick() }
    }

    private fun View.onClick(intent: Intent? = null) {
        this.animate().setDuration(300L).alpha(0f).withEndAction {
            this.animate().alpha(1f)
            intent?.let { startActivity(it) }
        }
    }
}
