package me.mitul.aij.utils

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import me.mitul.aij.R
import me.mitul.aij.helper.HelperAijExplorer

class FragmentCommon : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?,
    ): View {
        val view = inflater.inflate(R.layout.fragment_common, container, false)
        val id = requireArguments().getInt(Consts.KEY_FRAG_ID_ARG)
        if (id == 0) return view

        HelperAijExplorer(context).use {
            view.findViewById<TextView>(R.id.frag_tv_header).text = it.selectHeader(id)
            view.findViewById<JustifiedTextView>(R.id.frag_tv_description).text = it.selectDetail(id)
        }
        return view
    }

    companion object {
        @JvmStatic
        fun newInstance(id: Int) = FragmentCommon().also { frag ->
            val args = Bundle().also { it.putInt(Consts.KEY_FRAG_ID_ARG, id) }
            frag.setArguments(args)
        }
    }
}
