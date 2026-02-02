package me.mitul.aij.screens.auth

import android.content.ContentValues
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import androidx.appcompat.widget.SwitchCompat
import androidx.fragment.app.Fragment
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.textfield.TextInputEditText
import me.mitul.aij.R
import me.mitul.aij.helpers.AuthHelper
import me.mitul.aij.utils.animate

class LoginFragment(
    private val shake: Animation,
    private val prefs: SharedPreferences,
    private val submit: FloatingActionButton,
    private val onSubmit: (ContentValues) -> Unit,
) : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_login, container, false)
        val holder = ViewHolder(view, shake).apply { autofillFrom(prefs) }
        submit.setOnClickListener {
            if (!holder.isValid()) return@setOnClickListener
            it.animate(500L) { onSubmit(holder.getPrefValues()) }
        }
        return view
    }

    private class ViewHolder(view: View, private val shake: Animation) {
        private val tvUsername: TextInputEditText = view.findViewById(R.id.login_ed_username)
        private val tvPassword: TextInputEditText = view.findViewById(R.id.login_ed_password)
        private val swKeepSigned: SwitchCompat = view.findViewById(R.id.login_sw_keep_signed)

        fun autofillFrom(prefs: SharedPreferences) {
            if (prefs.getBoolean(AuthHelper.KEY_KEEP_SIGNED, false)) {
                tvUsername.setText(prefs.getString(AuthHelper.COL_USERNAME, ""))
                tvPassword.setText(prefs.getString(AuthHelper.COL_PASSWORD, ""))
                swKeepSigned.isChecked = prefs.getBoolean(AuthHelper.KEY_KEEP_SIGNED, false)
            }
        }

        fun getPrefValues() = ContentValues().apply {
            put(AuthHelper.COL_USERNAME, tvUsername.text.toString())
            put(AuthHelper.COL_PASSWORD, tvPassword.text.toString())
            put(AuthHelper.KEY_KEEP_SIGNED, swKeepSigned.isChecked)
        }

        fun isValid(): Boolean {
            if (tvUsername.text?.isBlank() ?: false) {
                tvUsername.startAnimation(shake)
                return false
            }
            if ((tvPassword.text?.isBlank() ?: false) || tvPassword.text!!.length < 6) {
                tvPassword.startAnimation(shake)
                return false
            }
            return true
        }
    }
}
