package me.mitul.aij.aij

import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import me.mitul.aij.R
import me.mitul.aij.utils.Keys

class AijContainer : FragmentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.pager_container)

        val id = intent.getIntExtra(Keys.KEY_FILTER_ID, 0)

        supportFragmentManager.beginTransaction().replace(
            R.id.pager_container, AijFragment.Instance.new(id)
        ).commit()
    }
}
