package me.mitul.aij.reg

import android.content.DialogInterface
import android.content.Intent
import android.os.AsyncTask
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.AutoCompleteTextView
import android.widget.CheckBox
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import me.mitul.aij.R
import me.mitul.aij.helper.HelperBackupRestore
import me.mitul.aij.helper.HelperLogin
import me.mitul.aij.home.HomeScreenActivity

class LoginActivity : AppCompatActivity() {
    private var emailView: AutoCompleteTextView? = null
    private var passwordView: AutoCompleteTextView? = null
    private var cbxKeepMeLoggedIn: CheckBox? = null
    private var progressView: View? = null
    private var loginFormView: View? = null
    private var shake: Animation? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        emailView = findViewById(R.id.login_email)
        passwordView = findViewById(R.id.login_password)
        cbxKeepMeLoggedIn = findViewById(R.id.checkBoxKeepMeLoggedIn)
        loginFormView = findViewById(R.id.login_form)
        progressView = findViewById(R.id.fab_login_go)
        val loginPreference = getSharedPreferences("Login1", 0)
        val intent = intent
        if (intent.getBooleanExtra("IsRegistered", false)) {
            emailView.setText(intent.getStringExtra("UserName"))
            passwordView.setText(intent.getStringExtra("Password"))
        }
        val firstRun = getPreferences(MODE_PRIVATE)
        if (firstRun.getBoolean("firstTime", true)) {
            if (HelperBackupRestore.sdDatabaseExists()) {
                AlertDialog.Builder(this).setIcon(R.drawable.ic_launcher)
                    .setTitle("Backup found!")
                    .setMessage("Do you want to restore previous data ?(Restart Required!)")
                    .setCancelable(false).setNegativeButton("No", null)
                    .setPositiveButton("Yes") { p1: DialogInterface?, p2: Int ->
                        HelperBackupRestore.restoreDb()
                        finish()
                        startActivity(getIntent())
                    }.create().show()
            }
            firstRun.edit().putBoolean("firstTime", false).apply()
        }
        if (loginPreference.getBoolean("keepMeLoggdIn", false)) {
            emailView.setText(loginPreference.getString("Email1", ""))
            passwordView.setText(loginPreference.getString("Pass1", ""))
            cbxKeepMeLoggedIn.setChecked(loginPreference.getBoolean("keepMeLoggdIn", false))
            // attemptLogin();
        }
        findViewById<View>(R.id.fab_login_go).setOnClickListener { view: View? -> attemptLogin() }
        findViewById<View>(R.id.fab_reg_new).setOnClickListener { v: View? ->
            startActivity(Intent(this@LoginActivity, RegisterActivity::class.java))
            finish()
        }
        shake = AnimationUtils.loadAnimation(this@LoginActivity, R.anim.shake)
    }

    override fun onStop() {
        super.onStop()
        val editor = getSharedPreferences("Login1", 0)
            .edit()
            .putBoolean("keepMeLoggdIn", cbxKeepMeLoggedIn!!.isChecked)
        if (cbxKeepMeLoggedIn!!.isChecked) {
            editor.putString("Email1", emailView!!.getText().toString())
                .putString("Pass1", passwordView!!.getText().toString())
        } else {
            editor.putString("Email1", "mady@me")
                .putString("Pass1", "123456")
        }
        editor.apply()
    }

    private fun attemptLogin() {
        var isValid = true
        val email = emailView!!.getText().toString()
        val pass = passwordView!!.getText().toString()
        if (TextUtils.isEmpty(email)) {
            emailView!!.startAnimation(shake)
            isValid = false
        }
        if (TextUtils.isEmpty(pass)) {
            passwordView!!.startAnimation(shake)
            isValid = false
        }
        if (isValid) {
            UserLoginTask().execute(email, pass)
        }
    }

    private inner class UserLoginTask : AsyncTask<String?, Void?, Int>() {
        protected override fun doInBackground(vararg strings: String): Int {
            try {
                Thread.sleep(2000)
                return HelperLogin(this@LoginActivity).attemptLogin(strings[0], strings[1])
            } catch (ignored: InterruptedException) {
            }
            return 999
        }

        override fun onPreExecute() {
            progressView!!.startAnimation(
                AnimationUtils.loadAnimation(
                    applicationContext, R.anim.anim_fab
                )
            )
        }

        override fun onPostExecute(localID: Int) {
            if (localID != 999) {
                val intent = Intent(applicationContext, HomeScreenActivity::class.java)
                startActivity(intent.putExtra("UserID", localID.toString()))
                finish()
            } else {
                loginFormView!!.startAnimation(shake)
            }
        }
    }
}
