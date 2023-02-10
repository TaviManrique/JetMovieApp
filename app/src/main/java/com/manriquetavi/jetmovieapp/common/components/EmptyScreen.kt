package com.manriquetavi.jetmovieapp.common.components

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import com.google.android.material.divider.MaterialDividerItemDecoration
import com.manriquetavi.jetmovieapp.R
import com.manriquetavi.jetmovieapp.ui.theme.NETWORK_ERROR_ICON_HEIGHT
import com.manriquetavi.jetmovieapp.ui.theme.SMALL_PADDING
import com.manriquetavi.jetmovieapp.ui.theme.iconNetwork

@Composable
fun EmptyScreen(
    message: String
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(
            modifier = Modifier
                .size(NETWORK_ERROR_ICON_HEIGHT),
            painter = painterResource(R.drawable.ic_network_error),
            contentDescription = "Error Icon",
            tint = MaterialTheme.colors.iconNetwork
        )
        Text(
            modifier = Modifier
                .padding(top = SMALL_PADDING),
            text = message,
            color = MaterialTheme.colors.iconNetwork,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Medium,
            fontSize = MaterialTheme.typography.subtitle1.fontSize
        )
    }
}