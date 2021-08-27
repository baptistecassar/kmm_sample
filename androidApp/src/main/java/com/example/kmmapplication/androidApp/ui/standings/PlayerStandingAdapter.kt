package com.example.kmmapplication.androidApp.ui.standings

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.kmmapplication.androidApp.databinding.ItemPlayerStandingBinding
import com.example.kmmapplication.shared.model.PlayerWithRating

class PlayerStandingAdapter :
    ListAdapter<PlayerWithRating, PlayerStandingAdapter.PlayerStandingViewHolder>(DIFF_CALLBACK) {

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<PlayerWithRating>() {
            override fun areItemsTheSame(
                oldItem: PlayerWithRating,
                newItem: PlayerWithRating
            ): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(
                oldItem: PlayerWithRating,
                newItem: PlayerWithRating
            ): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayerStandingViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemPlayerStandingBinding.inflate(inflater, parent, false)
        return PlayerStandingViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PlayerStandingViewHolder, position: Int) {
        holder.bindData(position, getItem(position))
    }

    inner class PlayerStandingViewHolder(private val binding: ItemPlayerStandingBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bindData(position: Int, playerWithRating: PlayerWithRating) {
            binding.playerStanding.text = "${position + 1}"
            binding.playerName.text = playerWithRating.player.name
            binding.playerRating.text = playerWithRating.rating.currentRating.toString()
        }
    }
}