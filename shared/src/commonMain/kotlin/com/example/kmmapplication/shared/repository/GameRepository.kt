package com.example.kmmapplication.shared.repository

import com.example.kmmapplication.shared.model.Game
import com.example.kmmapplication.shared.network.GameApi
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

interface GameRepositoryInterface {
    suspend fun getGames(): List<Game>
}

class GameRepository : KoinComponent, GameRepositoryInterface {

    private val api: GameApi by inject()

    @Throws(Exception::class)
    override suspend fun getGames(): List<Game> {
        return api.getAllGames()
    }
}