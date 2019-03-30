package com.gobinda.mvp.sample.ui.flightevent

import com.gobinda.mvp.sample.di.ActivityScope
import com.gobinda.mvp.sample.di.FragmentScope
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector


/**
 * Flight Event Module which to inject dependencies  fragment and
 * Exposes presenter
 */
@Module
abstract class FlightEventModule {

    @FragmentScope
    @ContributesAndroidInjector
    internal abstract fun flyEventsFragment(): FlightEventListFragment

    @ActivityScope
    @Binds
    internal abstract fun flyEventsPresenter(presenter: FlightEventsPresenter): FlightEventsContract.Presenter


    @FragmentScope
    @ContributesAndroidInjector
    internal abstract fun flyEventDetailFragment(): FlightDetailFragment

    @ActivityScope
    @Binds
    internal abstract fun flyEventDetailPresenter(presenter: FlightEventDetailPresenter): FlightEventDetailContract.Presenter
}