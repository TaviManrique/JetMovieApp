package com.manriquetavi.jetmovieapp.presentation.screens.map

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState

@Composable
fun MapScreen(
    latitude: String?,
    longitude: String?
) {
    var locationMovie = LatLng(0.0, 0.0)
    latitude?.let {
        longitude?.let {
            locationMovie = LatLng(latitude.toDouble(), longitude.toDouble())
        }
    }
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(locationMovie, 3f)
    }
    GoogleMap(
        modifier = Modifier.fillMaxSize(),
        cameraPositionState = cameraPositionState
    ) {
        Marker(
            state = MarkerState(position = locationMovie),
            title = "Movie Location"
        )
    }
}