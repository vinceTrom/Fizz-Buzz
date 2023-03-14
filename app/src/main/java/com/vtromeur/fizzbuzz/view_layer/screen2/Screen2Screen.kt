package com.vtromeur.fizzbuzz.view_layer.screen2

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.vtromeur.fizzbuzz.R
import com.vtromeur.fizzbuzz.utils.FizzBuzzRules
import com.vtromeur.fizzbuzz.view_layer.data.PlayParams

@Composable
fun Screen2Screen(playParams: PlayParams,onBackPressed: () -> Unit) {
    Scaffold(
        topBar = {
            Screen2TopAppBar(
                onBackPressed = onBackPressed
            )
        }
    ){ paddingValues ->
        ValueList(Modifier.padding(paddingValues), playParams)
    }
}

@Composable
fun Screen2TopAppBar(onBackPressed: () -> Unit){
    TopAppBar(
        title = {
            Text(text = stringResource(R.string.screen2_topbar_title))
        },
        navigationIcon = {
            IconButton(onClick = {
                onBackPressed()
            }
            ) {
                Icon(Icons.Filled.ArrowBack, "back")
            }
        },
        elevation = dimensionResource(R.dimen.topappbar_elevation)
    )
}

@Composable
fun ValueList(modifier: Modifier, playParams: PlayParams) {
    val indexs = (1..playParams.limit).toList()
    LazyColumn(modifier = modifier
        .padding(dimensionResource(R.dimen.normal_padding))
        .fillMaxWidth()) {
        items(indexs) { index ->
            ValueItem(playParams, index)
        }
    }
}

@Composable
fun ValueItem(playParams: PlayParams, index: Int){
    Text(modifier = Modifier.height(40.dp),
        text = FizzBuzzRules.fizzBuzzOutputValue(playParams, index),
        style = MaterialTheme.typography.subtitle1)
}

@Preview("Value List Item")
@Composable
fun ValueItemPreview(){
    ValueItem(
        playParams = PlayParams(9, 0, "aa", "bb", 100),
        0
    )
}