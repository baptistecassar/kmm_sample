package com.example.kmmapplication.androidApp.ui.games

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.kmmapplication.androidApp.databinding.ItemGameBinding
import com.example.kmmapplication.shared.model.Game
import com.example.kmmapplication.shared.utils.concatenatePlayers

class GameAdapter : ListAdapter<Game, GameAdapter.GameViewHolder>(DIFF_CALLBACK) {

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Game>() {
            override fun areItemsTheSame(oldItem: Game, newItem: Game): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Game, newItem: Game): Boolean {
                return oldItem.winningPlayers == newItem.winningPlayers
                        && oldItem.loosingPlayers == newItem.loosingPlayers
                        && oldItem.dateUtc == newItem.dateUtc
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemGameBinding.inflate(inflater, parent, false)
        return GameViewHolder(binding)
    }

    override fun onBindViewHolder(holder: GameViewHolder, position: Int) {
        holder.bindData(getItem(position))
    }

    inner class GameViewHolder(private val binding: ItemGameBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bindData(game: Game) {
            binding.gameType.text = game.gameType
            binding.winningScore.text = game.winningScore?.toString()
            binding.winningScore.isVisible = game.winningScore != null
            binding.loosingScore.text = game.loosingScore?.toString()
            binding.loosingScore.isVisible = game.loosingScore != null
            binding.loosingPlayers.text = game.loosingPlayers.concatenatePlayers()
            binding.winningPlayers.text = game.winningPlayers.concatenatePlayers()
        }
    }
}