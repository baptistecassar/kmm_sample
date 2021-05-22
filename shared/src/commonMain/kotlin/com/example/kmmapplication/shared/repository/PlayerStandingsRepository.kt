package com.example.kmmapplication.shared.repository

import com.example.kmmapplication.shared.model.PlayerWithRating
import com.example.kmmapplication.shared.network.GameApi
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class PlayerStandingsRepository: KoinComponent {

    private val api : GameApi by inject()

    @Throws(Exception::class)
    suspend fun getPlayerStandings(): List<PlayerWithRating> {
        return api.getPlayerStandings()
    }
}