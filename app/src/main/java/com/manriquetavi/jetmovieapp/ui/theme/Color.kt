package com.manriquetavi.jetmovieapp.ui.theme

import androidx.compose.material.Colors
import androidx.compose.ui.graphics.Color


val Purple200 = Color(0xFFBB86FC)
val Purple500 = Color(0xFF6200EE)
val Purple700 = Color(0xFF3700B3)
val Teal200 = Color(0xFF03DAC5)

val LightGray = Color(0xFFD8D8D8)
val DarkGray = Color(0xFF2A2A2A)
val StarColor = Color(0xFFFFC94D)



val Colors.backgroundColor
    get() = if(isLight) Color.White else Color.Black

val Colors.titleColor
    get() = if(isLight) DarkGray else Color.LightGray

val Colors.descriptionColor
    get() = if(isLight) DarkGray.copy(alpha = 0.5f) else LightGray.copy(alpha = 0.5f)

val Colors.activateIndicatorColor
    get() = if(isLight) Purple500 else Purple700

val Colors.inactivateIndicatorColor
    get() = if(isLight) LightGray else DarkGray

val Colors.iconScreenInformation
    get() = if(isLight) LightGray else DarkGray

val Colors.buttonBackgroundColor
    get() = if(isLight) Purple500 else Purple700

val Colors.topAppBarContentColor: Color
    get() = if(isLight) Color.White else LightGray

val Colors.topAppBarBackgroundColor: Color
    get() = if(isLight) Purple500 else Color.Black