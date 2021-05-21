package com.example.kmmapplication.shared.model

import kotlinx.serialization.Serializable

@Serializable
data class Game(
    val id: Int,
    val gameType: String,
    val winningPlayers: List<Player>,
    val loosingPlayers: List<Player>,
    val loosingScore: Int? = null,
    val winningScore: Int? = null,
    val dateUtc: String,
)