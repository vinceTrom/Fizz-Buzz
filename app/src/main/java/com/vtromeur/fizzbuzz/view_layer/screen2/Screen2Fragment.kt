package com.vtromeur.fizzbuzz.view_layer.screen2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.vtromeur.fizzbuzz.ui.theme.FizzBuzzAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class Screen2Fragment: Fragment() {

    private val model: Screen2ViewModel by activityViewModels()
    private val args: Screen2FragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        model.playParams = args.playParams

        return ComposeView(requireContext()).apply {
            setContent {
                FizzBuzzAppTheme() {
                    Screen2Screen(
                        playParams = model.playParams,
                        onBackPressed = {
                            findNavController().popBackStack()
                        }
                    )
                }
            }
        }
    }
}