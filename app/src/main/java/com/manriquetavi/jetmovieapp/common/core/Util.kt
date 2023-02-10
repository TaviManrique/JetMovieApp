package com.manriquetavi.jetmovieapp.common.core


fun cleanText(text: String): String {
    return text.trim().lowercase()
}

fun String.capitalizeWords(): String {
    return this.split("\\s+".toRegex()).joinToString(" ") { it.capitalize() }
}