package com.task.onetaskapp.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.task.onetaskapp.ui.theme.AppTheme

@Composable
fun OneTaskLabelView(title: String) {
    Text(
        text = title,
        style = AppTheme.typography.promt14spW400,
        textAlign = TextAlign.Start,
        color = AppTheme.colors.white
    )
}

@Stable
@Composable
fun OneTaskInputTextField(
    modifier: Modifier = Modifier,
    title: String,
    value: String,
    readOnly: Boolean = false,
    enabled: Boolean = true,
    onValueChanged: (String) -> Unit
) {
    var textState by rememberSaveable { mutableStateOf("") }
    var errorState by rememberSaveable { mutableStateOf(false) }
    var errorMessage by rememberSaveable { mutableStateOf("") }
    val focusManager = LocalFocusManager.current

    Column(horizontalAlignment = Alignment.Start, verticalArrangement = Arrangement.Center) {

        TextField(
            modifier = modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .defaultMinSize(minHeight = 60.dp)
                .padding(
                    start = AppTheme.dimensions.paddingXL,
                    end = AppTheme.dimensions.paddingXL,
                    top = 10.dp
                ),
            shape = RoundedCornerShape(AppTheme.shapes.shapeLarge),
            value = value,
            readOnly = readOnly,
            enabled = enabled,
            onValueChange = {
                textState = it
                when (textState.isEmpty()) {
                    true -> {
                        errorState = true
                        errorMessage = "$title should not be empty"
                    }
                    false -> {
                        errorState = false
                        errorMessage = ""
                    }
                }
                onValueChanged(it)
            },

            label = { OneTaskLabelView(title = title) },
            textStyle = AppTheme.typography.promt14spW400,
            colors = textFieldColors(),
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
            keyboardActions = KeyboardActions(
                onNext = {

                    // Focus to next input
                    focusManager.moveFocus(FocusDirection.Next)

                    // validate
                    when (textState.isEmpty()) {
                        true -> {
                            errorState = true
                            errorMessage = "$title should not be empty"
                        }
                        false -> {
                            errorState = false
                            errorMessage = ""
                        }
                    }
                }
            ),
            isError = errorState
        )

        if (errorState) {
            Text(
                errorMessage,
                style = AppTheme.typography.caption,
                color = AppTheme.colors.error,
                modifier = modifier.padding(
                    top = AppTheme.dimensions.paddingLarge,
                    start = AppTheme.dimensions.paddingXL,
                    end = AppTheme.dimensions.paddingLarge
                )
            )
        }
    }
}

@Stable
@Composable
fun OneTaskInputTextFieldWithoutHint(
    modifier: Modifier = Modifier,
    title: String,
    value: String,
    onValueChanged: (String) -> Unit
) {
    val focusManager = LocalFocusManager.current
    Column(horizontalAlignment = Alignment.Start, verticalArrangement = Arrangement.Center) {

        TextField(
            modifier = modifier
                .fillMaxWidth()
                .padding(
                    start = AppTheme.dimensions.paddingXL,
                    end = AppTheme.dimensions.paddingXL
                ),
            value = value,
            onValueChange = {
                onValueChanged(it)
            },

            label = { OneTaskLabelView(title = title) },
            textStyle = AppTheme.typography.body,
            colors = textFieldColors(),
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
            keyboardActions = KeyboardActions(
                onNext = {

                    // Focus to next input
                    focusManager.moveFocus(FocusDirection.Down)
                }
            )
        )
    }
}

@Composable
fun textFieldColors() = TextFieldDefaults.textFieldColors(
    textColor = AppTheme.colors.white,
    focusedLabelColor = Color.Transparent,
    focusedIndicatorColor = Color.Transparent,
    unfocusedIndicatorColor = Color.Transparent,
    disabledIndicatorColor = Color.Transparent,
    disabledTextColor = AppTheme.colors.white,
    cursorColor = AppTheme.colors.text,
    backgroundColor = AppTheme.colors.information,
    placeholderColor = AppTheme.colors.card,
    disabledPlaceholderColor = AppTheme.colors.card
)