package com.vtromeur.fizzbuzz.view_layer.screen1

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.vtromeur.fizzbuzz.R
import com.vtromeur.fizzbuzz.ui.theme.FizzBuzzAppTheme
import com.vtromeur.fizzbuzz.view_layer.data.PlayParams
import com.vtromeur.fizzbuzz.view_layer.ui.components.FizzBuzzField
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

@Composable
fun Screen1Screen(model: Screen1ViewModel,
                  onStartGameClicked: () -> Unit) {

    Scaffold(
        topBar = {
            Screen1TopAppBar()
        }
    ) { paddingValues ->
        Screen1Content(
            modifier = Modifier.padding(paddingValues),
            playParams = model.playParams,
            model.startGameButtonEnabledFlow,
            {model.fieldUpdate()},
            onStartGameClicked = onStartGameClicked)
    }
}

@Composable
fun Screen1TopAppBar(){
    TopAppBar(
        title = {
            Text(text = stringResource(R.string.screen1_topbar_title))
        },
        elevation = dimensionResource(R.dimen.topappbar_elevation)
    )
}

@Composable
fun Screen1Content(modifier: Modifier = Modifier,
                   playParams: PlayParams,
                   startGameButtonEnabledFlow: StateFlow<Boolean>,
                   fieldUpdate: () -> Unit,
                   onStartGameClicked: () -> Unit) {
    val isStartButtonEnabled by startGameButtonEnabledFlow.collectAsState()

    Column(modifier
        .padding(dimensionResource(R.dimen.normal_padding))) {
        Spacer(Modifier.weight(1f))
        Row() {
            Spacer(Modifier.weight(0.5f))
            FizzBuzzField(
                modifier = Modifier.width(width = 140.dp),
                fieldName = stringResource(id = R.string.screen1_int1_field_name),
                value = playParams.int1.toString(),
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Next,
                validator = { value ->
                    isValueAPositiveInteger(value)
                },
                onFieldValidValueChange = { newValue ->
                    playParams.int1 = newValue.toInt()
                },
                fieldUpdate = { fieldUpdate() }
            )
            Spacer(Modifier.weight(1f))
            FizzBuzzField(
                modifier = Modifier.width(width = 140.dp),
                fieldName = stringResource(id = R.string.screen1_int2_field_name),
                value = playParams.int2.toString(),
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Next,
                validator = { value ->
                    isValueAPositiveInteger(value)
                },
                onFieldValidValueChange = { newValue ->
                    playParams.int2 = newValue.toInt()
                },
                fieldUpdate = { fieldUpdate() }
            )
            Spacer(Modifier.weight(0.5f))
        }
        Spacer(Modifier.weight(0.5f))
        Row() {
            Spacer(Modifier.weight(0.5f))
            FizzBuzzField(
                modifier = Modifier.width(width = 140.dp),
                fieldName = stringResource(id = R.string.screen1_word1_field_name),
                value = playParams.word1,
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next,
                validator = { value ->
                    isValueANonEmptyString(value)
                },
                onFieldValidValueChange = { newValue ->
                    playParams.word1 = newValue
                },
                fieldUpdate = { fieldUpdate() }
            )
            Spacer(Modifier.weight(1f))
            FizzBuzzField(
                modifier = Modifier.width(width = 140.dp),
                fieldName = stringResource(id = R.string.screen1_word2_field_name),
                value = playParams.word2,
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next,
                validator = { value ->
                    isValueANonEmptyString(value)
                },
                onFieldValidValueChange = { newValue ->
                    playParams.word2 = newValue
                },
                fieldUpdate = { fieldUpdate() }
            )
            Spacer(Modifier.weight(0.5f))
        }
        Spacer(Modifier.weight(0.5f))
        FizzBuzzField(
            modifier = Modifier
                .width(width = 200.dp)
                .align(Alignment.CenterHorizontally),
            fieldName = stringResource(id = R.string.screen1_limit_field_name),
            value = playParams.limit.toString(),
            keyboardType = KeyboardType.Number,
            imeAction = ImeAction.Send,
            onSend = { onStartGameClicked() },
            onFieldValidValueChange = { newValue ->
                playParams.limit = newValue.toInt()
            },
            validator = { value ->
                isValueAPositiveInteger(value)
            },
            fieldUpdate = { fieldUpdate() }
        )
        Spacer(Modifier.weight(0.5f))
        Button(
            modifier = Modifier
                .width(width = 200.dp)
                .align(Alignment.CenterHorizontally),
            enabled = isStartButtonEnabled,
            onClick = onStartGameClicked
        ) {
            Text(text = stringResource(id = R.string.screen1_start_game_button_label),
                style = MaterialTheme.typography.button,
            )
        }
        Spacer(Modifier.weight(1f))
    }

}

@Preview("Screen 1 Content")
@Preview("Screen 1 Content DARK", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview("Screen 1 Content FR", locale = "fr")
@Composable
fun Screen1ContentPreview(){
    FizzBuzzAppTheme {
        Screen1Content(
            modifier = Modifier.background(MaterialTheme.colors.background),
            playParams = PlayParams(9, 0, "aa", "bb", 100),
            startGameButtonEnabledFlow = MutableStateFlow(true),
            fieldUpdate = {},
            onStartGameClicked = {}
        )
    }
}

private fun isValueAPositiveInteger(value: String): Boolean{
    return try{
        val intValue = Integer.parseInt(value)
        intValue > 0
    }catch(e: NumberFormatException){
        false
    }
}

private fun isValueANonEmptyString(value: String) = value.isNotEmpty()