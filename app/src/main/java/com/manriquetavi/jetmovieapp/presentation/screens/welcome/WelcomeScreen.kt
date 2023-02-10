package com.manriquetavi.jetmovieapp.presentation.screens.welcome

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import com.google.accompanist.pager.*
import com.manriquetavi.jetmovieapp.ui.theme.*

@ExperimentalPagerApi
@Composable
fun WelcomeScreen(
    navigateToHome: () -> Unit,
    saveOnBoardingPageState: () -> Unit
) {
    val pagerState = rememberPagerState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.backgroundColor),
    ) {
        HorizontalPager(
            modifier = Modifier.weight(10f),
            count = onBoardingPages.size,
            state = pagerState
        ) { position ->
            PagerScreen(onBoardingPage = onBoardingPages[position])
        }
        HorizontalPagerIndicator(
            modifier = Modifier
                .weight(1f)
                .align(Alignment.CenterHorizontally),
            pagerState = pagerState,
            activeColor = MaterialTheme.colors.activateIndicatorColor,
            inactiveColor = MaterialTheme.colors.inactivateIndicatorColor,
            indicatorWidth = PAGING_INDICATOR_WIDTH,
            spacing = PAGING_INDICATOR_SPACING
        )
        FinishButton(
            modifier = Modifier.weight(2f),
            pagerState = pagerState,
            pageCount = onBoardingPages.size,
            navigateToAuth = navigateToHome,
            saveOnBoardingPageState = saveOnBoardingPageState
        )
    }
}

@Composable
fun PagerScreen(
    onBoardingPage: OnBoardingPage
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            modifier = Modifier
                .fillMaxWidth(0.5f)
                .fillMaxHeight(0.7f),
            painter = painterResource(id = onBoardingPage.image),
            contentDescription = "OnBoading Image"
        )
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = SMALL_PADDING),
            text = onBoardingPage.title,
            textAlign = TextAlign.Center,
            color = MaterialTheme.colors.titleColor,
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.h4
        )
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = EXTRA_LARGE_PADDING)
                .padding(top = SMALL_PADDING),
            text = onBoardingPage.description,
            textAlign = TextAlign.Center,
            color = MaterialTheme.colors.descriptionColor,
            fontWeight = FontWeight.Medium,
            style = MaterialTheme.typography.subtitle1
        )
    }
}

@ExperimentalPagerApi
@Composable
fun FinishButton(
    modifier: Modifier,
    pagerState: PagerState,
    pageCount: Int,
    navigateToAuth: () -> Unit,
    saveOnBoardingPageState: () -> Unit
) {
    Row(
        modifier = modifier
            .padding(horizontal = EXTRA_LARGE_PADDING),
        verticalAlignment = Alignment.Top,
        horizontalArrangement = Arrangement.Center
    ) {
        AnimatedVisibility(
            modifier = Modifier.fillMaxWidth(),
            visible = pagerState.currentPage == pageCount - 1
        ) {
            Button(
                modifier = Modifier.fillMaxHeight(0.4f),
                onClick = {
                    navigateToAuth.invoke()
                    saveOnBoardingPageState.invoke()
                },
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = MaterialTheme.colors.buttonBackgroundColor,
                    contentColor = Color.White
                ),
                shape = RoundedCornerShape(BUTTON_SHAPE)
            ) {
                Text(
                    text = "Finish"
                )
            }
        }
    }
}