package com.example.kmmapplication.androidApp.ui.games

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kmmapplication.shared.model.Game
import com.example.kmmapplication.shared.repository.GameRepositoryInterface
import kotlinx.coroutines.launch

class GamesViewModel(private val gamesRepository: GameRepositoryInterface) : ViewModel() {

    private val _gameListEvent = MutableLiveData<GameListEvent>()
    val gameListEvent: LiveData<GameListEvent> = _gameListEvent

    private val _gameList = MutableLiveData<List<Game>>()
    val gameList: LiveData<List<Game>> = _gameList

    init {
        refreshGames()
    }

    fun refreshGames() {
        _gameListEvent.postValue(GameListEvent.GameListLoading)
        viewModelScope.launch {
            kotlin.runCatching {
                gamesRepository.getGames()
            }.onSuccess {
                _gameList.postValue(it)
                _gameListEvent.postValue(GameListEvent.GameListLoaded(it))
            }.onFailure {
                _gameListEvent.postValue(GameListEvent.GameListFailed(it))
            }
        }
    }
}