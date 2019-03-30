package com.gobinda.mvp.sample

import com.gobinda.mvp.sample.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication
import timber.log.Timber

/**
 * Application Class
 *
 */
class App : DaggerApplication() {

    private val appComponent: AndroidInjector<App> by lazy {
        DaggerAppComponent
            .builder()
            .create(this)
    }


    override fun applicationInjector(): AndroidInjector<out DaggerApplication> = appComponent


    override fun onCreate() {
        super.onCreate()
        // Used Timber for logs
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }

    }

}