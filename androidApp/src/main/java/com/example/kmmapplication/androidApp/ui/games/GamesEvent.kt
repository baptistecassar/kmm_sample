package com.example.kmmapplication.androidApp.ui.games

import com.example.kmmapplication.shared.model.Game

sealed class GameListEvent {

    object GameListLoading : GameListEvent()
    data class GameListLoaded(val games: List<Game>) : GameListEvent()
    data class GameListFailed(val throwable: Throwable) : GameListEvent()

}