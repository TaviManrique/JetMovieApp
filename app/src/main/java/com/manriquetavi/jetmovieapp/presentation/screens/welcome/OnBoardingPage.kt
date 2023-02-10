package com.manriquetavi.jetmovieapp.presentation.screens.welcome

import androidx.annotation.DrawableRes
import com.manriquetavi.jetmovieapp.R

sealed class OnBoardingPage(
    @DrawableRes
    val image: Int,
    val title: String,
    val description: String
) {
    object First: OnBoardingPage(
        image = R.drawable.first,
        title = "Welcome",
        description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod."
    )

    object Second: OnBoardingPage(
        image = R.drawable.second,
        title = "Explore",
        description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod."
    )

    object Third: OnBoardingPage(
        image = R.drawable.third,
        title = "Enjoy",
        description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod. Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod."
    )
}

val onBoardingPages = listOf(
    OnBoardingPage.First,
    OnBoardingPage.Second,
    OnBoardingPage.Third
)

