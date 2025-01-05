package ru.perm.v.shopkotlin.rest

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class EchoCtrlRestTest {
    @Test
    internal fun echoStr() {
        val echoCtrl = EchoCtrl()
        val mes = echoCtrl.echoStr("MESSAGE")
        assertEquals("MESSAGE", mes)
    }
}