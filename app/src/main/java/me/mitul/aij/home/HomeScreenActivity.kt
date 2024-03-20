package me.mitul.aij.home

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.Toolbar
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import me.mitul.aij.R
import me.mitul.aij.aij.HomeScreenAijActivity
import me.mitul.aij.helper.HelperBackupRestore.Companion.exportDb
import me.mitul.aij.helper.HelperBackupRestore.Companion.restoreDb

class HomeScreenActivity : Activity(), View.OnClickListener {
    private lateinit var btnNew2: Button
    private lateinit var btnNew3: Button
    private lateinit var btnNew4: Button
    private lateinit var btnNew5: Button
    private lateinit var btnNew6: Button
    private lateinit var btnNew7: Button
    private lateinit var btnFind: Button
    private lateinit var btnCollage: Button
    private lateinit var btnBranch: Button
    private lateinit var btnUniversity: Button
    private lateinit var btnHelpCenter: Button
    private lateinit var btnScholarship: Button
    private lateinit var btnBankBranch: Button
    private lateinit var btnDeveloper: Button
    private lateinit var btnQA: Button
    private lateinit var btnAdmissionStep: Button
    private lateinit var btnStudentCorner: Button

    private lateinit var fabBackup: FloatingActionButton
    private lateinit var fabRestore: FloatingActionButton
    private lateinit var fabExit: FloatingActionButton

    private lateinit var exitDialog: AlertDialog.Builder

    private var localChecked: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_home_screen)

        exitDialog = AlertDialog.Builder(this)
        btnFind = findViewById<Button>(R.id.find).also { it.setOnClickListener(this) }
        btnCollage = findViewById<Button>(R.id.collage).also { it.setOnClickListener(this) }
        btnDeveloper = findViewById<Button>(R.id.developer).also { it.setOnClickListener(this) }
        btnHelpCenter = findViewById<Button>(R.id.help).also { it.setOnClickListener(this) }
        btnUniversity = findViewById<Button>(R.id.university).also { it.setOnClickListener(this) }
        btnAdmissionStep = findViewById<Button>(R.id.webAdmissionView2).also {
            it.setOnClickListener(this)
        }
        btnScholarship = findViewById<Button>(R.id.scholarship).also { it.setOnClickListener(this) }
        btnQA = findViewById<Button>(R.id.qa).also { it.setOnClickListener(this) }
        btnBranch = findViewById<Button>(R.id.branches).also { it.setOnClickListener(this) }
        btnBankBranch = findViewById<Button>(R.id.bank).also { it.setOnClickListener(this) }
        btnNew2 = findViewById<Button>(R.id.new2).also { it.setOnClickListener(this) }
        btnNew3 = findViewById<Button>(R.id.new3).also { it.setOnClickListener(this) }
        btnNew4 = findViewById<Button>(R.id.new4).also { it.setOnClickListener(this) }
        btnNew5 = findViewById<Button>(R.id.new5).also { it.setOnClickListener(this) }
        btnNew6 = findViewById<Button>(R.id.new6).also { it.setOnClickListener(this) }
        btnNew7 = findViewById<Button>(R.id.new7).also { it.setOnClickListener(this) }
        btnStudentCorner = findViewById<Button>(R.id.student_corner).also {
            it.setOnClickListener(this)
        }
        fabBackup = findViewById<FloatingActionButton>(R.id.fab_backup_go).also {
            it.setOnClickListener(this)
        }
        fabRestore = findViewById<FloatingActionButton>(R.id.fab_restore_go).also {
            it.setOnClickListener(this)
        }
        fabExit = findViewById<FloatingActionButton?>(R.id.fab_exit_now).also {
            it.setOnClickListener(this)
        }
        val userID = if (intent.hasExtra("UserID")) intent.getStringExtra("UserID")!!
            .toInt() else -1
        btnFind.setOnClickListener(this)
        btnCollage.setOnClickListener(this)
        btnDeveloper.setOnClickListener(this)
        btnHelpCenter.setOnClickListener(this)
        btnUniversity.setOnClickListener(this)
        btnAdmissionStep.setOnClickListener(this)
        btnScholarship.setOnClickListener(this)
        btnQA.setOnClickListener(this)
        btnBranch.setOnClickListener(this)
        btnBankBranch.setOnClickListener(this)
        btnNew2.setOnClickListener(this)
        btnNew3.setOnClickListener(this)
        btnNew4.setOnClickListener(this)
        btnNew5.setOnClickListener(this)
        btnNew6.setOnClickListener(this)
        btnNew7.setOnClickListener(this)
