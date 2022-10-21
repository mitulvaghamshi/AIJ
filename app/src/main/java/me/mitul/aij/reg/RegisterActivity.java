package me.mitul.aij.reg;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.Settings;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AutoCompleteTextView;

import androidx.appcompat.app.AppCompatActivity;

import me.mitul.aij.helper.HelperRegistration;
import me.mitul.aij.R;

public class RegisterActivity extends AppCompatActivity {
    private AutoCompleteTextView editTextFullName;
    private AutoCompleteTextView editTextEmail;
    private AutoCompleteTextView editTextMobile;
    private AutoCompleteTextView editTextCity;
    private AutoCompleteTextView editTextPassword;
    private AutoCompleteTextView editTextPasswordRe;
    private View progressView;
    private View regFormView;
    private Animation shake;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        editTextFullName = findViewById(R.id.full_name_reg);
        editTextEmail = findViewById(R.id.email_reg);
        editTextMobile = findViewById(R.id.mobile_reg);
        editTextCity = findViewById(R.id.city_reg);
        editTextPassword = findViewById(R.id.password_reg);
        editTextPasswordRe = findViewById(R.id.password_reg_re);
        progressView = findViewById(R.id.fab_register_go);
        regFormView = findViewById(R.id.reg_form);

        findViewById(R.id.fab_register_go).setOnClickListener(view -> attemptReg());
        findViewById(R.id.fab_skip_go).setOnClickListener(v -> {
            new AlertDialog.Builder(RegisterActivity.this)
                    .setMessage("Registration required!")
                    .setCancelable(false)
                    .setPositiveButton("OK", null)
                    .create().show();
        });
        shake = AnimationUtils.loadAnimation(RegisterActivity.this, R.anim.shake);
    }

    private void attemptReg() {
        boolean isValid = true;
        String strTextFullName = editTextFullName.getText().toString();
        String strTextEmail = editTextEmail.getText().toString();
        String strTextMobile = editTextMobile.getText().toString();
        String strTextCity = editTextCity.getText().toString();
        String strTextPassword = editTextPassword.getText().toString();
        String strTextPasswordRe = editTextPasswordRe.getText().toString();
        String deviceId = Settings.System.getString(RegisterActivity.this.getContentResolver(), Settings.Secure.ANDROID_ID);

        if (TextUtils.isEmpty(strTextFullName)) {
            editTextFullName.startAnimation(shake);
            isValid = false;
        }
        if (TextUtils.isEmpty(strTextEmail) || !strTextEmail.contains("@") || !strTextEmail.contains(".")) {
            editTextEmail.startAnimation(shake);
            isValid = false;
        }
        if (TextUtils.isEmpty(strTextMobile)) {
            editTextMobile.startAnimation(shake);
            isValid = false;
        }
        if (TextUtils.isEmpty(strTextCity)) {
            editTextCity.startAnimation(shake);
            isValid = false;
        }
        if (TextUtils.isEmpty(strTextPassword) ||
                strTextPassword.length() < 6) {
            editTextPassword.startAnimation(shake);
            isValid = false;
        }
        if (TextUtils.isEmpty(strTextPasswordRe) ||
                !strTextPassword.equals(strTextPasswordRe)) {
            editTextPasswordRe.startAnimation(shake);
            isValid = false;
        }
        if (isValid) {
            new UserRegTask().execute(strTextFullName, strTextEmail, strTextMobile, strTextCity, strTextPassword, strTextPasswordRe, deviceId);
        }
    }

    private class UserRegTask extends AsyncTask<String, Void, Boolean> {
        String email;
        String pass;

        @Override
        protected Boolean doInBackground(String... strings) {
            try {
                Thread.sleep(2000);
                return new HelperRegistration(RegisterActivity.this)
                        .attemptReg(email = strings[0], strings[1], strings[2], strings[3], pass = strings[4], strings[5], strings[6]);
            } catch (InterruptedException ignored) {
            }
            return false;
        }

        @Override
        protected void onPreExecute() {
            progressView.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.anim_fab));
        }

        @Override
        protected void onPostExecute(Boolean success) {
            if (success) {
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                intent.putExtra("IsRegistered", true)
                        .putExtra("UserName", email)
                        .putExtra("Password", pass);
                startActivity(intent);
                finish();
            } else {
                regFormView.startAnimation(shake);
            }
        }
    }
}
