package com.gobinda.mvp.sample.ui.flightevent

import com.gobinda.mvp.sample.data.DataSource
import com.gobinda.mvp.sample.di.ActivityScope
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject

/**
 * Flight Events List presenter which implements  FlightsContract.Presenter
 */
@ActivityScope
class FlightEventsPresenter @Inject constructor(private val dataSource: DataSource)
    : FlightEventsContract.Presenter {

    private val disposable = CompositeDisposable()
    private lateinit var view: FlightEventsContract.View

    override fun subscribe(view: FlightEventsContract.View) {
        this.view = view
        disposable.add(dbOrApi())
    }

    override fun unSubscribe() {
        disposable.clear()
    }

    override fun requestRefresh() {
        disposable.add(api())
    }



    private fun dbOrApi(): Disposable {
        return dataSource.database.flightEventDao().loadFlightEvents()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                if (it.isNotEmpty()) {
                    view.showFlights(it)
                    view.showLoading(false)
                } else {
                    api()
                }

            }, { e -> Timber.e(e) })
    }




    private fun api(): Disposable {
        view.showLoading(true)
        return dataSource.api.getFlightEvents()
            .observeOn(Schedulers.io())
            .subscribe({
                val flightDao = dataSource.database.flightEventDao()
                flightDao.deleteAllFlightEvents()
                    .subscribe({
                        Timber.d("Flight Event delete succeed")
                        flightDao.insertFlightEvents(it)
                            .subscribe({
                                Timber.d("Flight Event new records store succeed")
                                flightDao.loadFlightEvents()
                                    .observeOn(AndroidSchedulers.mainThread())
                                    .subscribe({
                                        view.showFlights(it)
                                        view.showLoading(false)
                                    }, { e -> Timber.e(e) })

                            }, { e -> Timber.e(e) })
                    }, { e -> Timber.e(e) })

            }, { e ->
                Observable.just(e)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe {
                        view.showError()
                        view.showLoading(false)
                    }
                Timber.e(e)
            })
    }


}
