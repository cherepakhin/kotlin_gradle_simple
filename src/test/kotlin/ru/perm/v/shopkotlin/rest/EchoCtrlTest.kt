package ru.perm.v.shopkotlin.rest

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity

internal class EchoCtrlTest {

    @Test
    fun echoStr() {
        val ctrl = EchoCtrl();
        assertEquals("MES", ctrl.echoStr("MES"))
    }
}