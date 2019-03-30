package com.gobinda.mvp.sample.ui.flightevent

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.gobinda.mvp.sample.R
import com.gobinda.mvp.sample.ui.adapter.FlightEventsDateSecAdapter
import com.gobinda.mvp.sample.di.ActivityScope
import com.gobinda.mvp.sample.models.FlightEvent
import com.gobinda.mvp.sample.util.showSnackBar
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.frag_flight_list.*
import timber.log.Timber
import java.util.*
import javax.inject.Inject

/**
 * This fragment class to display list of flight's event
 */
@ActivityScope
class FlightEventListFragment  @Inject constructor()
    : DaggerFragment() , FlightEventsContract.View {

    @Inject
    override lateinit var presenter: FlightEventsContract.Presenter

    private val fltEventAdapter = FlightEventsDateSecAdapter(ArrayList(0),
        {  showDetail(it)})


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.frag_flight_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(recyclerFlightsView) {
            setHasFixedSize(true)
            adapter = fltEventAdapter
            val manager = LinearLayoutManager(context)
            layoutManager = manager
            addItemDecoration(DividerItemDecoration(context,manager.orientation))
        }

        pullToRefresh.setOnRefreshListener {
          presenter.requestRefresh()
        }

        emptyView.setOnClickListener {
            showEmptyView(false)
            presenter.requestRefresh()
        }


    }

    override fun onResume() {
        super.onResume()
        presenter.subscribe(this)
    }

    override fun onPause() {
        super.onPause()
        presenter.unSubscribe()
    }



    override fun showLoading(active: Boolean) {
        if (active)
            pullToRefresh?.takeIf {
                !it.isRefreshing
            }?.apply { isRefreshing = true } else {
            pullToRefresh?.takeIf {
                it.isRefreshing
            }?.apply { isRefreshing = false }

        }
    }


    override fun showFlights(flightEvents: List<FlightEvent>) {
        Timber.d("Flight Events size %s", flightEvents.size)
        fltEventAdapter.flightEvents = flightEvents
        showEmptyView(false)
    }


    override fun showError() {
        if (fltEventAdapter.flightEvents.isNullOrEmpty()) {
            showEmptyView(true)
        } else {
            view?.showSnackBar(getString(R.string.msg_failed_to_refresh), Snackbar.LENGTH_LONG)
        }
    }

    private fun showEmptyView(flag : Boolean) {
        if(flag) {
            emptyView?.visibility = View.VISIBLE
            recyclerFlightsView?.visibility = View.GONE
        }else {
            emptyView?.visibility = View.GONE
            recyclerFlightsView?.visibility = View.VISIBLE

        }
    }

    private fun showDetail(flight: FlightEvent) {
        activity!!.supportFragmentManager.beginTransaction().run {
            replace(R.id.container, FlightDetailFragment.getInstance(flight.id))
            addToBackStack(null)
            commit()
        }
    }



}