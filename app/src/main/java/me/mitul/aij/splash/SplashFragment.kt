package me.mitul.aij.splash

import android.graphics.Typeface
import android.os.Bundle
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import me.mitul.aij.R
import me.mitul.aij.model.Splash
import me.mitul.aij.utils.Consts

class SplashHolderFragment(private val splash: Splash) : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?,
    ): View {
        val view = inflater.inflate(R.layout.fragment_splash, container, false)
        if (splash.id == 0) return view

        view.findViewById<TextView>(R.id.splash_tv_content).let {
            it.text = splash.text
            it.setTextColor(Consts.splashColors[splash.id - 1])
            it.setTextSize(TypedValue.COMPLEX_UNIT_SP, 32f)
            it.setTypeface(Typeface.createFromAsset(requireActivity().assets, Consts.FONT_PATH))
        }
        return view
    }
}
