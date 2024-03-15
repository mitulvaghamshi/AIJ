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
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        val rootView: View = inflater.inflate(R.layout.fragment_explore_holder, container, false)
        val argItemId = requireArguments().getInt("ARG_ITEm_ID")
        if (argItemId == 0) return rootView
        HelperAijExplorer(context).use { aij ->
            rootView.findViewById<TextView>(R.id.header_text).text = aij.selectHeader(argItemId)
            rootView.findViewById<JustifiedTextView>(R.id.detail_text).text =
                aij.selectDetail(argItemId)
        }
        return rootView
    }

    companion object {
        @JvmStatic
        fun newInstance(itemId: Int): PlaceHolderFragment {
            val fragment = PlaceHolderFragment()
            val args = Bundle().also { it.putInt("ARG_ITEm_ID", itemId) }
            fragment.setArguments(args)
            return fragment
        }
    }
}
