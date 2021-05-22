package com.example.kmmapplication.androidApp.ui.games

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.kmmapplication.shared.model.Game
import com.example.kmmapplication.shared.repository.GameRepository

class GamesViewModel(private val gamesRepository: GameRepository) : ViewModel() {

    val games: LiveData<List<Game>> = liveData {
        val data = gamesRepository.getGames() // loadUser is a suspend function.
        emit(data)
    }
}