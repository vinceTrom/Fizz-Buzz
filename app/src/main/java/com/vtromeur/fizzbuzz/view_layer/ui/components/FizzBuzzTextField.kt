package com.vtromeur.fizzbuzz.view_layer.ui.components

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Warning
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.vtromeur.fizzbuzz.R
import com.vtromeur.fizzbuzz.ui.theme.FizzBuzzAppTheme

@Composable
fun FizzBuzzField(
    modifier: Modifier,
    fieldName: String,
    value: String,
    keyboardType: KeyboardType,
    imeAction: ImeAction,
    validator: (String) -> Boolean,
    onFieldValidValueChange: (String) -> Unit,
    fieldUpdate: () -> Unit,
    onSend: () -> Unit = {}
) {
    var isError by rememberSaveable { mutableStateOf(false) }
    var currentValue by rememberSaveable  { mutableStateOf(if(isValueEqualToZero(value)) "" else value) }

    Column(modifier = modifier) {
        OutlinedTextField(
            value = currentValue,
            onValueChange = { newValue ->
                currentValue = newValue
                if (validator(currentValue)) {
                    isError = false
                    onFieldValidValueChange(currentValue)
                } else {
                    isError = true
                }
                fieldUpdate()
            },
            label = {
                Text(
                    text = fieldName,
                )
            },
            modifier = Modifier
                .fillMaxWidth(),
            isError = isError,
            maxLines = 1,
            trailingIcon = {
                if (isError)
                    Icon(Icons.Outlined.Warning, "error", tint = MaterialTheme.colors.error)
            },
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = imeAction,
                keyboardType = keyboardType
            ),
            keyboardActions = KeyboardActions(
                onSend = {
                    onSend()
                }
            )
        )
        if(isError){
            Text(text = stringResource(R.string.screen1_invalid_field_value))
        }
    }
}

private fun isValueEqualToZero(value: String): Boolean{
    return try{
        val intValue = value.toInt()
        intValue == 0
    }catch (e: NumberFormatException){
        false
    }
}

@Preview("FizzBuzz Field")
@Preview("FizzBuzz Field (dark)", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun FizzBuzzTextFieldValidPreview(){
    FizzBuzzAppTheme {
        FizzBuzzField(
            modifier = Modifier.width(width = 140.dp),
            fieldName = stringResource(id = R.string.screen1_int1_field_name),
            value = "18",
            keyboardType = KeyboardType.Number,
            imeAction = ImeAction.Next,
            validator = { value ->
                true
            },
            onFieldValidValueChange = {},
            fieldUpdate = { }
        )
    }
}