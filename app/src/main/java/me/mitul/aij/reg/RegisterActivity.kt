package me.mitul.aij.reg

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.text.TextUtils
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.AutoCompleteTextView
import android.widget.LinearLayout
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import me.mitul.aij.R
import me.mitul.aij.helper.HelperRegistration
import me.mitul.aij.home.HomeScreenActivity
import me.mitul.aij.utils.Consts
import kotlin.coroutines.EmptyCoroutineContext

class RegisterActivity : Activity() {
    private lateinit var tvUsername: AutoCompleteTextView
    private lateinit var tvEmail: AutoCompleteTextView
    private lateinit var tvPhone: AutoCompleteTextView
    private lateinit var tvCity: AutoCompleteTextView
    private lateinit var tvPassword: AutoCompleteTextView
    private lateinit var tvRePassword: AutoCompleteTextView

    private lateinit var fabRegister: FloatingActionButton
    private lateinit var registerForm: LinearLayout

    private lateinit var shake: Animation
    private lateinit var rotate: Animation

    private lateinit var intentLogin: Intent
    private lateinit var dbHelper: HelperRegistration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        intentLogin = Intent(applicationContext, LoginActivity::class.java)
        dbHelper = HelperRegistration(applicationContext)

        shake = AnimationUtils.loadAnimation(applicationContext, R.anim.shake)
        rotate = AnimationUtils.loadAnimation(applicationContext, R.anim.anim_fab)

        tvUsername = findViewById(R.id.reg_ed_username)
        tvEmail = findViewById(R.id.reg_ed_email)
        tvPhone = findViewById(R.id.reg_ed_phone)
        tvCity = findViewById(R.id.reg_ed_city)
        tvPassword = findViewById(R.id.reg_ed_password)
        tvRePassword = findViewById(R.id.reg_ed_re_password)
        registerForm = findViewById(R.id.registration_form)

        fabRegister = findViewById<FloatingActionButton>(R.id.reg_fab_register).also {
            it.setOnClickListener {
                CoroutineScope(EmptyCoroutineContext).launch { attemptRegister() }
            }
        }
        findViewById<FloatingActionButton>(R.id.reg_fab_login).setOnClickListener {
            startActivity(intentLogin)
            finish()
        }
    }

    private suspend fun attemptRegister() {
        val username = tvUsername.text.toString()
        val email = tvEmail.text.toString()
        val phone = tvPhone.text.toString()
        val city = tvCity.text.toString()
        val password = tvPassword.text.toString()
        val rePassword = tvRePassword.text.toString()

        if (TextUtils.isEmpty(username)) {
            tvUsername.startAnimation(shake)
            return
        }
        if (TextUtils.isEmpty(email) || !email.contains("@") || !email.contains(".")) {
            tvEmail.startAnimation(shake)
            return
        }
        if (TextUtils.isEmpty(phone)) {
            tvPhone.startAnimation(shake)
            return
        }
        if (TextUtils.isEmpty(city)) {
            tvCity.startAnimation(shake)
            return
        }
        if (TextUtils.isEmpty(password) || password.length < 6) {
            tvPassword.startAnimation(shake)
            return
        }
        if (TextUtils.isEmpty(rePassword) || password != rePassword) {
            tvRePassword.startAnimation(shake)
            return
        }

        fabRegister.startAnimation(rotate)

        coroutineScope {
            try {
                delay(timeMillis = 2000L)
                val deviceId =
                    Settings.System.getString(contentResolver, Settings.Secure.ANDROID_ID)
                val status = dbHelper.register(username, email, phone, city, password, deviceId)
                if (status != -1L) {
                    intentLogin.putExtra(Consts.KEY_IS_REGISTERED, true)
                        .putExtra(Consts.KEY_USERNAME, username)
                        .putExtra(Consts.KEY_PASSWORD, password)
                    startActivity(intentLogin)
                    finish()
                } else {
                    registerForm.startAnimation(shake)
                }
            } catch (ignored: InterruptedException) {
            }
        }
    }
}
