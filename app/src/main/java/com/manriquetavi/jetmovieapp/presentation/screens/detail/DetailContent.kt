package com.manriquetavi.jetmovieapp.presentation.screens.detail

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.google.firebase.firestore.GeoPoint
import com.manriquetavi.jetmovieapp.R
import com.manriquetavi.jetmovieapp.common.core.capitalizeWords
import com.manriquetavi.jetmovieapp.domain.model.Movie
import com.manriquetavi.jetmovieapp.ui.theme.*

@Composable
fun DetailContent(
    movie: Movie,
    paddingValues: PaddingValues
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AsyncImage(
            modifier = Modifier
                .height(360.dp)
                .fillMaxWidth(0.6f),
            model = ImageRequest
                .Builder(LocalContext.current)
                .data("${movie.image}")
                .build(),
            contentDescription = "Movie Image",
            contentScale = ContentScale.Crop,
            placeholder = painterResource(R.drawable.ic_placeholder),
            error = painterResource(R.drawable.ic_placeholder)
        )
        Spacer(modifier = Modifier.height(SMALL_PADDING))
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = SMALL_PADDING),
            text = movie.title.toString().capitalizeWords(),
            textAlign = TextAlign.Center,
            color = MaterialTheme.colors.titleColor,
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.h5
        )
        Spacer(modifier = Modifier.height(SMALL_PADDING))
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = EXTRA_LARGE_PADDING),
            text = "Director: ${movie.director.toString()}",
            textAlign = TextAlign.Center,
            color = MaterialTheme.colors.descriptionColor,
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.subtitle1
        )
        Spacer(modifier = Modifier.height(SMALL_PADDING))
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = EXTRA_LARGE_PADDING),
            text = movie.description.toString(),
            textAlign = TextAlign.Center,
            color = MaterialTheme.colors.descriptionColor,
            fontWeight = FontWeight.Medium,
            style = MaterialTheme.typography.subtitle2
        )
    }

}

@Preview
@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
fun DetailContentPreview() {
    JetMovieAppTheme {
        Surface {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                DetailContent(
                    movie = Movie(
                        title = "better call saul",
                        description = "The best series of the world",
                        image = "https://media.revistagq.com/photos/62fe075f5ab63dcb237f86de/16:9/w_2560%2Cc_limit/better-call-saul.jpeg",
                        stars = "4.5",
                        director = "Peter Gould",
                        geoPoint = GeoPoint(10.3, 15.0)
                    ),
                    paddingValues = PaddingValues()
                )
            }
        }
    }
}