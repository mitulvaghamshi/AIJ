package me.mitul.aij.splash

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

class PagerFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?,
    ): View {
        val view = inflater.inflate(R.layout.fragment_splash, container, false)

        val args = requireArguments()
        val id = args.getInt(KEY_ID)
        if (id == 0) return view

        view.findViewById<FrameLayout>(R.id.splash_container)
            .setBackgroundColor(resources.getColor(COLORS[id - 1], context?.theme))

        view.findViewById<TextView>(R.id.splash_tv_content).apply {
            text = args.getString(KEY_TEXT)
            setTextSize(TypedValue.COMPLEX_UNIT_SP, 32f)
            setTypeface(Typeface.createFromAsset(requireActivity().assets, FONT_PRECIOUS))
        }
        return view
    }

    object Instance {
        @JvmStatic
        fun new(index: Int) = PagerFragment().apply {
            arguments = Bundle().apply {
                putInt(KEY_ID, index)
                putString(KEY_TEXT, "Hello World: $index")
            }
        }
    }

    private companion object {
        const val KEY_ID = "splash_id"
        const val KEY_TEXT = "splash_text"
        const val FONT_PRECIOUS = "font/Precious.ttf"

        val COLORS = listOf(
            R.color.red,
            R.color.pink,
            R.color.orange,
            R.color.yellow,
            R.color.green,
            R.color.blue,
            R.color.purple,
        )
    }
}
