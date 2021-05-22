package com.example.kmmapplication.shared.network

import com.example.kmmapplication.shared.model.Game
import com.example.kmmapplication.shared.model.Player
import com.example.kmmapplication.shared.model.PlayerWithRating
import io.ktor.client.*
import io.ktor.client.request.*

class GameApi(private val httpClient: HttpClient) {

    suspend fun getAllPlayers(): List<Player> {
        return httpClient.get(PLAYERS_ENDPOINT)
    }

    suspend fun getAllGames(): List<Game> {
        return httpClient.get(GAMES_ENDPOINT)
    }

    suspend fun getPlayerStandings(): List<PlayerWithRating> {
        return httpClient.get(PLAYERS_STANDINGS_ENDPOINT)
    }

    companion object {
        private const val BASE_URL = "http://192.168.1.236:8080/v1"
        private const val PLAYERS_ENDPOINT = "$BASE_URL/players"
        private const val GAMES_ENDPOINT = "$BASE_URL/games"
        private const val PLAYERS_STANDINGS_ENDPOINT =
            "$BASE_URL/players/ratings?gameType=tableTennis"
    }
}