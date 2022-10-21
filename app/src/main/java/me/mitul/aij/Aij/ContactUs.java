package me.mitul.aij.aij;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.appbar.CollapsingToolbarLayout;

import java.util.Objects;

import me.mitul.aij.R;

public class ContactUs extends AppCompatActivity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contact_us);
        setSupportActionBar(findViewById(R.id.toolbar12));
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        ((CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar12)).setTitle(getResources().getString(R.string.contact_us_txt));
    }
}
