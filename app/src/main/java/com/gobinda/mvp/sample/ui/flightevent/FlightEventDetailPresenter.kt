package com.gobinda.mvp.sample.ui.flightevent

import com.gobinda.mvp.sample.data.DataSource
import com.gobinda.mvp.sample.di.ActivityScope
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject

/**
 * Flight Event Details presenter which implements  FlightDetailContract.Presenter
 */
@ActivityScope
class FlightEventDetailPresenter @Inject constructor(private val dataSource: DataSource)
    : FlightEventDetailContract.Presenter {

    private val disposable = CompositeDisposable()
    private lateinit var view: FlightEventDetailContract.View


    override fun subscribe(view: FlightEventDetailContract.View) {
        this.view = view
    }

    override fun unSubscribe() {
        disposable.clear()

    }

    override fun requestDetails(id: Int) {
        disposable.add(
            dataSource.database.flightEventDao().loadFlightEventById(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    Timber.d("Flight Data $it")
                    view.showDetails(it)
                }, { e ->
                    view.showError()
                    Timber.e(e)
                })
        )
    }
}