package com.vtromeur.fizzbuzz.view_layer.data

import android.os.Parcelable
import androidx.annotation.Keep
import kotlinx.parcelize.Parcelize

@Keep
@Parcelize
data class PlayParams (
    var int1: Int,
    var int2: Int,
    var word1: String,
    var word2: String,
    var limit: Int
): Parcelable