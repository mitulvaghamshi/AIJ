package me.mitul.aij.splash

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.fragment.app.Fragment
import me.mitul.aij.R

class PagerFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?,
    ): View {
        val view = inflater.inflate(R.layout.pager_container, container, false)
        val id = requireArguments().getInt(KEY_PAGE_ID)
        return if (id == 0) view
        else view.findViewById<FrameLayout>(R.id.pager_container).apply {
            setBackgroundColor(resources.getColor(COLORS[id - 1], context?.theme))
        }
    }

    object Instance {
        @JvmStatic
        fun new(index: Int) = PagerFragment().apply {
            arguments = Bundle().apply { putInt(KEY_PAGE_ID, index) }
        }
    }

    private companion object {
        const val KEY_PAGE_ID = "page_id"

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
