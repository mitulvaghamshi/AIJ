package me.mitul.aij.home

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.children
import me.mitul.aij.R
import me.mitul.aij.branch.BranchListActivity
import me.mitul.aij.city.CityListActivity
import me.mitul.aij.college.CollegeListActivity
import me.mitul.aij.university.UniversityListActivity
import me.mitul.aij.utils.Keys

class HomeActivity : Activity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        findViewById<ConstraintLayout>(R.id.home_button_container).children.forEach {
            if (it is Button) it.setOnClickListener(this)
        }
    }

    override fun onClick(view: View) {
        val intent = when (view.id) {
            R.id.btn_college -> Intent(applicationContext, CollegeListActivity::class.java)
            R.id.btn_branch -> Intent(applicationContext, BranchListActivity::class.java)
            R.id.btn_university -> Intent(applicationContext, UniversityListActivity::class.java)
            R.id.btn_help_center -> Intent(applicationContext, CityListActivity::class.java)
                .putExtra(Keys.KEY_FILTER_OPTION, Keys.KEY_FILTER_HELP_CENTER)

            R.id.btn_bank_branch -> Intent(applicationContext, CityListActivity::class.java)
                .putExtra(Keys.KEY_FILTER_OPTION, Keys.KEY_FILTER_BANK_BRANCH)

            R.id.btn_developer -> Intent(applicationContext, DeveloperActivity::class.java)
            else -> null
        }
        view.animate().setDuration(300L).alpha(0f).withEndAction {
            view.animate().alpha(1f)
            intent?.let { startActivity(it) }
        }
    }
}
