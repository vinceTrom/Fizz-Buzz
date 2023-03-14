package com.vtromeur.fizzbuzz.view_layer.screen1

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.vtromeur.fizzbuzz.view_layer.data.PlayParams
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class Screen1ViewModel @Inject constructor(
    app: Application)
    : AndroidViewModel(app){

    val playParams = PlayParams(0, 0, "", "", 0)
    private val _action = MutableStateFlow<Screen1Actions?>(null)
    val action = _action.asStateFlow()

    private val _startGameButtonEnabledFlow = MutableStateFlow(isStartGameButtonEnabled())
    val startGameButtonEnabledFlow: StateFlow<Boolean> = _startGameButtonEnabledFlow.asStateFlow()

    fun fieldUpdate() {
        viewModelScope.launch(Dispatchers.Main) {
            _startGameButtonEnabledFlow.emit(isStartGameButtonEnabled())
        }
    }

    fun onStartGameClicked(){
        viewModelScope.launch {
            _action.emit(Screen1Actions.GoToScreen2(playParams))
        }
    }

    fun actionConsumed() {
        _action.value = null
    }

    private fun isStartGameButtonEnabled(): Boolean {
        //Check that every parameters needed to start a Fizz Buzz Game are filled
        return playParams.int1 > 0
                && playParams.int2 > 0
                && playParams.word1.isNotEmpty()
                && playParams.word2.isNotEmpty()
                && playParams.limit > 0
    }
}