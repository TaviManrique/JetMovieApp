package com.manriquetavi.jetmovieapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.pager.ExperimentalPagerApi
import com.manriquetavi.jetmovieapp.navigation.SetUpNavGraph
import com.manriquetavi.jetmovieapp.presentation.screens.splash.SplashViewModel
import com.manriquetavi.jetmovieapp.ui.theme.JetMovieAppTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@ExperimentalPagerApi
@ExperimentalFoundationApi
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var splashViewModel: SplashViewModel
    private lateinit var navController: NavHostController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen().setKeepOnScreenCondition {
            !splashViewModel.isLoading
        }
        setContent {
            JetMovieAppTheme {
                navController = rememberNavController()
                val startDestination = splashViewModel.startDestination
                SetUpNavGraph(
                    navController = navController,
                    startDestination = startDestination
                )
            }
        }
    }
}
