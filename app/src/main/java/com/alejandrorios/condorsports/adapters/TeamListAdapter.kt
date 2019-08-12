package com.alejandrorios.condorsports.adapters

import android.support.v7.widget.RecyclerView
import android.support.v7.widget.RecyclerView.Adapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.alejandrorios.condorsports.R
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.alejandrorios.condorsports.domain.models.Teams

class TeamListAdapter(private var teamsList: List<Teams>) : Adapter<TeamListAdapter.TeamHolder>() {

    interface Delegate {
        fun onTeamClicked(team: Teams?)
    }

    var delegate: Delegate? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeamHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_team, parent, false)

        return TeamHolder(view)
    }

    override fun getItemCount() = teamsList.size

    override fun onBindViewHolder(holder: TeamHolder, position: Int) {
        holder.bindData(teamsList[position])
    }

    inner class TeamHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val teamBadge: ImageView = itemView.findViewById(R.id.imgTeamBadge)
        private val teamName: TextView = itemView.findViewById(R.id.txtTeamName)
        private val teamStadium: TextView = itemView.findViewById(R.id.txtTeamStadium)
        private val lytTeam: View = itemView.findViewById(R.id.lytTeam)

        fun bindData(teamItem: Teams?) {
            teamName.text = teamItem?.strTeam
            teamStadium.text = teamItem?.strStadium

            Glide.with(teamBadge.context)
                .load(teamItem?.strTeamBadge)
                .diskCacheStrategy(DiskCacheStrategy.DATA)
                .placeholder(R.drawable.ic_soccer_ball)
                .error(R.drawable.ic_no_img)
                .into(teamBadge)

            lytTeam.setOnClickListener {
                delegate?.onTeamClicked(teamItem)
            }
        }
    }
}
