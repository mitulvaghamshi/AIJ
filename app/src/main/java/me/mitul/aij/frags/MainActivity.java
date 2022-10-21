package me.mitul.aij.frags;

import android.os.Bundle;
import android.view.Display;
import android.view.WindowManager;
import android.widget.LinearLayout;

import androidx.fragment.app.FragmentActivity;

import me.mitul.aij.R;

public class MainActivity extends FragmentActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LinearLayout layout = findViewById(R.id.activity_mainLinearLayout);
        WindowManager manager = getWindowManager();
        Display display = manager.getDefaultDisplay();
        if (display.getWidth() < display.getHeight()) {
            layout.setOrientation(LinearLayout.HORIZONTAL);
        } else {
            layout.setOrientation(LinearLayout.VERTICAL);
        }
    }
}
