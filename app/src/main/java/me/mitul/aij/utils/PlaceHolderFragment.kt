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
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView: View
        rootView = inflater.inflate(R.layout.fragment_explore_holder, container, false)
        val ARG_ITEM_ID = arguments!!.getInt("ARG_ITEm_ID")
        if (ARG_ITEM_ID == 0) return rootView
        HelperAijExplorer(context).use { aij ->
            val tv = rootView.findViewById<TextView>(R.id.header_text)
            val jtv = rootView.findViewById<JustifiedTextView>(R.id.detail_text)
            tv.text = aij.selectHeader(ARG_ITEM_ID)
            jtv.setText(aij.selectDetail(ARG_ITEM_ID))
        }
        return rootView
    }

    companion object {
        fun newInstance(itemId: Int): PlaceHolderFragment {
            val fragment = PlaceHolderFragment()
            val args = Bundle()
            args.putInt("ARG_ITEm_ID", itemId)
            fragment.setArguments(args)
            return fragment
        }
    }
}
