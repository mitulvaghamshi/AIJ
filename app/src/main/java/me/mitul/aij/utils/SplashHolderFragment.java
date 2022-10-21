package me.mitul.aij.utils;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import me.mitul.aij.model.SplashScreen;
import me.mitul.aij.helper.HelperSplashScreen;
import me.mitul.aij.R;

public class SplashHolderFragment extends Fragment {

    public static SplashHolderFragment newInstance(int itemId) {
        SplashHolderFragment fragment = new SplashHolderFragment();
        Bundle args = new Bundle();
        args.putInt("ARG_String_ID", itemId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView;
        rootView = inflater.inflate(R.layout.fragment_splash, container, false);
        int ARG_ITEM_ID = getArguments().getInt("ARG_String_ID");
        if (ARG_ITEM_ID == 0) return rootView;
        TextView tv = rootView.findViewById(R.id.fragment_tv_splash_text);
        SplashScreen splashScreen = new HelperSplashScreen(getContext()).selectSplashTextData(ARG_ITEM_ID);
        tv.setText(splashScreen.getText());
        //tv.setTextColor(Color.parseColor(splashScreen.getSplashTextColor())/*getResources().getColor(getResources().getIdentifier(splashScreen.getSplashTextColor(), "color", getContext().getPackageName()))*/);
        //tv.setTextSize(TypedValue.COMPLEX_UNIT_PX, 50/*Float.valueOf(splashScreen.getSplashTextSize())*//*getResources().getDimension(getResources().getIdentifier(splashScreen.getSplashTextSize(), "dimen", getContext().getPackageName()))*/);
        // tv.setTypeface(Typeface.createFromAsset(getActivity().getAssets(), "font/Precious.ttf"));
        return rootView;
    }
}
