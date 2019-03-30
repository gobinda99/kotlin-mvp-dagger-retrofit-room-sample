package com.gobinda.mvp.sample.ui.flightevent

import com.gobinda.mvp.sample.ui.BasePresenter
import com.gobinda.mvp.sample.ui.BaseView
import com.gobinda.mvp.sample.models.FlightEvent

/**
 * Flight Detail contract between view and presenter of MVP
 */
interface FlightEventDetailContract {

    interface Presenter : BasePresenter<View> {
        fun requestDetails(id : Int)
    }


    interface View : BaseView<Presenter> {

        fun showDetails(flightEvent : FlightEvent)

        fun showError()

    }
}