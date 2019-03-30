package com.gobinda.mvp.sample.di

import com.gobinda.mvp.sample.App
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

/**
 * Application component consisting of
 * App Module, Activity Binding module.
 * The Scope is Singleton it life will be a
 * active entire application
 *
 */
@Singleton
@Component(
    modules = arrayOf(
        AppModule::class,
        ActivityBindingModule::class,
        AndroidSupportInjectionModule::class
    )
)
interface AppComponent : AndroidInjector<App> {

    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<App>()


}