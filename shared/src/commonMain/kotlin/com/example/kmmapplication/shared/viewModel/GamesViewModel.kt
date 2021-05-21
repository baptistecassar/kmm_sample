package com.example.kmmapplication.shared.viewModel

import com.example.kmmapplication.shared.repository.GameRepository
import dev.icerock.moko.mvvm.livedata.LiveData
import dev.icerock.moko.mvvm.livedata.MutableLiveData
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.launch

class GamesViewModel : ViewModel() {

    private val gameRepository = GameRepository()

    private val _getGamesState = MutableLiveData<GetGamesState>(LoadingGetGamesState)
    val getGamesState: LiveData<GetGamesState> = _getGamesState

    fun getGames() = viewModelScope.launch {
        _getGamesState.postValue(LoadingGetGamesState)
        viewModelScope.launch {
            kotlin.runCatching {
                gameRepository.getGames()
            }.onSuccess {
                _getGamesState.postValue(LoadingGetGamesState)
            }.onFailure {
                _getGamesState.postValue(LoadingGetGamesState)
            }
        }
    }
}