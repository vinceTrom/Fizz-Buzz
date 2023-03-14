package com.vtromeur.fizzbuzz.view_layer.screen2

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.vtromeur.fizzbuzz.view_layer.data.PlayParams
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class Screen2ViewModel @Inject constructor(
    app: Application)
    :AndroidViewModel(app){

     lateinit var playParams: PlayParams
}