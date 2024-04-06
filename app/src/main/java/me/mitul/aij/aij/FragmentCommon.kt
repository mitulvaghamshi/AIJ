package me.mitul.aij.aij

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import me.mitul.aij.R

class FragmentCommon : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?,
    ): View {
        val view = inflater.inflate(R.layout.aij_fragment_common, container, false)
        val id = requireArguments().getInt(KEY_FRAG_ID)
        if (id == 0) return view

        val dbHelper = HelperAijExplorer(requireContext())
        view.findViewById<TextView>(R.id.frag_tv_header).text = dbHelper.selectHeader(id)
        view.findViewById<JustifiedTextView>(R.id.frag_tv_description).text =
            dbHelper.selectDetail(id)

        return view
    }

    companion object {
        const val KEY_FRAG_ID = "fragment_id"

        @JvmStatic
        fun newInstance(id: Int) = FragmentCommon().also { frag ->
            frag.setArguments(Bundle().also { it.putInt(KEY_FRAG_ID, id) })
        }
    }
}
