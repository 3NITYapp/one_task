package com.helec.eisec.view.task

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.task.onetaskapp.R
import com.task.onetaskapp.components.TaskItemCard
import com.task.onetaskapp.model.task.Task
import com.task.onetaskapp.ui.theme.AppTheme
import com.task.onetaskapp.utils.MainActions
import com.task.onetaskapp.utils.viewstate.ViewState
import com.task.onetaskapp.view.animationviewstate.AnimationViewState
import com.task.onetaskapp.view.animationviewstate.ScreenState
import com.task.onetaskapp.viewmodel.MainViewModel

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun AllTaskScreen(
    modifier: Modifier,
    viewModel: MainViewModel,
    actions: MainActions,
    defaultUrgency: Int = 0,
    defaultImportance: Int = 0
) {

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(id = R.string.text_allTask),
                        style = AppTheme.typography.merriweathersans24spW800,
                        textAlign = TextAlign.Start,
                        color = AppTheme.colors.text,
                        modifier = modifier.padding(start = 16.dp),
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { actions.upPress.invoke() }) {
                        Icon(
                            modifier = Modifier.size(20.dp),
                            imageVector = ImageVector.vectorResource(id = R.drawable.ic_back),
                            contentDescription = stringResource(R.string.back_button),
                            tint = AppTheme.colors.text
                        )
                    }
                },
                backgroundColor = AppTheme.colors.background, elevation = 0.dp
            )
        },
        modifier = modifier.background(AppTheme.colors.background),

        ) {

        when (val result = viewModel.feed.collectAsState().value) {
            ViewState.Loading -> {
                AnimationViewState(
                    modifier,
                    title = stringResource(R.string.text_no_task_title),
                    description = stringResource(R.string.text_no_task_description),
                    callToAction = stringResource(R.string.text_add_a_task),
                    ScreenState.LOADING,
                    actions = {
                        actions.gotoAddTask.invoke(defaultUrgency, defaultImportance)
                    }
                )
            }
            ViewState.Empty -> {
                AnimationViewState(
                    modifier,
                    title = stringResource(R.string.text_no_task_title),
                    description = stringResource(R.string.text_no_task_description),
                    callToAction = stringResource(R.string.text_add_a_task),
                    ScreenState.EMPTY,
                    actions = {
                        actions.gotoAddTask.invoke(defaultUrgency, defaultImportance)
                    }
                )
            }
            is ViewState.Success -> {
                LazyColumn(
                    contentPadding = PaddingValues(
                        start = 16.dp,
                        top = 16.dp,
                        end = 16.dp
                    ),
                    modifier = modifier
                        .fillMaxSize()
                        .background(AppTheme.colors.background),
                ) {
                    itemsIndexed(result.task) { _: Int, item: Task ->
                        TaskItemCard(
                            modifier,
                            item,
                            onClick = {
                                actions.gotoTaskDetails(item.id)
                            },
                            onCheckboxChange = {
                                viewModel.updateStatus(item.id, it)
                            }
                        )
                    }
                }
                Box(modifier = Modifier.fillMaxSize()) {
                    Button(
                        onClick = { actions.gotoAddTask.invoke(0, 0) },
                        modifier = Modifier
                            .padding(bottom = 90.dp, end = 16.dp)
                            .align(Alignment.BottomEnd),
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
            }
            is ViewState.Error -> {
                AnimationViewState(
                    modifier,
                    title = stringResource(R.string.text_error_title),
                    description = stringResource(
                        R.string.text_error_description
                    ),
                    callToAction = stringResource(R.string.text_add_a_task),
                    ScreenState.ERROR,
                    actions = {
                        actions.gotoAddTask.invoke(defaultUrgency, defaultImportance)
                    }
                )
            }
        }
    }
}
