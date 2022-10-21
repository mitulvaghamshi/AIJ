package me.mitul.aij.utils;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import me.mitul.aij.helper.HelperAijExplorer;
import me.mitul.aij.R;

public class PlaceHolderFragment extends Fragment {
    public PlaceHolderFragment() {
    }

    public static PlaceHolderFragment newInstance(int itemId) {
        PlaceHolderFragment fragment = new PlaceHolderFragment();
        Bundle args = new Bundle();
        args.putInt("ARG_ITEm_ID", itemId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView;
        rootView = inflater.inflate(R.layout.fragment_explore_holder, container, false);
        int ARG_ITEM_ID = getArguments().getInt("ARG_ITEm_ID");
        if (ARG_ITEM_ID == 0) return rootView;
        TextView tv = rootView.findViewById(R.id.header_text);
        JustifiedTextView jtv = rootView.findViewById(R.id.detail_text);
        tv.setText(new HelperAijExplorer(getContext()).selectHeader(ARG_ITEM_ID));
        jtv.setText(new HelperAijExplorer(getContext()).selectDetail(ARG_ITEM_ID));
        return rootView;
    }
}
