package com.gobinda.mvp.sample.ui.flightevent

import android.os.Bundle
import com.gobinda.mvp.sample.R
import dagger.android.support.DaggerAppCompatActivity

import kotlinx.android.synthetic.main.act_flight_event.*
import javax.inject.Inject


/**
 * Flight Event main Launcher class  it invokes Flight Event List Fragment
 */
class FlightEventMainActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit  var fragment: FlightEventListFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.act_flight_event)
        setSupportActionBar(toolbar)

        val fragment = supportFragmentManager
            .findFragmentById(R.id.container)
            ?: fragment

        supportFragmentManager.beginTransaction().run {
            replace(R.id.container, fragment)
            commit()
        }

    }


}
