package com.gobinda.mvp.sample.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

/**
 * Flight Event Model also used for ORM (Room) and Json object
 */
@Entity(tableName = "flight_events")
data class FlightEvent(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    @Transient
    val id : Int,

    @ColumnInfo(name = "flight_no")
    @SerializedName("Flight Nr")
    val flightNo: String?,

    @ColumnInfo(name = "date")
    @SerializedName("Date")
    val date: String?,

    @ColumnInfo(name = "aircraft_type")
    @SerializedName("Aircraft Type;")
    val aircraftType: String?,

    @ColumnInfo(name = "tail")
    @SerializedName("Tail")
    val tail: String?,

    @ColumnInfo(name = "departure")
    @SerializedName("Departure")
    val departure: String?,

    @ColumnInfo(name = "destination")
    @SerializedName("Destination")
    val destination: String?,

    @ColumnInfo(name = "time_depart")
    @SerializedName("Time Depart")
    val timeDepart: String?,

    @ColumnInfo(name = "time_arrive")
    @SerializedName("Time_Arrive")
    val timeArrive: String?,

    @ColumnInfo(name = "duty_id")
    @SerializedName("Duty ID")
    val dutyId: String?,

    @ColumnInfo(name = "duty_code")
    @SerializedName("Duty Code")
    val dutyCode: String?,

    @ColumnInfo(name = "captain")
    @SerializedName("Captain")
    val captain: String?,
    @ColumnInfo(name = "first_officer")
    @SerializedName("First Officer")
    val firstOfficer: String?,

    @ColumnInfo(name = "flight_attendant")
    @SerializedName("Flight Attendant")
    val flightAttendant: String?
)


