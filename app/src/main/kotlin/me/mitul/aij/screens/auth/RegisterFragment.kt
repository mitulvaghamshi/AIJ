package me.mitul.aij.screens.auth

import android.content.ContentValues
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import androidx.fragment.app.Fragment
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.textfield.TextInputEditText
import me.mitul.aij.R
import me.mitul.aij.helpers.AuthHelper
import me.mitul.aij.utils.animate

class RegisterFragment(
    private val shake: Animation,
    private val submit: FloatingActionButton,
    private val onSubmit: (ContentValues) -> Unit,
) : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        val view = inflater.inflate(R.layout.fragment_register, container, false)
        val holder = ViewHolder(view, shake)
        submit.setOnClickListener {
            if (!holder.isValid()) return@setOnClickListener
            it.animate(500L) { onSubmit(holder.getPrefValues()) }
        }
        return view
    }

    private class ViewHolder(view: View, private val shake: Animation) {
        private val tvUsername: TextInputEditText = view.findViewById(R.id.reg_ed_username)
        private val tvPassword: TextInputEditText = view.findViewById(R.id.reg_ed_password)
        private val tvEmail: TextInputEditText = view.findViewById(R.id.reg_ed_email)
        private val tvPhone: TextInputEditText = view.findViewById(R.id.reg_ed_phone)
        private val tvCity: TextInputEditText = view.findViewById(R.id.reg_ed_city)
        private val tvRePassword: TextInputEditText = view.findViewById(R.id.reg_ed_re_password)

        fun getPrefValues() = ContentValues().apply {
            put(AuthHelper.COL_USERNAME, tvUsername.text.toString())
            put(AuthHelper.COL_EMAIL, tvEmail.text.toString())
            put(AuthHelper.COL_PHONE, tvPhone.text.toString())
            put(AuthHelper.COL_CITY, tvCity.text.toString())
            put(AuthHelper.COL_PASSWORD, tvPassword.text.toString())
        }

        fun isValid(): Boolean {
            if (tvUsername.text?.isBlank() ?: false) {
                tvUsername.startAnimation(shake)
                return false
            }
            if (
                (tvEmail.text?.isBlank() ?: false) ||
                !tvEmail.text!!.contains("@") ||
                !tvEmail.text!!.contains(".")
            ) {
                tvEmail.startAnimation(shake)
                return false
            }
            if (tvPhone.text?.isBlank() ?: false) {
                tvPhone.startAnimation(shake)
                return false
            }
            if (tvCity.text?.isBlank() ?: false) {
                tvCity.startAnimation(shake)
                return false
            }
            if ((tvPassword.text?.isBlank() ?: false) || tvPassword.text!!.length < 6) {
                tvPassword.startAnimation(shake)
                return false
            }
            if ((tvRePassword.text?.isBlank() ?: false) || tvPassword.text != tvRePassword.text) {
                tvRePassword.startAnimation(shake)
                return false
            }
            return true
        }
    }
}
