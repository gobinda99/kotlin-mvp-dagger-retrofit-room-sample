package com.gobinda.mvp.sample.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.gobinda.mvp.sample.models.FlightEvent

/**
 * ORM Database Helper class to connect to the database
 * named flight-sample.db and its records
 */
@Database(entities = arrayOf(FlightEvent::class), version = 1)
abstract class FlightDatabase : RoomDatabase() {

    abstract fun flightEventDao(): FlightEventDao

    companion object {

        @Volatile
        private var INSTANCE: FlightDatabase? = null

        fun getInstance(context: Context): FlightDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: bindDatabase(context).also { INSTANCE = it }
            }

        private fun bindDatabase(context: Context) =
            Room.databaseBuilder(context.applicationContext, FlightDatabase::class.java, "flight-sample.db")
                .build()
    }
}
