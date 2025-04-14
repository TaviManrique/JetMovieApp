package com.tavimanrique.domain.util

import com.tavimanrique.domain.usecases.PasswordResult

object PasswordResultParser {
    fun parseErrorMessage(error: PasswordResult): String? {
        return when (error) {
            PasswordResult.VALID -> null
            PasswordResult.INVALID_LOWERCASE -> "The password must have at least 1 lowercase character"
            PasswordResult.INVALID_UPPERCASE -> "The password must have at least 1 uppercase character"
            PasswordResult.INVALID_DIGITS -> "The password must have at least 1 number"
            PasswordResult.INVALID_LENGTH -> "The password must have at least 8 characters"
        }
    }
}