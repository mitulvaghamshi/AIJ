package me.mitul.aij.utils

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import me.mitul.aij.R
import me.mitul.aij.helper.HelperAijExplorer

class PlaceHolderFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?,
    ): View {
        val view = inflater.inflate(R.layout.fragment_explore_holder, container, false)
        val id = requireArguments().getInt("ARG_ITEm_ID")
        if (id == 0) return view

        HelperAijExplorer(context).use {
            view.findViewById<TextView>(R.id.header_text).text = it.selectHeader(id)
            view.findViewById<JustifiedTextView>(R.id.detail_text).text = it.selectDetail(id)
        }
        return view
    }

    companion object {
        @JvmStatic
        fun newInstance(itemId: Int) = PlaceHolderFragment().also { frag ->
            val args = Bundle().also { it.putInt("ARG_ITEm_ID", itemId) }
            frag.setArguments(args)
        }
    }
}
