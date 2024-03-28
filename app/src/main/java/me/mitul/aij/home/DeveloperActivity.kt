package me.mitul.aij.home

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import me.mitul.aij.R

class DeveloperActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_developer)

        findViewById<View>(R.id.dev_btn_aij).setOnClickListener {
            startActivity(
                Intent(this, DetailCollageActivity::class.java)
                    .putExtra("id_to_find", 58)
            )
        }
    }
}
