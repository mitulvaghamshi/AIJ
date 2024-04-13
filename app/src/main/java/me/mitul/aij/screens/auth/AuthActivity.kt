package me.mitul.aij.screens.auth

import android.app.Activity
import android.content.ContentValues
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.LinearLayout
import androidx.core.content.edit
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import me.mitul.aij.R
import me.mitul.aij.helpers.AuthHelper
import me.mitul.aij.screens.HomeActivity
import me.mitul.aij.utils.Database
import kotlin.coroutines.EmptyCoroutineContext

class AuthActivity : FragmentActivity() {
    private lateinit var dbHelper: AuthHelper
    private lateinit var prefs: SharedPreferences
    private lateinit var shake: Animation

    private lateinit var inputForm: LinearLayout
    private lateinit var fabSubmit: FloatingActionButton

    private lateinit var loginFragment: LoginFragment
    private lateinit var registerFragment: RegisterFragment

    private var isLoginVisible = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)

        dbHelper = AuthHelper(Database(applicationContext))
        prefs = getSharedPreferences(AuthHelper.KEY_LOGIN_INFO, Activity.MODE_PRIVATE)
        shake = AnimationUtils.loadAnimation(applicationContext, R.anim.anim_shake)

        inputForm = findViewById(R.id.auth_input_form)
        fabSubmit = findViewById(R.id.auth_fab_submit)

        loginFragment = LoginFragment(shake, prefs, fabSubmit) {
            task(it) { dbHelper.login(it) }
        }
        registerFragment = RegisterFragment(shake, fabSubmit) {
            task(it) { dbHelper.register(it) }
        }

        findViewById<FloatingActionButton>(R.id.auth_fab_change).setOnClickListener {
            showFragment(if (isLoginVisible) registerFragment else loginFragment)
            isLoginVisible = !isLoginVisible
        }

        showFragment(loginFragment)
    }

    private val showFragment: (Fragment) -> Unit = {
        supportFragmentManager.beginTransaction()
            .replace(R.id.auth_frag_container, it).commit()
    }

    private val savePrefs: (ContentValues) -> Unit = {
        val username = it.getAsString(AuthHelper.COL_USERNAME)
        val password = it.getAsString(AuthHelper.COL_PASSWORD)
        val keepSigned = it.getAsBoolean(AuthHelper.KEY_KEEP_SIGNED) ?: true

        prefs.edit {
            putBoolean(AuthHelper.KEY_KEEP_SIGNED, keepSigned)
            if (keepSigned) {
                putString(AuthHelper.COL_USERNAME, username)
                putString(AuthHelper.COL_PASSWORD, password)
            } else clear()
        }
    }

    private fun task(values: ContentValues, action: () -> Long) {
        CoroutineScope(EmptyCoroutineContext).launch {
            try {
                if (action() == -1L) {
                    inputForm.startAnimation(shake)
                    return@launch
                }
                savePrefs(values)
                delay(timeMillis = 2000L) // TODO("Network call simulation")
                startActivity(Intent(applicationContext, HomeActivity::class.java))
                finish()
            } catch (ignored: InterruptedException) {
            }
        }
    }
}
