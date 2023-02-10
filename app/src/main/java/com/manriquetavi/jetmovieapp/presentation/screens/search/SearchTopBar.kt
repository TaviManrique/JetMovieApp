package com.manriquetavi.jetmovieapp.presentation.screens.search

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.manriquetavi.jetmovieapp.common.core.cleanText
import com.manriquetavi.jetmovieapp.ui.theme.JetMovieAppTheme
import com.manriquetavi.jetmovieapp.ui.theme.topAppBarBackgroundColor
import com.manriquetavi.jetmovieapp.ui.theme.topAppBarContentColor

@Composable
fun SearchTopBar(
    text: String,
    onTextChange: (String) -> Unit,
    searchMovies: (String) -> Unit,
    navigateToHome: () -> Unit
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp),
        elevation = AppBarDefaults.TopAppBarElevation,
        color = MaterialTheme.colors.topAppBarBackgroundColor
    ) {
        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = text,
            onValueChange = { onTextChange(it) },
            placeholder = {
                Text(
                    modifier = Modifier.alpha(alpha = ContentAlpha.medium),
                    text = "Search your favourite movie...",
                    color = Color.White
                )
            },
            textStyle = TextStyle(
                color = MaterialTheme.colors.topAppBarContentColor
            ),
            singleLine = true,
            leadingIcon = {
                IconButton(
                    onClick = { navigateToHome() }
                ) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "ArrowBack Icon",
                        tint = MaterialTheme.colors.topAppBarContentColor
                    )
                }
            },
            trailingIcon = {
                IconButton(
                    onClick = {
                        if (text.isNotEmpty()){
                            onTextChange("")
                        }
                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = "Close Icon",
                        tint = MaterialTheme.colors.topAppBarContentColor
                    )
                }
            },
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Search
            ),
            keyboardActions = KeyboardActions(
                onSearch = {
                    searchMovies(cleanText(text))
                }
            ),
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color.Transparent,
                cursorColor = MaterialTheme.colors.topAppBarContentColor
            )
        )
    }
}

@Preview
@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
fun SearchTopBarPreview() {
    JetMovieAppTheme {
        Surface {
            SearchTopBar(
                text = "",
                onTextChange = {},
                searchMovies = {},
                navigateToHome = {}
            )
        }
    }
}