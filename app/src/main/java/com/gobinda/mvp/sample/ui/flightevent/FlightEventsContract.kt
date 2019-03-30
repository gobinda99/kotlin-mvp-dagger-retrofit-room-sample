package com.gobinda.mvp.sample.ui.flightevent

import com.gobinda.mvp.sample.ui.BasePresenter
import com.gobinda.mvp.sample.ui.BaseView
import com.gobinda.mvp.sample.models.FlightEvent

/**
 * Flight Event list contract between view and presenter of MVP
 */
interface FlightEventsContract {
    interface Presenter : BasePresenter<View> {
        fun requestRefresh()
    }

    interface View : BaseView<Presenter> {

        fun showLoading(active: Boolean)

        fun showFlights(flightEvents : List<FlightEvent>)

        fun showError()

    }
}