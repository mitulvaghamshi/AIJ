package me.mitul.aij.home

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.children
import me.mitul.aij.R
import me.mitul.aij.aij.AijActivity
import me.mitul.aij.bankbranch.BankBranchListActivity
import me.mitul.aij.branch.BranchListActivity
import me.mitul.aij.college.CollegeListActivity
import me.mitul.aij.helpcenter.HelpCenterListActivity
import me.mitul.aij.university.UniversityListActivity

class HomeActivity : Activity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        // val userId = intent.getIntExtra(Keys.KEY_USER_ID, -1)
        findViewById<ConstraintLayout>(R.id.home_button_container).children.forEach {
            if (it is Button) it.setOnClickListener(this)
        }
    }

    override fun onClick(view: View) {
        val clazz = when (view.id) {
            R.id.btn_college -> CollegeListActivity::class.java
            R.id.btn_branches -> BranchListActivity::class.java
            R.id.btn_university -> UniversityListActivity::class.java
            R.id.btn_help_centers -> HelpCenterListActivity::class.java
            R.id.btn_bank -> BankBranchListActivity::class.java
            R.id.btn_developer -> DeveloperActivity::class.java
            R.id.btn_aij -> AijActivity::class.java
            else -> null
        }
        view.animate().setDuration(300L).alpha(0f).withEndAction {
            view.animate().alpha(1f)
            clazz?.let { startActivity(Intent(applicationContext, it)) }
        }
    }
}

// R.id.btn_college
// R.id.btn_branches
// R.id.btn_university
// R.id.btn_help_centers
// R.id.btn_bank
// R.id.btn_developer
// R.id.btn_aij
// R.id.btn_scholarship
// R.id.btn_admission
// R.id.btn_students
// R.id.btn_qa
// R.id.btn_2
// R.id.btn_3
// R.id.btn_4
// R.id.btn_5
// R.id.btn_6
