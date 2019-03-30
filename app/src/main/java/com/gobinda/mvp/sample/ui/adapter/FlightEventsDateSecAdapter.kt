package com.gobinda.mvp.sample.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gobinda.mvp.sample.R
import com.gobinda.mvp.sample.models.FlightEvent
import com.gobinda.mvp.sample.util.toTargetDateFormat
import com.gobinda.mvp.sample.util.toIcon
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.sec_flight_even_header_item.*
import kotlinx.android.synthetic.main.sec_flight_event_row_item.*

/**
 * Flight Event Date wise Section Recycle Adapter
 */
class FlightEventsDateSecAdapter(
    flightEvents: List<FlightEvent>,
    private val itemClick: (FlightEvent) -> Unit = {}
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val sectionFlights = mutableListOf<Any?>()


    var flightEvents: List<FlightEvent> = flightEvents
        set(flightEvents) {
            field = flightEvents
            val groupBy = flightEvents.groupBy { it.date }
            sectionFlights.clear()
            for (key in groupBy.keys) {
                sectionFlights.add(key)
                sectionFlights.addAll(groupBy.get(key)!!)
            }
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflate = LayoutInflater.from(parent.context)
        return when (viewType) {
            SECTION -> inflate.inflate(R.layout.sec_flight_even_header_item, parent, false)
                .run { HeaderViewHolder(this) }
            else -> inflate.inflate(R.layout.sec_flight_event_row_item, parent, false)
                .run {
                    ItemViewHolder(this, itemClick)

                }

        }
    }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (sectionFlights.get(position) is String) {
            (holder as HeaderViewHolder).run {
                bind(sectionFlights.get(position) as String)
            }
        } else {
            (holder as ItemViewHolder).run {
                bind(sectionFlights.get(position) as FlightEvent)
            }

        }
    }

    override fun getItemViewType(position: Int): Int {

        if (sectionFlights.get(position) is String) {
            return SECTION
        }
        return ITEM_VIEW
    }


    override fun getItemCount() = sectionFlights.size


    inner class ItemViewHolder(override val containerView: View, private val itemClick: (FlightEvent) -> Unit) :
        RecyclerView.ViewHolder(containerView), LayoutContainer {

        fun bind(flightEvent: FlightEvent) {
            with(flightEvent) {
                field1.text = "$departure - $destination"
                field2.visibility = View.INVISIBLE
                field3.visibility = View.INVISIBLE
                time.text = "${timeArrive?.take(5)} - ${timeDepart?.take(5)}"
                time.visibility = View.VISIBLE
                icon.toIcon(R.string.fa_plane)
                itemView.setOnClickListener { itemClick(this) }
            }
        }
    }


    inner class HeaderViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView),
        LayoutContainer {

        fun bind(date: String?) {
            section.text = date?.toTargetDateFormat()?.toLowerCase()
        }
    }

    companion object {
        private val ITEM_VIEW = 1
        private val SECTION = 2
    }


}
