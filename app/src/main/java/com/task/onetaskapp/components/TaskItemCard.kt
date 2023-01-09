package com.task.onetaskapp.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.task.onetaskapp.R
import com.task.onetaskapp.model.task.Task
import com.task.onetaskapp.ui.theme.AppTheme
import com.task.onetaskapp.ui.theme.promt
import com.task.onetaskapp.utils.convertTimeStampToDate


@Composable
fun TaskItemCard(
    modifier: Modifier = Modifier,
    task: Task,
    onClick: () -> Unit,
    onCheckboxChange: (Boolean) -> Unit
) {

    Spacer(modifier = modifier.height(AppTheme.dimensions.paddingLarge))
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {
        // checkbox state
        val status = remember { mutableStateOf(task.isCompleted) }

        /**
         * Checkbox
         */
        IconButton(
            onClick = {
                status.value = !status.value
            }) {

            OneTaskCheckBox(
                value = status.value,
                onValueChanged = {
                    status.value = it
                    onCheckboxChange(status.value)
                }
            )

            val icon = if (status.value) {
                ImageVector.vectorResource(id = R.drawable.new_check)
            } else {
                ImageVector.vectorResource(id = R.drawable.ic_task_unchecked)
            }

            Icon(imageVector = icon, contentDescription = "", tint = AppTheme.colors.information)
        }

        Spacer(modifier = modifier.width(AppTheme.dimensions.paddingLarge))

        /**
         * Emoji + (title + category)
         */
        Row(
            modifier = modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(AppTheme.shapes.shapeXL))
                .background(AppTheme.colors.card)
                .clickable { onClick() },
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {

            /**
             * Emoji Text View
             */
//            EmojiTextView()
            Spacer(modifier = modifier.width(30.dp))
            /**
             * Title + category
             */
            Column(
                modifier = modifier
                    .align(Alignment.CenterVertically)
                    .padding(AppTheme.dimensions.paddingLarge),
            ) {
                Text(
                    text = task.title,
                    style = when (task.isCompleted) {
                        true -> TextStyle(
                            textDecoration = TextDecoration.LineThrough,
                            fontSize = 16.sp,
                            fontFamily = promt,
                            fontWeight = FontWeight.W600
                        )
                        false -> TextStyle(
                            textDecoration = TextDecoration.None,
                            fontSize = 16.sp,
                            fontFamily = promt,
                            fontWeight = FontWeight.W600
                        )
                    },
                    color = AppTheme.colors.white,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

                Spacer(modifier = modifier.height(AppTheme.dimensions.paddingLarge))
                Text(
                    text = task.category,
                    style = when (task.isCompleted) {
                        true -> TextStyle(
                            textDecoration = TextDecoration.LineThrough,
                            fontSize = 16.sp,
                            fontFamily = promt,
                            fontWeight = FontWeight.W400
                        )
                        false -> TextStyle(
                            textDecoration = TextDecoration.None,
                            fontSize = 16.sp,
                            fontFamily = promt,
                            fontWeight = FontWeight.W400
                        )
                    },
                    color = AppTheme.colors.white
                )
                Spacer(modifier = modifier.height(AppTheme.dimensions.paddingLarge))
                Text(
                    text = "${stringResource(id = R.string.text_task_created)} ${
                        convertTimeStampToDate(
                            task.createdAt
                        )
                    }",
                    style = when (task.isCompleted) {
                        true -> TextStyle(
                            textDecoration = TextDecoration.LineThrough,
                            fontSize = 12.sp,
                            fontFamily = promt,
                            fontWeight = FontWeight.W400
                        )
                        false -> TextStyle(
                            textDecoration = TextDecoration.None,
                            fontSize = 12.sp,
                            fontFamily = promt,
                            fontWeight = FontWeight.W400
                        )
                    },
                    color = AppTheme.colors.white
                )
            }
        }
    }
}

@Composable
fun EmojiTextView(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .size(80.dp)
            .padding(AppTheme.dimensions.paddingLarge)
            .clip(CircleShape)
            .clickable { }
            .background(AppTheme.colors.background)
    ) {
    }
}

@Composable
fun OneTaskCheckBox(value: Boolean, onValueChanged: (Boolean) -> Unit) {
    val alpha = if (value) 1f else 0f
    val background = if (value) AppTheme.colors.white else Color.Transparent
    Box(
        modifier = Modifier
            .size(30.dp)
            .background(background)
            .alpha(0f)
    ) {
        Checkbox(
            checked = value,
            onCheckedChange = {
                onValueChanged(it)
            },
            colors = CheckboxDefaults.colors(
                uncheckedColor = AppTheme.colors.white,
                checkedColor = AppTheme.colors.white,
                checkmarkColor = AppTheme.colors.white
            ),
            modifier = Modifier
                .size(90.dp)
                .alpha(alpha)

        )
    }

}


