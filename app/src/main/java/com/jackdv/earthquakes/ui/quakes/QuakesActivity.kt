package com.jackdv.earthquakes.ui.quakes

import android.os.Bundle
import com.jackdv.earthquakes.R
import com.jackdv.earthquakes.util.ActivityUtils.addFragmentToActivity
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class QuakesActivity : DaggerAppCompatActivity() {
    @JvmField
    @Inject
    var injectedFragment: QuakesFragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quakes)

        // Set up fragment
        var fragment = supportFragmentManager.findFragmentById(R.id.contentFrame) as QuakesFragment?
        if (fragment == null) {
            fragment = injectedFragment
            addFragmentToActivity(supportFragmentManager, fragment!!, R.id.contentFrame)
        }
    }
}