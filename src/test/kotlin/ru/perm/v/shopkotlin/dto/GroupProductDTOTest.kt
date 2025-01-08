package ru.perm.v.shopkotlin.dto

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import kotlin.test.assertFalse

internal class GroupProductDTOTest {

    @Test
    fun constructor() {
        val N = 100L
        val NAME = "NAME"
        val PARENT_N = 101L
        val group = GroupProductDTO(N, NAME, PARENT_N, true)
        assertEquals(N, group.n)
        assertEquals(NAME, group.name)
        assertTrue(group.haveChilds)
    }

    @Test
    fun defaultConstructor() {
        val groupProductDTO = GroupProductDTO()

        assertEquals(-1L, groupProductDTO.n)
        assertEquals("", groupProductDTO.name)
        assertEquals(-1L, groupProductDTO.parentN)
        assertFalse(groupProductDTO.haveChilds)
    }

    @Test
    fun constructorWithParam() {
        val groupProductDTO = GroupProductDTO(1L, "NAME", 100L, true)

        assertEquals(1L, groupProductDTO.n)
        assertEquals("NAME", groupProductDTO.name)
        assertEquals(100L, groupProductDTO.parentN)
        assertTrue(groupProductDTO.haveChilds)
    }

}