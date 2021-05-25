package com.example.kmmapplication.androidApp.ui.games

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kmmapplication.shared.repository.GameRepository
import kotlinx.coroutines.launch

class GamesViewModel(private val gamesRepository: GameRepository) : ViewModel() {

    private val _gameListEvent = MutableLiveData<GameListEvent>()
    val gameListEvent: LiveData<GameListEvent> = _gameListEvent

    init {
        refreshGames()
    }

    fun refreshGames() {
        _gameListEvent.value = GameListEvent.GameListLoading
        viewModelScope.launch {
            kotlin.runCatching {
                gamesRepository.getGames()
            }.onSuccess {
                _gameListEvent.value = GameListEvent.GameListLoaded(it)
            }.onFailure {
                _gameListEvent.value = GameListEvent.GameListFailed(it)
            }
        }
    }
}