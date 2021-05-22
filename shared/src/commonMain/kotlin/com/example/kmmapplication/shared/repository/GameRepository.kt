package com.example.kmmapplication.shared.repository

import com.example.kmmapplication.shared.model.Game
import com.example.kmmapplication.shared.network.GameApi
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class GameRepository : KoinComponent{

    private val api : GameApi by inject()

    @Throws(Exception::class)
    suspend fun getGames(): List<Game> {
        return api.getAllGames()
    }
}