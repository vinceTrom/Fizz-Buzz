package com.vtromeur.fizzbuzz.view_layer.screen1

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.vtromeur.fizzbuzz.ui.theme.FizzBuzzAppTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class Screen1Fragment: Fragment() {

    private val model: Screen1ViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                FizzBuzzAppTheme() {
                    Screen1Screen(
                        model = model,
                        onStartGameClicked = {
                            model.onStartGameClicked()
                        }
                    )
                }
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lifecycleScope.launch {
            model.action.collect{ action ->
                when(action){
                    is Screen1Actions.GoToScreen2 -> {
                        findNavController().navigate(Screen1FragmentDirections.actionScreen1ToScreen2(action.playParams))
                        model.actionConsumed()
                    }
                    else -> {}
                }
            }
        }
    }
}