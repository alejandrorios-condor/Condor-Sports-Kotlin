package com.alejandrorios.condorsports.adapters

import android.graphics.drawable.Drawable
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.alejandrorios.condorsports.R
import com.alejandrorios.condorsports.common.RealmManager
import com.alejandrorios.condorsports.common.setTopDrawable
import com.alejandrorios.condorsports.models.EventsData
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.target.SimpleTarget
import com.bumptech.glide.request.transition.Transition
import java.text.SimpleDateFormat
import java.util.*

class EventsListAdapter(private var eventsList: List<EventsData>) :
    RecyclerView.Adapter<EventsListAdapter.EventHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_event, parent, false)

        return EventHolder(view)
    }

    override fun getItemCount() = eventsList.size

    override fun onBindViewHolder(holder: EventHolder, position: Int) {
        holder.bindData(eventsList[position])
    }

    inner class EventHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private var date = Date()
        private val sf = SimpleDateFormat("yyyy-MM-dd", Locale.US)

        private val homeTeam: TextView = itemView.findViewById(R.id.txtHomeTeam)
        private val awayTeam: TextView = itemView.findViewById(R.id.txtAwayTeam)
        private val eventDate: TextView = itemView.findViewById(R.id.txtEventDate)

        fun bindData(eventItem: EventsData?) {
            val homeTeamBadge: String? = eventItem?.idHomeTeam?.let { RealmManager.instance.getTeamByName(it) }
            val awayTeamBadge: String? = eventItem?.idAwayTeam?.let { RealmManager.instance.getTeamByName(it) }
            homeTeam.text = eventItem?.strHomeTeam
            awayTeam.text = eventItem?.strAwayTeam

            try {
                date = sf.parse(eventItem?.dateEvent)
                sf.applyPattern("dd MMMM yyyy")

                eventDate.text = sf.format(date)

            } catch (e: Exception) {
                e.printStackTrace()
            }

            Glide.with(homeTeam.context)
                .load(homeTeamBadge)
                .diskCacheStrategy(DiskCacheStrategy.DATA)
                .placeholder(R.drawable.ic_soccer_ball)
                .error(R.drawable.ic_no_img)
                .into(object : SimpleTarget<Drawable>(100, 220) {
                    override fun onResourceReady(resource: Drawable, transition: Transition<in Drawable>?) {
                        homeTeam.setTopDrawable(resource)
                    }
                })

            Glide.with(awayTeam.context)
                .load(awayTeamBadge)
                .diskCacheStrategy(DiskCacheStrategy.DATA)
                .placeholder(R.drawable.ic_soccer_ball)
                .error(R.drawable.ic_no_img)
                .into(object : SimpleTarget<Drawable>(100, 220) {
                    override fun onResourceReady(resource: Drawable, transition: Transition<in Drawable>?) {
                        awayTeam.setTopDrawable(resource)
                    }
                })
        }
    }
}