package com.vtromeur.fizzbuzz.utils

import com.vtromeur.fizzbuzz.view_layer.data.PlayParams

object FizzBuzzRules {
    fun fizzBuzzOutputValue(playParams: PlayParams, value: Int): String {
        playParams.let {
            return when {
                isAMultiple(value, it.int1 * it.int2) -> it.word1 + it.word2
                isAMultiple(value, it.int1) -> it.word1
                isAMultiple(value, it.int2) -> it.word2
                else -> value.toString()
            }
        }
    }
    private fun isAMultiple(value1: Int, value2: Int)
            = value1 % value2 == 0
}