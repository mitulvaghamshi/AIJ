package me.mitul.aij.aij

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.appbar.CollapsingToolbarLayout
import me.mitul.aij.R

class ContactUs : AppCompatActivity() {
    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.contact_us)
        setSupportActionBar(findViewById(R.id.toolbar12))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        findViewById<CollapsingToolbarLayout>(R.id.collapsing_toolbar12).title = "Contact Us"
    }
}
