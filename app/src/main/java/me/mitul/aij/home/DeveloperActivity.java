package me.mitul.aij.home;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;

import me.mitul.aij.R;
import me.mitul.aij.reg.LoginActivity;

public class DeveloperActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_developer);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        findViewById(R.id.dev_goto_detail_aij).setOnClickListener(v -> {
            Intent intent = new Intent(DeveloperActivity.this, DetailCollageActivity.class);
            startActivity(intent.putExtra(getString(R.string.aij_id_to_find), getString(R.string.aij_id_58)));
        });
        findViewById(R.id.dev_goto_aij_login).setOnClickListener(v -> {
            startActivity(new Intent(DeveloperActivity.this, LoginActivity.class));
        });
    }
}
