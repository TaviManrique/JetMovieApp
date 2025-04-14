package com.tavimanrique.jetmovieapp.usecase

import com.tavimanrique.domain.usecases.PasswordResult
import com.tavimanrique.domain.usecases.ValidatePasswordUseCase
import junit.framework.TestCase.assertEquals
import org.junit.Before
import org.junit.Test

class ValidatePasswordUseCaseTest {
    private lateinit var validatePasswordUseCase: ValidatePasswordUseCase

    @Before
    fun setup() {
        validatePasswordUseCase =
            ValidatePasswordUseCase()
    }

    @Test
    fun `given low character password, return invalid password`() {
        val input = "1234"
        val result = validatePasswordUseCase(input)

        assertEquals(
            PasswordResult.INVALID_LENGTH,
            result
        )
    }

    @Test
    fun `given no lowercase password, return invalid password`() {
        val input = "FGFGFGFGGF"
        val result = validatePasswordUseCase(input)

        assertEquals(
            PasswordResult.INVALID_LOWERCASE,
            result
        )
    }

    @Test
    fun `given no uppercase password, return invalid password`() {
        val input = "asdfghjkl"
        val result = validatePasswordUseCase(input)

        assertEquals(
            PasswordResult.INVALID_UPPERCASE,
            result
        )
    }

    @Test
    fun `given no numbered password, return invalid password`() {
        val input = "asdfgFHJKGHJK"
        val result = validatePasswordUseCase(input)

        assertEquals(
            PasswordResult.INVALID_DIGITS,
            result
        )
    }

    @Test
    fun `given valid password, return valid`() {
        val input = "asdASD123"
        val result = validatePasswordUseCase(input)

        assertEquals(
            PasswordResult.VALID,
            result
        )
    }
}