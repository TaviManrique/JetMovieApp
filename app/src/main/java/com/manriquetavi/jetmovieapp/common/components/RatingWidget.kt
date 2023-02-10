package com.manriquetavi.jetmovieapp.common.components

import android.util.Log
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.clipPath
import androidx.compose.ui.graphics.drawscope.scale
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.graphics.vector.PathParser
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.manriquetavi.jetmovieapp.R
import com.manriquetavi.jetmovieapp.ui.theme.EXTRA_SMALL_PADDING
import com.manriquetavi.jetmovieapp.ui.theme.LightGray
import com.manriquetavi.jetmovieapp.ui.theme.StarColor

@Composable
fun RatingWidget(
    modifier: Modifier,
    rating: Double,
    scaleFactor: Float = 3f,
    spaceBetween: Dp = EXTRA_SMALL_PADDING
) {
    val result = calculatedStars(rating = rating)
    val starPathString = stringResource(id = R.string.start_path)
    val starPath = remember {
        PathParser().parsePathString(pathData = starPathString).toPath()
    }
    val starPathBounds = remember {
        starPath.getBounds()
    }

    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(spaceBetween)
    ) {
        result["filledStars"]?.let {
            repeat(it) {
                FilledStar(startPath = starPath, startPathBounds = starPathBounds, scaleFactor = scaleFactor)
            }
        }
        result["halfFilledStars"]?.let {
            repeat(it) {
                HalfFilledStar(startPath = starPath, startPathBounds = starPathBounds, scaleFactor = scaleFactor)
            }
        }
        result["emptyStars"]?.let {
            repeat(it) {
                EmptyStar(startPath = starPath, startPathBounds = starPathBounds, scaleFactor = scaleFactor)
            }
        }
    }
}

@Composable
fun FilledStar(
    startPath: Path,
    startPathBounds: Rect,
    scaleFactor: Float
) {
    Canvas(
        modifier = Modifier
            .size(24.dp)
            .semantics { contentDescription = "FilledStar" }
    ) {
        val canvasSize = size
        scale(scale = scaleFactor){
            val pathWidth = startPathBounds.width
            val pathHeight = startPathBounds.height
            val left = (canvasSize.width/2f) - (pathWidth/1.7f)
            val top = (canvasSize.height/2f) - (pathHeight/1.6f)
            translate(left = left, top = top) {
                drawPath(
                    path = startPath,
                    color = StarColor
                )
            }
        }
    }
}

@Composable
fun HalfFilledStar(
    startPath: Path,
    startPathBounds: Rect,
    scaleFactor: Float
) {
    Canvas(
        modifier = Modifier
            .size(24.dp)
            .semantics { contentDescription = "HalfFilledStar" }
    ) {
        val canvasSize = size
        scale(scale = scaleFactor){
            val pathWidth = startPathBounds.width
            val pathHeight = startPathBounds.height
            val left = (canvasSize.width/2f) - (pathWidth/1.7f)
            val top = (canvasSize.height/2f) - (pathHeight/1.6f)
            translate(left = left, top = top) {
                drawPath(
                    path = startPath,
                    color = LightGray.copy(alpha = 0.5f)
                )
                clipPath(path = startPath) {
                    drawRect(
                        color = StarColor,
                        size = Size(
                            width = startPathBounds.maxDimension/1.6f,
                            height = startPathBounds.maxDimension * scaleFactor
                        )
                    )
                }
            }
        }
    }
}

@Composable
fun EmptyStar(
    startPath: Path,
    startPathBounds: Rect,
    scaleFactor: Float
) {
    Canvas(
        modifier = Modifier
            .size(24.dp)
            .semantics { contentDescription = "EmptyStar" }
    ) {
        val canvasSize = size
        scale(scale = scaleFactor){
            val pathWidth = startPathBounds.width
            val pathHeight = startPathBounds.height
            val left = (canvasSize.width/2f) - (pathWidth/1.7f)
            val top = (canvasSize.height/2f) - (pathHeight/1.6f)
            translate(left = left, top = top) {
                drawPath(
                    path = startPath,
                    color = LightGray.copy(alpha = 0.5f)
                )
            }
        }
    }
}

@Composable
fun calculatedStars(rating: Double): Map<String, Int> {
    val maxStars by remember { mutableStateOf(5) }
    var filledStars by remember { mutableStateOf(0) }
    var halfFilledStars by remember { mutableStateOf(0) }
    var emptyStars by remember { mutableStateOf(0) }

    LaunchedEffect(key1 = rating) {
        val (firstNumber, lastNumber) = rating.toString().split(".").map { it.toInt() }
        if (firstNumber in 0..5 && lastNumber in 0..9) {
            filledStars = firstNumber
            if (lastNumber in 1..5) {
                halfFilledStars++
            }
            if (lastNumber in 6..9) {
                filledStars++
            }
            if (firstNumber == 5 && lastNumber > 0) {
                emptyStars = 5
                filledStars = 0
                halfFilledStars = 0
            }
        } else {
            Log.d("RatingWidget", "Invalid Rating Number.")
        }
    }
    emptyStars = maxStars - (filledStars + halfFilledStars)
    return mapOf(
        "filledStars" to filledStars,
        "halfFilledStars" to halfFilledStars,
        "emptyStars" to emptyStars
    )
}


@Preview
@Composable
fun FilledStarPreview() {
    RatingWidget(modifier = Modifier, rating = 3.0)
}