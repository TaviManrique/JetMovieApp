package com.manriquetavi.jetmovieapp.presentation.screens.home

import android.content.res.Configuration.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.manriquetavi.jetmovieapp.ui.theme.JetMovieAppTheme
import com.manriquetavi.jetmovieapp.ui.theme.topAppBarBackgroundColor
import com.manriquetavi.jetmovieapp.ui.theme.topAppBarContentColor

@Composable
fun HomeTopBar(
    onSearchClicked: () -> Unit
) {
    TopAppBar(
        title = {
            Text(
                text = "JetMovieApp",
                color = MaterialTheme.colors.topAppBarContentColor
            )
        },
        backgroundColor = MaterialTheme.colors.topAppBarBackgroundColor,
        actions = {
            IconButton(onClick = onSearchClicked) {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Search Icon"
                )
            }
        }
    )
}

@Preview
@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
fun HomeTopBarPreviewNight() {
    JetMovieAppTheme {
        Surface {
            HomeTopBar {

            }
        }
    }
}
