package com.example.kmmapplication.shared.cache

import com.example.kmmapplication.shared.model.Player

internal class Database(databaseDriverFactory: DatabaseDriverFactory) {
    private val database = AppDatabase(databaseDriverFactory.createDriver())
    private val dbQuery = database.appDatabaseQueries

    internal fun clearDatabase() {
        dbQuery.transaction {
            dbQuery.removeAllPlayers()
        }
    }

    internal fun getAllPlayers(): List<Player> {
        return dbQuery.selectAllPlayers(::mapPlayer).executeAsList()
    }

    internal fun createPlayers(players: List<Player>) {
        dbQuery.transaction {
            players.forEach { player ->
                insertPlayer(player)
            }
        }
    }

    internal fun createPlayer(player: Player) {
        dbQuery.transaction {
            insertPlayer(player)
        }
    }

    private fun mapPlayer(
        name: String,
        email: String,
        imageUrl: String?
    ): Player {
        return Player(
            name = name,
            email = email,
            imageUrl = imageUrl
        )
    }

    private fun insertPlayer(player: Player) {
        dbQuery.insertPlayer(
            name = player.name,
            email = player.email,
            imageUrl = player.imageUrl
        )
    }
}