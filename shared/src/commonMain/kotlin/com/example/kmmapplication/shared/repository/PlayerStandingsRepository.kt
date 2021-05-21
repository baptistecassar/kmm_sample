package com.example.kmmapplication.shared.repository

import com.example.kmmapplication.shared.model.PlayerWithRating
import com.example.kmmapplication.shared.network.GameApi

class PlayerStandingsRepository {

    private val api = GameApi()

    @Throws(Exception::class)
    suspend fun getPlayerStandings(): List<PlayerWithRating> {
        return api.getPlayerStandings()
    }
}