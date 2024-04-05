package me.mitul.aij.login

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.AutoCompleteTextView
import android.widget.LinearLayout
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import me.mitul.aij.R
import me.mitul.aij.utils.Database
import kotlin.coroutines.EmptyCoroutineContext

class RegisterActivity : Activity() {
    private lateinit var tvUsername: AutoCompleteTextView
    private lateinit var tvEmail: AutoCompleteTextView
    private lateinit var tvPhone: AutoCompleteTextView
    private lateinit var tvCity: AutoCompleteTextView
    private lateinit var tvPassword: AutoCompleteTextView
    private lateinit var tvRePassword: AutoCompleteTextView
    private lateinit var registerForm: LinearLayout

    private lateinit var shake: Animation
    private lateinit var dbHelper: LoginModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        dbHelper = LoginModel(Database(applicationContext))
        shake = AnimationUtils.loadAnimation(applicationContext, R.anim.anim_shake)

        tvUsername = findViewById(R.id.reg_ed_username)
        tvEmail = findViewById(R.id.reg_ed_email)
        tvPhone = findViewById(R.id.reg_ed_phone)
        tvCity = findViewById(R.id.reg_ed_city)
        tvPassword = findViewById(R.id.reg_ed_password)
        tvRePassword = findViewById(R.id.reg_ed_re_password)
        registerForm = findViewById(R.id.registration_form)

        findViewById<FloatingActionButton>(R.id.reg_fab_register).setOnClickListener {
            attemptRegister()
            it.animate().setDuration(1000L).alpha(0f).withEndAction {
                it.animate().alpha(1f)
            }
        }

        findViewById<FloatingActionButton>(R.id.reg_fab_close).setOnClickListener { finish() }
    }

    private fun attemptRegister() {
        val username = tvUsername.text.toString()
        val email = tvEmail.text.toString()
        val phone = tvPhone.text.toString()
        val city = tvCity.text.toString()
        val password = tvPassword.text.toString()
        val rePassword = tvRePassword.text.toString()

        if (username.isBlank()) {
            tvUsername.startAnimation(shake)
            return
        }
        if (email.isBlank() || !email.contains("@") || !email.contains(".")) {
            tvEmail.startAnimation(shake)
            return
        }
        if (phone.isBlank()) {
            tvPhone.startAnimation(shake)
            return
        }
        if (city.isBlank()) {
            tvCity.startAnimation(shake)
            return
        }
        if (password.isBlank() || password.length < 6) {
            tvPassword.startAnimation(shake)
            return
        }
        if (rePassword.isBlank() || password != rePassword) {
            tvRePassword.startAnimation(shake)
            return
        }

        CoroutineScope(EmptyCoroutineContext).launch {
            try {
                delay(timeMillis = 2000L)
                val status = dbHelper.register(username, password, email, phone, city)
                if (status == -1L) {
                    registerForm.startAnimation(shake)
                    return@launch
                }

                startActivity(Intent(applicationContext, LoginActivity::class.java).apply {
                    putExtra(LoginModel.Keys.KEY_IS_REGISTERED, true)
                    putExtra(LoginModel.Keys.KEY_USERNAME, username)
                    putExtra(LoginModel.Keys.KEY_PASSWORD, password)
                })
                finish()
            } catch (ignored: InterruptedException) {
            }
        }
    }
}
