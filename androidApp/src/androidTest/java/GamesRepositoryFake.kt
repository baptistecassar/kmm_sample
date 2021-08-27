package com.example.kmmapplication.androidApp

import com.example.kmmapplication.shared.model.Game
import com.example.kmmapplication.shared.model.Player
import com.example.kmmapplication.shared.repository.GameRepositoryInterface

class GamesRepositoryFake : GameRepositoryInterface {
    val gameList = listOf(
        Game(
            id = 1,
            gameType = "tennis",
            winningPlayers = listOf(Player(name = "baptiste", email = "baptiste.test@pinch.nl")),
            winningScore = 11,
            loosingPlayers = listOf(Player(name = "user", email = "user.test@pinch.nl")),
            loosingScore = 9,
            dateUtc = "2021-08-26T13:22:52"
        )
    )

    override suspend fun getGames(): List<Game> {
        return gameList
    }
}
