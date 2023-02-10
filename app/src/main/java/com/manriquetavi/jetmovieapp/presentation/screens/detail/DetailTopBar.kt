package com.manriquetavi.jetmovieapp.presentation.screens.detail

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.runtime.Composable
import com.manriquetavi.jetmovieapp.ui.theme.topAppBarBackgroundColor
import com.manriquetavi.jetmovieapp.ui.theme.topAppBarContentColor

@Composable
fun DetailTopBar(
    latitude: Double?,
    longitude: Double?,
    navigateToMap: (String, String) -> Unit,
    navigateBack: () -> Unit
) {
    TopAppBar(
        title = {
            Text(
                text = "Detail",
                color = MaterialTheme.colors.topAppBarContentColor
            )
        },
        backgroundColor = MaterialTheme.colors.topAppBarBackgroundColor,
        navigationIcon = {
            IconButton(
                onClick = { navigateBack() }
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "ArrowBack Icon",
                    tint = MaterialTheme.colors.topAppBarContentColor
                )
            }
        },
        actions = {
            IconButton(
                onClick = {
                    if (latitude != null && longitude != null) {
                        navigateToMap.invoke(latitude.toString(),longitude.toString())
                    }
                }
            ) {
                Icon(
                    imageVector = Icons.Default.LocationOn,
                    contentDescription = "Location Icon"
                )
            }
        }
    )
}