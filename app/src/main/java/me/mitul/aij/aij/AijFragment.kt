package me.mitul.aij.aij

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import me.mitul.aij.R

class AijFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?,
    ): View {
        val view = inflater.inflate(R.layout.aij_fragment, container, false)
        val id = requireArguments().getInt(Instance.KEY_FRAG_ID)
        if (id == 0) return view
//        view.findViewById<TextView>(R.id.aij_frag_tv_title).text = "Title"
//        view.findViewById<TextView>(R.id.aij_frag_tv_description).text = "Description"
        return view
    }

    object Instance {
        const val KEY_FRAG_ID = "fragment_id"

        @JvmStatic
        fun new(id: Int) = AijFragment().apply {
            setArguments(Bundle().apply { putInt(KEY_FRAG_ID, id) })
        }
    }
}
