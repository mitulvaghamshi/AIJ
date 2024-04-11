package me.mitul.aij.auth

import android.content.ContentValues
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.widget.AutoCompleteTextView
import androidx.fragment.app.Fragment
import com.google.android.material.floatingactionbutton.FloatingActionButton
import me.mitul.aij.R
import me.mitul.aij.utils.animate

class RegisterFragment(
    private val shake: Animation,
    private val submit: FloatingActionButton,
    private val onSubmit: (ContentValues) -> Unit,
) : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?,
    ): View {
        val view = inflater.inflate(R.layout.fragment_register, container, false)
        val holder = ViewHolder(view)
        submit.setOnClickListener {
            it.animate(duration = 800L)
            if (holder.validate(shake)) onSubmit(holder.values)
        }
        return view
    }

    private class ViewHolder(view: View) {
        private val tvUsername: AutoCompleteTextView = view.findViewById(R.id.reg_ed_username)
        private val tvPassword: AutoCompleteTextView = view.findViewById(R.id.reg_ed_password)
        private val tvEmail: AutoCompleteTextView = view.findViewById(R.id.reg_ed_email)
        private val tvPhone: AutoCompleteTextView = view.findViewById(R.id.reg_ed_phone)
        private val tvCity: AutoCompleteTextView = view.findViewById(R.id.reg_ed_city)
        private val tvRePassword: AutoCompleteTextView = view.findViewById(R.id.reg_ed_re_password)

        val values: ContentValues
            get() = ContentValues().apply {
                put(AuthHelper.COL_USERNAME, tvUsername.text.toString())
                put(AuthHelper.COL_EMAIL, tvEmail.text.toString())
                put(AuthHelper.COL_PHONE, tvPhone.text.toString())
                put(AuthHelper.COL_CITY, tvCity.text.toString())
                put(AuthHelper.COL_PASSWORD, tvPassword.text.toString())
            }

        fun validate(shake: Animation): Boolean {
            if (tvUsername.text.isBlank()) {
                tvUsername.startAnimation(shake)
                return false
            }
            if (
                tvEmail.text.isBlank() ||
                !tvEmail.text.contains("@") ||
                !tvEmail.text.contains(".")
            ) {
                tvEmail.startAnimation(shake)
                return false
            }
            if (tvPhone.text.isBlank()) {
                tvPhone.startAnimation(shake)
                return false
            }
            if (tvCity.text.isBlank()) {
                tvCity.startAnimation(shake)
                return false
            }
            if (tvPassword.text.isBlank() || tvPassword.text.length < 6) {
                tvPassword.startAnimation(shake)
                return false
            }
            if (tvRePassword.text.isBlank() || tvPassword.text != tvRePassword.text) {
                tvRePassword.startAnimation(shake)
                return false
            }
            return true
        }
    }
}
