package me.mitul.aij.screens.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.fragment.app.Fragment
import me.mitul.aij.R

class MainFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?,
    ): View {
        val view = inflater.inflate(R.layout.fragment_main, container, false)

        val id = requireArguments().getInt(KEY_PAGE_ID)
        if (id == 0) return view

        val fragContainer = view.findViewById<FrameLayout>(R.id.main_frag_container)
        fragContainer.setBackgroundColor(resources.getColor(colors[id], context?.theme))

        return view
    }

    object Instance {
        @JvmStatic
        fun new(index: Int) = MainFragment().apply {
            arguments = Bundle().apply { putInt(KEY_PAGE_ID, index) }
        }
    }

    private companion object {
        const val KEY_PAGE_ID = "page_id"
        val colors = listOf(
            android.R.color.black,
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
