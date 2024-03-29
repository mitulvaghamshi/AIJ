package me.mitul.aij.splash

import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import me.mitul.aij.R
import me.mitul.aij.model.Splash
import me.mitul.aij.utils.Consts

class SplashFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?,
    ): View {
        val view = inflater.inflate(R.layout.fragment_splash, container, false)
        val args = requireArguments()
        val id = args.getInt(KEY_ID)
        if (id == 0) return view

        view.findViewById<FrameLayout>(R.id.splash_container)
            .setBackgroundColor(Color.parseColor(splashColors[id]))
        view.findViewById<TextView>(R.id.splash_tv_content).let {
            it.text = args.getString(KEY_TEXT)
            it.setTextColor(Color.WHITE)
            it.setTextSize(TypedValue.COMPLEX_UNIT_SP, 32f)
            it.setTypeface(Typeface.createFromAsset(requireActivity().assets, Consts.FONT_PRECIOUS))
        }
        return view
    }

    companion object {
        private const val KEY_ID = "splash_id"
        private const val KEY_TEXT = "splash_text"
        private val splashColors = listOf(
            "#B40501", "#FF4781", "#2196F3", "#4D2F1D", "#4CAF50", "#FFEB3B", "#6067F4", "#6067F4"
        )

        @JvmStatic
        fun getInstance(splash: Splash = Splash(id = 0, text = "")): SplashFragment {
            val args = Bundle()
            args.putInt(KEY_ID, splash.id)
            args.putString(KEY_TEXT, splash.text)
            return SplashFragment().also { it.arguments = args }
        }
    }
}
