package me.mitul.aij.login

import android.app.Activity
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.AutoCompleteTextView
import android.widget.LinearLayout
import androidx.appcompat.widget.SwitchCompat
import androidx.core.content.edit
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import me.mitul.aij.R
import me.mitul.aij.home.HomeActivity
import me.mitul.aij.utils.Database
import kotlin.coroutines.EmptyCoroutineContext

class LoginActivity : Activity() {
    private lateinit var tvUsername: AutoCompleteTextView
    private lateinit var tvPassword: AutoCompleteTextView
    private lateinit var swKeepSigned: SwitchCompat

    private lateinit var fabLogin: FloatingActionButton
    private lateinit var loginForm: LinearLayout

    private lateinit var shake: Animation
    private lateinit var rotate: Animation

    private lateinit var prefs: SharedPreferences

    private lateinit var dbHelper: LoginModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        dbHelper = LoginModel(Database(applicationContext))

        prefs = getSharedPreferences(LoginModel.Keys.KEY_LOGIN_INFO, MODE_PRIVATE)

        shake = AnimationUtils.loadAnimation(applicationContext, R.anim.anim_shake)
        rotate = AnimationUtils.loadAnimation(applicationContext, R.anim.anim_rotate)

        tvUsername = findViewById(R.id.login_ed_username)
        tvPassword = findViewById(R.id.login_ed_password)
        swKeepSigned = findViewById(R.id.login_sw_keep_signed)
        loginForm = findViewById(R.id.login_form)

        if (intent.hasExtra(LoginModel.Keys.KEY_IS_REGISTERED)) {
            tvUsername.setText(intent.getStringExtra(LoginModel.Keys.KEY_USERNAME))
            tvPassword.setText(intent.getStringExtra(LoginModel.Keys.KEY_PASSWORD))
        } else if (prefs.getBoolean(LoginModel.Keys.KEY_KEEP_SIGNED, false)) {
            tvUsername.setText(prefs.getString(LoginModel.Keys.KEY_USERNAME, ""))
            tvPassword.setText(prefs.getString(LoginModel.Keys.KEY_PASSWORD, ""))
            swKeepSigned.isChecked = prefs.getBoolean(LoginModel.Keys.KEY_KEEP_SIGNED, false)
        }

        fabLogin = findViewById(R.id.login_fab_submit)
        fabLogin.setOnClickListener { attemptLogin() }

        findViewById<FloatingActionButton>(R.id.login_fab_register).setOnClickListener {
            startActivity(Intent(applicationContext, RegisterActivity::class.java))
        }
    }

    private fun attemptLogin() {
        val username = tvUsername.text.toString()
        val password = tvPassword.text.toString()

        if (username.isBlank()) {
            tvUsername.startAnimation(shake)
            return
        }
        if (password.isBlank()) {
            tvPassword.startAnimation(shake)
            return
        }

        fabLogin.startAnimation(rotate)

        CoroutineScope(EmptyCoroutineContext).launch {
            try {
                delay(timeMillis = 2000L)
                val id = dbHelper.login(username, password)
                if (id == -1) {
                    loginForm.startAnimation(shake)
                    return@launch
                }

                prefs.edit {
                    if (swKeepSigned.isChecked) {
                        putBoolean(LoginModel.Keys.KEY_KEEP_SIGNED, swKeepSigned.isChecked)
                        putString(LoginModel.Keys.KEY_USERNAME, tvUsername.text.toString())
                        putString(LoginModel.Keys.KEY_PASSWORD, tvPassword.text.toString())
                    } else clear()
                }

                startActivity(Intent(applicationContext, HomeActivity::class.java).apply {
                    putExtra(LoginModel.Keys.KEY_USER_ID, id)
                })
                finish()
            } catch (ignored: InterruptedException) {
            }
        }
    }
}
