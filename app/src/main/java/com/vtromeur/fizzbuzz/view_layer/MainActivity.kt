package com.vtromeur.fizzbuzz.view_layer

import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import com.vtromeur.fizzbuzz.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : FragmentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}