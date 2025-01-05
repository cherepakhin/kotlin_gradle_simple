package ru.perm.v.shopkotlin.bito.rest

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import ru.perm.v.shopkotlin.rest.EchoCtrl

class EchoCtrlTest {
    private val echoCtrl = EchoCtrl()

    @Test
    fun `echoStr should return the same message`() {
        val message = "Hello World"
        val result = echoCtrl.echoStr(message)
        assertEquals(message, result)
    }

    @Test
    fun `echoStr should return 404 for empty message`() {
        val mockMvc: MockMvc = MockMvcBuilders.standaloneSetup(echoCtrl).build()
        mockMvc.perform(get("/"))
            .andExpect(status().isNotFound)
    }
}