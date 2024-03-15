package me.mitul.aij.reg

import android.app.AlertDialog
import android.content.Intent
import android.os.AsyncTask
import android.os.Bundle
import android.provider.Settings
import android.text.TextUtils
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.AutoCompleteTextView
import androidx.appcompat.app.AppCompatActivity
import me.mitul.aij.R
import me.mitul.aij.helper.HelperRegistration

class RegisterActivity : AppCompatActivity() {
    private var editTextFullName: AutoCompleteTextView? = null
    private var editTextEmail: AutoCompleteTextView? = null
    private var editTextMobile: AutoCompleteTextView? = null
    private var editTextCity: AutoCompleteTextView? = null
    private var editTextPassword: AutoCompleteTextView? = null
    private var editTextPasswordRe: AutoCompleteTextView? = null
    private var progressView: View? = null
    private var regFormView: View? = null
    private var shake: Animation? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        editTextFullName = findViewById(R.id.full_name_reg)
        editTextEmail = findViewById(R.id.email_reg)
        editTextMobile = findViewById(R.id.mobile_reg)
        editTextCity = findViewById(R.id.city_reg)
        editTextPassword = findViewById(R.id.password_reg)
        editTextPasswordRe = findViewById(R.id.password_reg_re)
        progressView = findViewById(R.id.fab_register_go)
        regFormView = findViewById(R.id.reg_form)
        findViewById<View>(R.id.fab_register_go).setOnClickListener { view: View? -> attemptReg() }
        findViewById<View>(R.id.fab_skip_go).setOnClickListener { v: View? ->
            AlertDialog.Builder(this@RegisterActivity)
                .setMessage("Registration required!")
                .setCancelable(false)
                .setPositiveButton("OK", null)
                .create().show()
        }
        shake = AnimationUtils.loadAnimation(this@RegisterActivity, R.anim.shake)
    }

    private fun attemptReg() {
        var isValid = true
        val strTextFullName = editTextFullName!!.getText().toString()
        val strTextEmail = editTextEmail!!.getText().toString()
        val strTextMobile = editTextMobile!!.getText().toString()
        val strTextCity = editTextCity!!.getText().toString()
        val strTextPassword = editTextPassword!!.getText().toString()
        val strTextPasswordRe = editTextPasswordRe!!.getText().toString()
        val deviceId = Settings.System.getString(
            this@RegisterActivity.contentResolver,
            Settings.Secure.ANDROID_ID
        )
        if (TextUtils.isEmpty(strTextFullName)) {
            editTextFullName!!.startAnimation(shake)
            isValid = false
        }
        if (TextUtils.isEmpty(strTextEmail) || !strTextEmail.contains("@") || !strTextEmail.contains(
                "."
            )
        ) {
            editTextEmail!!.startAnimation(shake)
            isValid = false
        }
        if (TextUtils.isEmpty(strTextMobile)) {
            editTextMobile!!.startAnimation(shake)
            isValid = false
        }
        if (TextUtils.isEmpty(strTextCity)) {
            editTextCity!!.startAnimation(shake)
            isValid = false
        }
        if (TextUtils.isEmpty(strTextPassword) ||
            strTextPassword.length < 6
        ) {
            editTextPassword!!.startAnimation(shake)
            isValid = false
        }
        if (TextUtils.isEmpty(strTextPasswordRe) ||
            strTextPassword != strTextPasswordRe
        ) {
            editTextPasswordRe!!.startAnimation(shake)
            isValid = false
        }
        if (isValid) {
            UserRegTask().execute(
                strTextFullName,
                strTextEmail,
                strTextMobile,
                strTextCity,
                strTextPassword,
                strTextPasswordRe,
                deviceId
            )
        }
    }

    private inner class UserRegTask : AsyncTask<String?, Void?, Boolean>() {
        var email: String? = null
        var pass: String? = null

        @Deprecated("Deprecated in Java")
        override fun doInBackground(vararg params: String?): Boolean {
            try {
                Thread.sleep(2000)
                return HelperRegistration(this@RegisterActivity).attemptReg(
                    params[0].also { email = it },
                    params[1],
                    params[2],
                    params[3],
                    params[4].also { pass = it },
                    params[5],
                    params[6]
                )
            } catch (ignored: InterruptedException) {
            }
            return false
        }

        @Deprecated("Deprecated in Java")
        override fun onPreExecute() = progressView!!.startAnimation(
            AnimationUtils.loadAnimation(applicationContext, R.anim.anim_fab)
        )

        @Deprecated("Deprecated in Java")
        override fun onPostExecute(success: Boolean) = if (success) {
            val intent = Intent(this@RegisterActivity, LoginActivity::class.java)
            intent.putExtra("IsRegistered", true)
                .putExtra("UserName", email)
                .putExtra("Password", pass)
            startActivity(intent)
            finish()
        } else {
            regFormView!!.startAnimation(shake)
        }
    }
}
