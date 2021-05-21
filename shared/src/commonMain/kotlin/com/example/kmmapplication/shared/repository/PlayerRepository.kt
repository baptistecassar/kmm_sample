package com.example.kmmapplication.shared.repository

import com.example.kmmapplication.shared.cache.Database
import com.example.kmmapplication.shared.cache.DatabaseDriverFactory
import com.example.kmmapplication.shared.model.Player
import com.example.kmmapplication.shared.network.GameApi

class PlayerRepository(databaseDriverFactory: DatabaseDriverFactory) {
    private val database = Database(databaseDriverFactory)
    private val api = GameApi()

    @Throws(Exception::class)
    suspend fun getPlayers(forceReload: Boolean): List<Player> {
        val cachedPlayers = database.getAllPlayers()
        return if (cachedPlayers.isNotEmpty() && !forceReload) {
            cachedPlayers
        } else {
            api.getAllPlayers().also {
                database.clearDatabase()
                database.createPlayers(it)
            }
        }
    }
}