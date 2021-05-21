package com.example.kmmapplication.shared.repository

import com.example.kmmapplication.shared.model.Game
import com.example.kmmapplication.shared.network.GameApi

class GameRepository {

    private val api = GameApi()

    @Throws(Exception::class)
    suspend fun getGames(): List<Game> {
        return api.getAllGames()
    }
}