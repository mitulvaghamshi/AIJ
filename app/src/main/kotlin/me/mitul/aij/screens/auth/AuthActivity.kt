package me.mitul.aij.screens.auth

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
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import me.mitul.aij.R
import me.mitul.aij.helpers.AuthHelper
import me.mitul.aij.screens.HomeActivity
import kotlin.coroutines.EmptyCoroutineContext

class AuthActivity : FragmentActivity() {
    private val coroutineScope = CoroutineScope(EmptyCoroutineContext)

    private lateinit var dbHelper: AuthHelper
    private lateinit var prefs: SharedPreferences
    private lateinit var shake: Animation

    private lateinit var fabChange: ExtendedFloatingActionButton
    private lateinit var fabSubmit: FloatingActionButton
    private lateinit var inputForm: LinearLayout

    private lateinit var loginFragment: LoginFragment
    private lateinit var registerFragment: RegisterFragment

    private var isLoginVisible = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)

        dbHelper = AuthHelper(applicationContext)
        prefs = getSharedPreferences(AuthHelper.KEY_LOGIN_INFO, MODE_PRIVATE)
        shake = AnimationUtils.loadAnimation(applicationContext, R.anim.anim_shake)

        inputForm = findViewById(R.id.auth_input_form)
        fabSubmit = findViewById(R.id.auth_fab_submit)

        loginFragment = LoginFragment(shake, prefs, fabSubmit) {
            task(it) { dbHelper.login(it) }
        }
        registerFragment = RegisterFragment(shake, fabSubmit) {
            task(it) { dbHelper.register(it) }
        }

        fabChange = findViewById(R.id.auth_fab_change)
        fabChange.setOnClickListener {
            if (isLoginVisible) {
                showFragment(registerFragment)
                fabChange.setText(R.string.already_registered_log_in)
            } else {
                showFragment(loginFragment)
                fabChange.setText(R.string.don_t_have_an_account_register_here)
            }
            isLoginVisible = !isLoginVisible
        }

        showFragment(loginFragment)
    }

    private val showFragment: (Fragment) -> Unit = {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.auth_frag_container, it)
            .commit()
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
        coroutineScope.launch {
            try {
                if (action() == -1L) {
                    inputForm.startAnimation(shake)
                    return@launch
                }
                savePrefs(values)
                startActivity(Intent(applicationContext, HomeActivity::class.java))
                finish()
            } catch (_: InterruptedException) {
            }
        }
    }
}
