package com.vtromeur.fizzbuzz.view_layer.screen1

import com.vtromeur.fizzbuzz.view_layer.data.PlayParams

sealed class Screen1Actions {
    data class GoToScreen2(val playParams: PlayParams): Screen1Actions()
}