//        buttonClick=AnimationUtils.loadAnimation(getApplicationContext(), R.anim.click));
    }

    private fun animate() {
        Handler(Looper.getMainLooper()).post {
            btnFind.startAnimation(
                AnimationUtils.loadAnimation(applicationContext, R.anim.anim_1)
            )
            btnCollage.startAnimation(
                AnimationUtils.loadAnimation(applicationContext, R.anim.anim_2)
            )
            btnDeveloper.startAnimation(
                AnimationUtils.loadAnimation(applicationContext, R.anim.anim_3)
            )
            btnHelpCenter.startAnimation(
                AnimationUtils.loadAnimation(applicationContext, R.anim.anim_4)
            )
            btnUniversity.startAnimation(
                AnimationUtils.loadAnimation(applicationContext, R.anim.anim_5)
            )
            btnAdmissionStep.startAnimation(
                AnimationUtils.loadAnimation(applicationContext, R.anim.anim_6)
            )
            btnScholarship.startAnimation(
                AnimationUtils.loadAnimation(applicationContext, R.anim.anim_7)
            )
            btnQA.startAnimation(
                AnimationUtils.loadAnimation(applicationContext, R.anim.anim_8)
            )
            btnBranch.startAnimation(
                AnimationUtils.loadAnimation(applicationContext, R.anim.anim_9)
            )
            btnBankBranch.startAnimation(
                AnimationUtils.loadAnimation(applicationContext, R.anim.anim_1)
            )
            btnNew2.startAnimation(
                AnimationUtils.loadAnimation(applicationContext, R.anim.anim_2)
            )
            btnNew3.startAnimation(
                AnimationUtils.loadAnimation(applicationContext, R.anim.anim_3)
            )
            btnNew4.startAnimation(
                AnimationUtils.loadAnimation(applicationContext, R.anim.anim_4)
            )
            btnNew5.startAnimation(
                AnimationUtils.loadAnimation(applicationContext, R.anim.anim_5)
            )
            btnNew6.startAnimation(
                AnimationUtils.loadAnimation(applicationContext, R.anim.anim_6)
            )
            btnNew7.startAnimation(
                AnimationUtils.loadAnimation(applicationContext, R.anim.anim_7)
            )
            btnStudentCorner.startAnimation(
                AnimationUtils.loadAnimation(applicationContext, R.anim.anim_1)
            )
            fabBackup.startAnimation(
                AnimationUtils.loadAnimation(applicationContext, R.anim.anim_8)
            )
            fabRestore.startAnimation(
                AnimationUtils.loadAnimation(applicationContext, R.anim.anim_9)
            )
            fabExit.startAnimation(
                AnimationUtils.loadAnimation(applicationContext, R.anim.anim_5)
            )
        }
    }

    override fun onClick(view: View) {
        view.animate().setDuration(300L).alpha(0f).withEndAction {
            view.animate().alpha(1f)
            when (view) {
                btnFind -> Snackbar.make(view, "Not implemented", Snackbar.LENGTH_LONG).show()
                btnCollage -> startActivity(
                    Intent(applicationContext, CollageListActivity::class.java)
                        .putExtra("selected_or_all", "ALL")
                )

                btnDeveloper -> startActivity(
                    Intent(applicationContext, DeveloperActivity::class.java)
                )

                btnHelpCenter -> startActivity(
                    Intent(applicationContext, HelpCenterListActivity::class.java)
                )

                btnUniversity -> startActivity(
                    Intent(applicationContext, UniversityListActivity::class.java)
                )

                btnBranch -> startActivity(
                    Intent(applicationContext, BranchListActivity::class.java)
                )

                btnBankBranch -> startActivity(
                    Intent(applicationContext, BankBranchListActivity::class.java)
                )

                btnNew7 -> startActivity(
                    Intent(applicationContext, HomeScreenAijActivity::class.java)
                )

                fabBackup -> exportDb()
                fabRestore -> restoreDb()
                fabExit -> performExit()
            }
        }
    }

    private fun performExit() {
        localChecked = false
        exitDialog.setTitle("Do you want to exit?")
            .setMultiChoiceItems(arrayOf<CharSequence>("Log out?"), null) { _, _, isChecked ->
                localChecked = isChecked
            }
            .setPositiveButton("Yes") { _, _ ->
                if (localChecked) getSharedPreferences("Login1", 0)
                    .edit()
                    .putBoolean("keepMeLoggdIn", false)
                    .putString("Email1", "mady@me")
                    .putString("Pass1", "123456")
                    .apply()
                finish()
            }
            .setNegativeButton("No", null)
            .create()
            .show()
    }

//    override fun onNavigationItemSelected(item: MenuItem): Boolean {
//        findViewById<DrawerLayout>(R.id.drawer_layout).closeDrawer(GravityCompat.START)
//        return true
//    }

//    @Deprecated("Deprecated in Java")
//    override fun onBackPressed() {
//        val drawer = findViewById<DrawerLayout>(R.id.drawer_layout)
//        if (drawer.isDrawerOpen(GravityCompat.START)) drawer.closeDrawer(GravityCompat.START)
//        else performExit()
//    }
}
