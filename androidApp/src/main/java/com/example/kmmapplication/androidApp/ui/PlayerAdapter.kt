package com.example.kmmapplication.androidApp.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.kmmapplication.androidApp.R
import com.example.kmmapplication.androidApp.databinding.ItemGameBinding
import com.example.kmmapplication.androidApp.databinding.ItemLaunchBinding
import com.example.kmmapplication.shared.model.Player

class PlayerAdapter : ListAdapter<Player, PlayerAdapter.PlayerViewHolder>(DIFF_CALLBACK) {

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Player>() {
            override fun areItemsTheSame(oldItem: Player, newItem: Player): Boolean {
                return oldItem.name == oldItem.name
                        && oldItem.email == oldItem.email
                        && oldItem.imageUrl == oldItem.imageUrl
            }

            override fun areContentsTheSame(oldItem: Player, newItem: Player): Boolean {
                return oldItem.name == oldItem.name
                        && oldItem.email == oldItem.email
                        && oldItem.imageUrl == oldItem.imageUrl
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayerViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemLaunchBinding.inflate(inflater, parent, false)
        return PlayerViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: PlayerViewHolder, position: Int) {
        holder.bindData(getItem(position))
    }

    inner class PlayerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val playerNameTextView = itemView.findViewById<TextView>(R.id.playerName)
        private val playerEmailTextView = itemView.findViewById<TextView>(R.id.playerEmail)

        fun bindData(player: Player) {
            // ...
            playerNameTextView.text = player.name
            playerEmailTextView.text = player.email
        }
    }
}