package com.example.kmmapplication.shared.viewModel

import com.example.kmmapplication.shared.model.Game

sealed class GetGamesState {
}
data class SuccessGetGamesState(val games: List<Game>) : GetGamesState()
object LoadingGetGamesState : GetGamesState()
data class ErrorGetGamesState(val error: String) : GetGamesState()