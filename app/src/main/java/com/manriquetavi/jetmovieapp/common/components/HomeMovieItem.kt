package com.manriquetavi.jetmovieapp.common.components

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ContentAlpha
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.google.firebase.firestore.GeoPoint
import com.manriquetavi.jetmovieapp.domain.model.Movie
import com.manriquetavi.jetmovieapp.R
import com.manriquetavi.jetmovieapp.ui.theme.*

@Composable
fun HomeMovieItem(
    movie: Movie,
    navigateToSearch: () -> Unit
) {
    Box(
        modifier = Modifier
            .height(MOVIE_ITEM_HEIGHT)
            .clickable { navigateToSearch() },
        contentAlignment = Alignment.BottomStart
    ) {
        Surface(
            shape = RoundedCornerShape(LARGE_PADDING)
        ) {
            AsyncImage(
                modifier = Modifier.fillMaxSize(),
                model = ImageRequest
                    .Builder(LocalContext.current)
                    .data("${movie.image}")
                    .build(),
                contentDescription = "Movie Image",
                contentScale = ContentScale.Crop,
                placeholder = painterResource(R.drawable.ic_placeholder),
                error = painterResource(R.drawable.ic_placeholder)
            )
        }
        Surface(
            modifier = Modifier
                .fillMaxHeight(0.35f)
                .fillMaxWidth(),
            color = Color.Black.copy(alpha = ContentAlpha.medium),
            shape = RoundedCornerShape(bottomStart = LARGE_PADDING, bottomEnd = LARGE_PADDING)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(all = MEDIUM_PADDING)
            ) {
                Text(
                    text = movie.title.toString(),
                    color = MaterialTheme.colors.topAppBarContentColor,
                    fontSize = MaterialTheme.typography.h5.fontSize,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = movie.description.toString(),
                    color = MaterialTheme.colors.topAppBarContentColor.copy(alpha = ContentAlpha.medium),
                    fontSize = MaterialTheme.typography.subtitle1.fontSize,
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis
                )
                Row(
                    modifier = Modifier.padding(top = SMALL_PADDING),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RatingWidget(
                        modifier = Modifier.padding(end = SMALL_PADDING),
                        rating = movie.stars?.toDouble() ?: 0.0
                    )
                    Text(
                        text = "(${movie.stars})",
                        textAlign = TextAlign.Center,
                        color = Color.White.copy(alpha = ContentAlpha.medium)
                    )
                }
            }
        }
    }
}

@Preview
@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
fun HomeMovieItemPreview() {
    JetMovieAppTheme {
        Surface {
            HomeMovieItem(
                movie = Movie(
                    title = "Parasite",
                    description = "Both Gi Taek and his family are out of work. When his eldest son, Gi Woo, begins teaching private lessons at the wealthy Park home, the two families, who have much in common despite being from two completely different worlds, strike up a relationship with unpredictable results.",
                    image = "https://pics.filmaffinity.com/Par_sitos-406070218-large.jpg",
                    stars = "4.5",
                    director = "Bong Joon-ho",
                    geoPoint = GeoPoint(10.3, 15.0)
                ),
                navigateToSearch = { }
            )
        }
    }
}