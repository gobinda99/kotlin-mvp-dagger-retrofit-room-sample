package com.gobinda.mvp.sample.ui.flightevent

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.snackbar.Snackbar
import com.gobinda.mvp.sample.R
import com.gobinda.mvp.sample.models.FlightEvent
import com.gobinda.mvp.sample.util.showSnackBar
import com.gobinda.mvp.sample.util.toTargetDateFormat
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.frag_flight_event_detail.*
import javax.inject.Inject

/**
 * This fragment class to display details of a flight's event
 */
class FlightDetailFragment : DaggerFragment(), FlightEventDetailContract.View {

    @Inject
    override lateinit var presenter: FlightEventDetailContract.Presenter

    companion object {
        fun getInstance(id: Int): FlightDetailFragment {
            val fragment = FlightDetailFragment()
            val bundle = Bundle()
            bundle.putInt("id", id)
            fragment.arguments = bundle
            return fragment
        }

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.frag_flight_event_detail, container, false)
    }

    override fun onResume() {
        super.onResume()
        presenter.subscribe(this)
        presenter.requestDetails(arguments!!.getInt("id", 0))
    }

    override fun onPause() {
        super.onPause()
        presenter.unSubscribe()
    }


    override fun showDetails(flightEvent: FlightEvent) {
        with(flightEvent) {
            _flightNo.text = flightNo
            _date.text = flightEvent.date?.toTargetDateFormat()
            _aircraftType.text = aircraftType
            _tail.text = tail
            _departure.text = departure
            _destination.text = destination
            _timeDepart.text = timeDepart
            _timeArrive.text = timeArrive
            _dutyId.text = dutyId
            _dutyCode.text = dutyCode
            _captain.text = captain
            _firstOfficer.text = firstOfficer
            _flightAttendant.text = flightAttendant

        }

    }


    override fun showError() {
        view?.showSnackBar(getString(R.string.msg_failed_load), Snackbar.LENGTH_LONG)
    }


}