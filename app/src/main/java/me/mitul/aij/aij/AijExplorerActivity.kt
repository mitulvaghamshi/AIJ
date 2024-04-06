package me.mitul.aij.aij

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import me.mitul.aij.R
import me.mitul.aij.aij.FragmentCommon.Companion.newInstance

class AijExplorerActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.aij_activity_explore)

        supportFragmentManager.beginTransaction().replace(
            R.id.fragment_container,
            newInstance(intent.getIntExtra("item_id", 0))
        ).commit()
    }
}
