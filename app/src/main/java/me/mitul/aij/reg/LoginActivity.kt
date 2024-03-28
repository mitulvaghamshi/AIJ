package me.mitul.aij.reg

import android.app.Activity
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.text.TextUtils
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.AutoCompleteTextView
import android.widget.LinearLayout
import androidx.appcompat.widget.SwitchCompat
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import me.mitul.aij.R
import me.mitul.aij.helper.HelperLogin
import me.mitul.aij.home.HomeScreenActivity
import me.mitul.aij.utils.Consts
import kotlin.coroutines.EmptyCoroutineContext

class LoginActivity : Activity() {
    private lateinit var tvUsername: AutoCompleteTextView
    private lateinit var tvPassword: AutoCompleteTextView
    private lateinit var swKeepSigned: SwitchCompat

    private lateinit var fabLogin: FloatingActionButton
    private lateinit var loginForm: LinearLayout

    private lateinit var shake: Animation
    private lateinit var rotate: Animation

    private lateinit var intentHome: Intent
    private lateinit var prefs: SharedPreferences
    private lateinit var dbHelper: HelperLogin

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        intentHome = Intent(applicationContext, HomeScreenActivity::class.java)
        prefs = getSharedPreferences(Consts.KEY_LOGIN_INFO, MODE_PRIVATE)
        dbHelper = HelperLogin(applicationContext)

        shake = AnimationUtils.loadAnimation(applicationContext, R.anim.shake)
        rotate = AnimationUtils.loadAnimation(applicationContext, R.anim.anim_fab)

        tvUsername = findViewById(R.id.login_ed_username)
        tvPassword = findViewById(R.id.login_ed_password)
        swKeepSigned = findViewById(R.id.login_sw_keep_signed)
        loginForm = findViewById(R.id.login_form)

        if (intent.hasExtra(Consts.KEY_IS_REGISTERED)) {
            tvUsername.setText(intent.getStringExtra(Consts.KEY_USERNAME))
            tvPassword.setText(intent.getStringExtra(Consts.KEY_PASSWORD))
        } else if (prefs.getBoolean(Consts.KEY_KEEP_SIGNED, false)) {
            tvUsername.setText(prefs.getString(Consts.KEY_USERNAME, ""))
            tvPassword.setText(prefs.getString(Consts.KEY_PASSWORD, ""))
            swKeepSigned.isChecked = prefs.getBoolean(Consts.KEY_KEEP_SIGNED, false)
        }

        fabLogin = findViewById<FloatingActionButton>(R.id.login_fab_submit).also {
            it.setOnClickListener {
                CoroutineScope(EmptyCoroutineContext).launch { attemptLogin() }
            }
        }
        findViewById<FloatingActionButton>(R.id.login_fab_register).setOnClickListener {
            startActivity(Intent(applicationContext, RegisterActivity::class.java))
            finish()
        }
    }

    private suspend fun attemptLogin() {
        val username = tvUsername.text.toString()
        val password = tvPassword.text.toString()

        if (TextUtils.isEmpty(username)) {
            tvUsername.startAnimation(shake)
            return
        }
        if (TextUtils.isEmpty(password)) {
            tvPassword.startAnimation(shake)
            return
        }

        fabLogin.startAnimation(rotate)

        coroutineScope {
            try {
                delay(timeMillis = 2000L)
                val userId = dbHelper.login(username, password)
                if (userId == -1) {
                    loginForm.startAnimation(shake)
                    return@coroutineScope
                }

                val editor = prefs.edit()
                editor.putBoolean(Consts.KEY_KEEP_SIGNED, swKeepSigned.isChecked)
                if (swKeepSigned.isChecked) {
                    editor.putString(Consts.KEY_USERNAME, tvUsername.text.toString())
                    editor.putString(Consts.KEY_PASSWORD, tvPassword.text.toString())
                } else {
                    editor.clear()
                }
                editor.apply()

                startActivity(intentHome.putExtra(Consts.KEY_USER_ID, userId))
                finish()
            } catch (ignored: InterruptedException) {
            }
        }
    }
}
