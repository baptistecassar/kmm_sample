package com.example.kmmapplication.shared.utils

import com.example.kmmapplication.shared.model.Player

fun List<Player>.concatenatePlayers(): String {
    return Utils().concatenatePlayers(this)
}


class Utils {
    fun concatenatePlayers(players: List<Player>): String {
        val names = players.map { it.name }
        return names.joinToString(", ")
    }
}