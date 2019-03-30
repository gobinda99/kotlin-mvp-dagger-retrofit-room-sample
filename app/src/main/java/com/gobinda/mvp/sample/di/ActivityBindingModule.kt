package com.gobinda.mvp.sample.di

import com.gobinda.mvp.sample.ui.flightevent.FlightEventMainActivity
import com.gobinda.mvp.sample.ui.flightevent.FlightEventModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Activity Binding module, it uses @ContributesAndroidInjector.
 * It will be used to create subComponent Scope Instance and  the life
 * of the scope instance which like as long as the activity & Fragment.
 */
@Module
abstract class ActivityBindingModule {

    @ActivityScope
    @ContributesAndroidInjector(modules = arrayOf(FlightEventModule::class))
    internal abstract fun flightEventActivity(): FlightEventMainActivity

}