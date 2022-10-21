package me.mitul.aij.splash;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewTreeObserver;

import androidx.viewpager.widget.ViewPager;

import me.mitul.aij.R;

public class MyViewPager extends ViewPager {
    private final String FIRST_RUN_KEY = "FIRST_RUN";

    public MyViewPager(final @NotNull Context context, final AttributeSet attrs) {
        super(context, attrs);
        final ViewTreeObserver observer = getViewTreeObserver();
        if (observer != null && observer.isAlive())
            observer.addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
                @Override
                public boolean onPreDraw() {
                    final ViewTreeObserver observer = getViewTreeObserver();
                    if (observer != null && observer.isAlive())
                        observer.removeOnPreDrawListener(this);
                    new Thread(() -> {
                        final SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
                        if (preferences != null)
                            if (preferences.getBoolean(FIRST_RUN_KEY, true)) {
                                preferences.edit().putBoolean(FIRST_RUN_KEY, false).apply();
                                post(() -> {
                                    final Activity activity = (Activity) context;
                                    final View helpView = activity.findViewById(R.id.tv_splash);
                                    if (helpView != null) helpView.setVisibility(View.VISIBLE);
                                });
                            }
                    }).start();
                    return true;
                }
            });
    }
}
