package me.mitul.aij.splash;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.fragment.app.FragmentActivity;
import androidx.viewpager.widget.ViewPager;

import java.lang.reflect.Field;
import java.util.Objects;

import me.mitul.aij.adapter.AdapterViewPager;
import me.mitul.aij.utils.FixedSpeedScroller;
import me.mitul.aij.utils.SplashHolderFragment;
import me.mitul.aij.helper.HelperLogin;
import me.mitul.aij.R;
import me.mitul.aij.reg.LoginActivity;

public class SplashScreenActivity extends FragmentActivity {
    @Override
    public void onBackPressed() {
        long milli;
        if ((milli = System.currentTimeMillis()) < milli + 2000L) super.onBackPressed();
        else Toast.makeText(this, "Press again to exit!", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.spalash);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        new HelperLogin(this);
        final MyViewPager viewPager = findViewById(R.id.pager);
        if (viewPager != null) setupViewPager(viewPager);
        try {
            Field field = ViewPager.class.getDeclaredField("mScroller");
            field.setAccessible(true);
            assert viewPager != null;
            FixedSpeedScroller scroller = new FixedSpeedScroller(viewPager.getContext());
            try {
                field.set(viewPager, scroller);
            } catch (IllegalAccessException | IllegalArgumentException ignored) {
            }
        } catch (NoSuchFieldException ignored) {
        }
        new Thread(new Runnable() {
            int value = 0;
            final Handler handler = new Handler(Looper.getMainLooper());

            public void run() {
                while (true) {
                    try {
                        Thread.sleep(2000L);
                        handler.post(() -> Objects.requireNonNull(viewPager)
                                .setCurrentItem(value <= 7 ? value++ : (value = 0), true));
                    } catch (InterruptedException ignored) {
                    }
                }
            }
        }).start();

        findViewById(R.id.splash_fab_login_go).setOnClickListener(v -> {
            startActivity(new Intent(getApplicationContext(), LoginActivity.class));
            finish();
        });
    }

    private void setupViewPager(MyViewPager viewPager) {
        AdapterViewPager adapter = new AdapterViewPager(getSupportFragmentManager());
        int i = 0;
        while (i++ < 7) {
            adapter.addFragment(SplashHolderFragment.newInstance(i), "fragment");
        }
        viewPager.setAdapter(adapter);
    }
}
