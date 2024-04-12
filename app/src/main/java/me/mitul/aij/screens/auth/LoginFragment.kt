package me.mitul.aij.screens.auth

import android.content.ContentValues
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.widget.AutoCompleteTextView
import androidx.appcompat.widget.SwitchCompat
import androidx.fragment.app.Fragment
import com.google.android.material.floatingactionbutton.FloatingActionButton
import me.mitul.aij.helpers.AuthHelper
import me.mitul.aij.R
import me.mitul.aij.utils.animate

class LoginFragment(
    private val shake: Animation,
    private val prefs: SharedPreferences,
    private val submit: FloatingActionButton,
    private val onSubmit: (ContentValues) -> Unit,
) : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?,
    ): View {
        val view = inflater.inflate(R.layout.fragment_login, container, false)
        val holder = ViewHolder(view).apply { autoFill(prefs) }
        submit.setOnClickListener {
            it.animate(duration = 800L)
            if (holder.validate(shake)) onSubmit(holder.values)
        }
        return view
    }

    private class ViewHolder(view: View) {
        private val tvUsername: AutoCompleteTextView = view.findViewById(R.id.login_ed_username)
        private val tvPassword: AutoCompleteTextView = view.findViewById(R.id.login_ed_password)
        private val swKeepSigned: SwitchCompat = view.findViewById(R.id.login_sw_keep_signed)

        val values: ContentValues
            get() = ContentValues().apply {
                put(AuthHelper.COL_USERNAME, tvUsername.text.toString())
                put(AuthHelper.COL_PASSWORD, tvPassword.text.toString())
                put(AuthHelper.KEY_KEEP_SIGNED, swKeepSigned.isChecked)
            }

        fun validate(shake: Animation): Boolean {
            if (tvUsername.text.isBlank()) {
                tvUsername.startAnimation(shake)
                return false
            } else if (tvPassword.text.isBlank() || tvPassword.text.length < 6) {
                tvPassword.startAnimation(shake)
                return false
            }
            return true
        }

        fun autoFill(prefs: SharedPreferences) {
            if (prefs.getBoolean(AuthHelper.KEY_KEEP_SIGNED, false)) {
                tvUsername.setText(prefs.getString(AuthHelper.COL_USERNAME, ""))
                tvPassword.setText(prefs.getString(AuthHelper.COL_PASSWORD, ""))
                swKeepSigned.isChecked = prefs.getBoolean(AuthHelper.KEY_KEEP_SIGNED, false)
            }
        }
    }
}
