package me.mitul.aij.aij

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import me.mitul.aij.R
import me.mitul.aij.utils.FragmentCommon.Companion.newInstance

class AijExplorerActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_explore_aij)

        supportFragmentManager.beginTransaction().replace(
            R.id.fragment_container,
            newInstance(intent.getIntExtra("item_id", 0))
        ).commit()
    }
}
