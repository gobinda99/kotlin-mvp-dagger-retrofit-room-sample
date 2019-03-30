package com.gobinda.mvp.sample.ui

/**
 * Base Presenter Interface class of the MVP
 */
interface BasePresenter<T> {
    /**
     * call this method on resume of Activity or Fragment
     *
     */
    fun subscribe(view: T)

    /**
     * Call this method ton Pause of Activity or Fragment
     */
    fun unSubscribe()
}