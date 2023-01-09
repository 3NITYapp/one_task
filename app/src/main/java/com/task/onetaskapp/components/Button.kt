package com.task.onetaskapp.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.task.onetaskapp.ui.theme.AppTheme

@Composable
fun PrimaryButton(modifier: Modifier = Modifier, title: String, onclick: () -> Unit) {

    Button(
        modifier = modifier
            .fillMaxWidth()
            .height(54.dp)
            .clip(RoundedCornerShape(60.dp))
            .padding(start = AppTheme.dimensions.paddingXL, end = AppTheme.dimensions.paddingXL),
        contentPadding = PaddingValues(bottom = 5.dp),
        onClick = { onclick() },
        colors = ButtonDefaults.buttonColors(
            backgroundColor = AppTheme.colors.primary,
            contentColor = AppTheme.colors.white
        ),
        shape = RoundedCornerShape(AppTheme.shapes.shapeLarge)
    ) {
        Text(
            text = title,
            style = AppTheme.typography.merriweathersans16spW700,
            textAlign = TextAlign.Center,
            color = AppTheme.colors.white
        )
    }
}

@Composable
fun PrimaryButtonWithIcon(
    modifier: Modifier = Modifier,
    title: String,
    icon: ImageVector,
    onclick: () -> Unit,
    color: Color
) {
    Box(
        Modifier
            .clickable(enabled = true,
                onClickLabel = "",
                onClick = onclick,
                role = null,
                indication = null,
                interactionSource = remember { MutableInteractionSource() })
            .clip(RoundedCornerShape(65.dp)),
    ) {
        Row(
            modifier = Modifier
                .background(color)
                .padding(start = 16.dp, end = 16.dp, top = 10.dp, bottom = 10.dp)
        ) {
            Text(
                text = title,
                style = AppTheme.typography.promt12spW400,
                textAlign = TextAlign.Center,
                color = Color.White
            )

            Spacer(modifier = modifier.width(AppTheme.dimensions.paddingSmall))

            Icon(
                imageVector = icon,
                contentDescription = title,
                tint = Color.White
            )
        }
    }
}

