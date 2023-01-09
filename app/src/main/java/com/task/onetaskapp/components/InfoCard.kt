package com.task.onetaskapp.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.task.onetaskapp.R
import com.task.onetaskapp.ui.theme.AppTheme


@Composable
fun InfoCard(title: String, value: String, modifier: Modifier) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(AppTheme.shapes.shapeLarge))
            .background(color = AppTheme.colors.card)
            .padding(
                AppTheme.dimensions.paddingLarge
            ),
        contentAlignment = Alignment.Center
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.wrapContentWidth()
        ) {

            // concat string: Result : 4.5/5.0
            val builder = StringBuilder()
            builder.append(value)
                .append("/")
                .append(stringResource(R.string.text_four))

            Text(
                text = builder.toString(),
                modifier = Modifier.fillMaxWidth(),
                color = AppTheme.colors.white,
                style = AppTheme.typography.promt14spW600,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(AppTheme.dimensions.paddingSmall))

            Text(
                text = title.uppercase(),
                modifier = Modifier.fillMaxWidth(),
                color = AppTheme.colors.white,
                style = AppTheme.typography.promt12spW400,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Preview
@Composable
fun InfoCardPrev() {
    InfoCard(title = "", value = "", modifier = Modifier)
}
