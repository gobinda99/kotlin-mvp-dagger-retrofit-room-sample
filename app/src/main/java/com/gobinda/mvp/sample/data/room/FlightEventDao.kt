package com.gobinda.mvp.sample.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.gobinda.mvp.sample.models.FlightEvent
import io.reactivex.Completable
import io.reactivex.Single

/**
 * Data access class Flight Event Model.
 * Insert, update, select, delete operation/query using RxJava so thread
 * scheduling, non block ui operation can be maintained easily.
 */
@Dao
interface FlightEventDao {

    @Query("SELECT * FROM flight_events")
    fun loadFlightEvents(): Single<List<FlightEvent>>

    @Query("SELECT * from flight_events where id = :id LIMIT 1")
    fun loadFlightEventById(id: Int): Single<FlightEvent>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFlightEvents(flights: List<FlightEvent>): Completable

    @Query("DELETE FROM flight_events")
    fun deleteAllFlightEvents(): Completable
}