package me.mitul.aij.reg;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AutoCompleteTextView;
import android.widget.CheckBox;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import me.mitul.aij.helper.HelperBackupRestore;
import me.mitul.aij.helper.HelperLogin;
import me.mitul.aij.R;
import me.mitul.aij.home.HomeScreenActivity;

public class LoginActivity extends AppCompatActivity {
    private AutoCompleteTextView emailView;
    private AutoCompleteTextView passwordView;
    private CheckBox cbxKeepMeLoggedIn;
    private View progressView;
    private View loginFormView;
    private Animation shake;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        emailView = findViewById(R.id.login_email);
        passwordView = findViewById(R.id.login_password);
        cbxKeepMeLoggedIn = findViewById(R.id.checkBoxKeepMeLoggedIn);
        loginFormView = findViewById(R.id.login_form);
        progressView = findViewById(R.id.fab_login_go);

        SharedPreferences loginPreference = getSharedPreferences("Login1", 0);
        Intent intent = getIntent();

        if (intent.getBooleanExtra("IsRegistered", false)) {
            emailView.setText(intent.getStringExtra("UserName"));
            passwordView.setText(intent.getStringExtra("Password"));
        }

        SharedPreferences firstRun = getPreferences(Context.MODE_PRIVATE);
        if (firstRun.getBoolean("firstTime", true)) {
            if (HelperBackupRestore.sdDatabaseExists()) {
                new AlertDialog.Builder(this).setIcon(R.drawable.ic_launcher)
                        .setTitle("Backup found!")
                        .setMessage("Do you want to restore previous data ?(Restart Required!)")
                        .setCancelable(false).setNegativeButton("No", null)
                        .setPositiveButton("Yes", (p1, p2) -> {
                            HelperBackupRestore.restoreDb();
                            finish();
                            startActivity(getIntent());
                        }).create().show();
            }
            firstRun.edit().putBoolean("firstTime", false).apply();
        }

        if (loginPreference.getBoolean("keepMeLoggdIn", false)) {
            emailView.setText(loginPreference.getString("Email1", ""));
            passwordView.setText(loginPreference.getString("Pass1", ""));
            cbxKeepMeLoggedIn.setChecked(loginPreference.getBoolean("keepMeLoggdIn", false));
            // attemptLogin();
        }
        findViewById(R.id.fab_login_go).setOnClickListener(view -> attemptLogin());

        findViewById(R.id.fab_reg_new).setOnClickListener(v -> {
            startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            finish();
        });
        shake = AnimationUtils.loadAnimation(LoginActivity.this, R.anim.shake);
    }

    @Override
    protected void onStop() {
        super.onStop();
        SharedPreferences.Editor editor = getSharedPreferences("Login1", 0)
                .edit()
                .putBoolean("keepMeLoggdIn", cbxKeepMeLoggedIn.isChecked());
        if (cbxKeepMeLoggedIn.isChecked()) {
            editor.putString("Email1", emailView.getText().toString())
                    .putString("Pass1", passwordView.getText().toString());
        } else {
            editor.putString("Email1", "mady@me")
                    .putString("Pass1", "123456");
        }
        editor.apply();
    }

    private void attemptLogin() {
        boolean isValid = true;
        String email = emailView.getText().toString();
        String pass = passwordView.getText().toString();

        if (TextUtils.isEmpty(email)) {
            emailView.startAnimation(shake);
            isValid = false;
        }
        if (TextUtils.isEmpty(pass)) {
            passwordView.startAnimation(shake);
            isValid = false;
        }
        if (isValid) {
            new UserLoginTask().execute(email, pass);
        }
    }

    private class UserLoginTask extends AsyncTask<String, Void, Integer> {
        @Override
        protected Integer doInBackground(String... strings) {
            try {
                Thread.sleep(2000);
                return new HelperLogin(LoginActivity.this).attemptLogin(strings[0], strings[1]);
            } catch (InterruptedException ignored) {
            }
            return 999;
        }

        @Override
        protected void onPreExecute() {
            progressView.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.anim_fab));
        }

        @Override
        protected void onPostExecute(Integer localID) {
            if (localID != 999) {
                Intent intent = new Intent(getApplicationContext(), HomeScreenActivity.class);
                startActivity(intent.putExtra("UserID", localID.toString()));
                finish();
            } else {
                loginFormView.startAnimation(shake);
            }
        }
    }
}
