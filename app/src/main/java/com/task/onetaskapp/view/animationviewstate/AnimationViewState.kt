package com.task.onetaskapp.view.animationviewstate

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.task.onetaskapp.R
import com.task.onetaskapp.ui.theme.AppTheme


@Composable
fun AnimationViewState(
    modifier: Modifier = Modifier,
    title: String,
    description: String,
    callToAction: String,
    screenState: ScreenState,
    actions: () -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(AppTheme.colors.background)
            .wrapContentSize(Alignment.Center),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        when (screenState) {
            ScreenState.ERROR -> {
                LottieAnimationPlaceHolder(
                    modifier,
                    title,
                    description,
                    callToAction,
                    actions,
                    R.raw.error_state
                )
            }
            ScreenState.EMPTY -> {
                LottieAnimationPlaceHolder(
                    modifier,
                    title,
                    description,
                    callToAction,
                    actions,
                    R.raw.new_empty_state
                )
            }
            ScreenState.LOADING -> {
                LottieAnimation(modifier, lottie = R.raw.loading_state)
            }
        }
    }
}

@Composable
fun LottieAnimationPlaceHolder(
    modifier: Modifier,
    title: String,
    description: String,
    callToAction: String,
    actions: () -> Unit,
    lottie: Int,
) {

    // lottie animation
    LottieAnimation(modifier, lottie)

    // title, description & CTA button
    Text(
        text = title,
        modifier = modifier.fillMaxWidth(),
        style = AppTheme.typography.merriweathersans20spW700,
        textAlign = TextAlign.Center,
        color = AppTheme.colors.text
    )
    Spacer(modifier = modifier.height(AppTheme.dimensions.paddingMedium))
    Text(
        text = description,
        modifier = modifier
            .fillMaxWidth()
            .padding(
                start = AppTheme.dimensions.paddingXXL,
                end = AppTheme.dimensions.paddingXXL
            ),
        style = AppTheme.typography.promt14spW300,
        maxLines = 3,
        textAlign = TextAlign.Center,
        color = AppTheme.colors.text.copy(.7F)
    )
    Spacer(modifier = modifier.height(AppTheme.dimensions.paddingXXL))
    Button(
        onClick = { actions() },
        modifier = Modifier.padding(bottom = 81.dp),
        shape = RoundedCornerShape(AppTheme.shapes.shapeLarge),
        contentPadding = PaddingValues(
            start = 22.dp,
            top = 16.dp,
            end = 22.dp,
            bottom = 16.dp
        ),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = AppTheme.colors.primary,
            contentColor = AppTheme.colors.white
        )
    ) {
        Text(
            text = stringResource(id = R.string.add_tack_dash_button),
            style = AppTheme.typography.merriweathersans16spW800
        )
    }
}

@Composable
fun LottieAnimation(modifier: Modifier, lottie: Int) {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(lottie))
    val progress by animateLottieCompositionAsState(
        composition,
        iterations = LottieConstants.IterateForever,
    )
    com.airbnb.lottie.compose.LottieAnimation(
        modifier = modifier.size(300.dp),
        composition = composition,
        progress = progress
    )
}

enum class ScreenState {
    ERROR,
    EMPTY,
    LOADING
}