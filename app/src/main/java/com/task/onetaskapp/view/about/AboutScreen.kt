package com.task.onetaskapp.view.about

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.task.onetaskapp.BuildConfig
import com.task.onetaskapp.R
import com.task.onetaskapp.ui.theme.AppTheme
import com.task.onetaskapp.utils.MainActions
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun AboutScreen(modifier: Modifier, actions: MainActions) {

    // remember current URL
    var url by remember {
        mutableStateOf("")
    }
    var title by remember {
        mutableStateOf("")
    }

    val scaffoldState = rememberScaffoldState()


    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(id = R.string.text_about),
                        style = AppTheme.typography.merriweathersans24spW800,
                        textAlign = TextAlign.Start,
                        color = AppTheme.colors.text,
                        modifier = modifier.padding(start = AppTheme.dimensions.paddingXL)
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
        modifier = modifier
            .fillMaxSize()
            .background(AppTheme.colors.background)
    ) {
        val listState = rememberLazyListState()
        LazyColumn(
            state = listState,
            contentPadding = PaddingValues(AppTheme.dimensions.paddingXL),
            modifier = modifier
                .fillMaxSize()
                .background(
                    AppTheme.colors.background
                )
        ) {

            item {

                Box(modifier = modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                    Image(
                        painter = painterResource(id = R.drawable.onetask_about_logo),
                        contentDescription = stringResource(
                            R.string.text_einsen_logo
                        ),
                        modifier = modifier.size(82.dp)
                    )
                }
            }

            item {
                Spacer(modifier = modifier.height(AppTheme.dimensions.paddingXXL))
                val (version, code) = getVersionCodeAndName()
                TitleAndDescription(
                    modifier,
                    stringResource(R.string.app_name),
                    version.plus("($code)")
                )
            }

            item {
                Spacer(modifier = modifier.height(AppTheme.dimensions.paddingXXL))
                TitleAndDescription(
                    title = stringResource(R.string.text_attribution_and_license),
                    description = stringResource(
                        R.string.text_license
                    )
                )
            }

            item {
                url = stringResource(id = R.string.text_repo_link)
                title = stringResource(id = R.string.text_visit)

                Spacer(modifier = modifier.height(AppTheme.dimensions.paddingXXL))
                TitleAndURL(
                    title = stringResource(R.string.text_visit),
                    url = url,
                    onClick = {
                        val encodedUrl = URLEncoder.encode(url, StandardCharsets.UTF_8.toString())
                        actions.gotoWebView(title, encodedUrl)
                    }
                )
            }
        }
    }
}

fun getVersionCodeAndName(): Pair<String, String> {
    return Pair(BuildConfig.VERSION_NAME, BuildConfig.VERSION_CODE.toString())
}

@Composable
fun TitleAndDescription(modifier: Modifier = Modifier, title: String, description: String) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
        Text(
            text = title,
            style = AppTheme.typography.merriweathersans16spW700,
            color = AppTheme.colors.text,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = modifier.height(AppTheme.dimensions.paddingMedium))
        CompositionLocalProvider(values = arrayOf(LocalContentAlpha provides ContentAlpha.disabled)) {
            Text(
                text = description,
                style = AppTheme.typography.promt14spW400,
                color = AppTheme.colors.text
            )
        }
    }
}

@Composable
fun TitleAndURL(modifier: Modifier = Modifier, title: String, url: String, onClick: () -> Unit) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
        Text(
            text = title,
            style = AppTheme.typography.merriweathersans16spW700,
            color = AppTheme.colors.text,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = modifier.height(AppTheme.dimensions.paddingMedium))
        CompositionLocalProvider(values = arrayOf(LocalContentAlpha provides ContentAlpha.disabled)) {
            Text(
                text = AnnotatedString(
                    text = url,
                    spanStyle = SpanStyle(
                        color = AppTheme.colors.information,
                        fontFamily = FontFamily(
                            Font(R.font.prompt_regular, FontWeight.Medium)
                        ),
                        fontSize = 14.sp,
                        textDecoration = TextDecoration.Underline
                    )
                ),
                modifier = modifier.clickable(
                    onClick = {
                        onClick()
                    }
                )
            )
        }
    }
}
