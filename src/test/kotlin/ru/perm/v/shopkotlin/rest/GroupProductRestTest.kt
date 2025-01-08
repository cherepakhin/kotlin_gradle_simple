package ru.perm.v.shopkotlin.rest

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.doNothing
import org.mockito.Mockito.`when`
import org.mockito.junit.jupiter.MockitoExtension
import ru.perm.v.shopkotlin.dto.GroupProductDTO
import ru.perm.v.shopkotlin.entity.NotFoundException
import ru.perm.v.shopkotlin.service.GroupProductService
import ru.perm.v.shopkotlin.service.ProductService

@ExtendWith(MockitoExtension::class)
class GroupProductRestTest {
    @Mock
    private lateinit var groupProductService: GroupProductService

    @Mock
    private lateinit var productService: ProductService

    @Test
    fun `test all method when groups found`() {
        val controller = GroupProductRest(groupProductService, productService)
        // Given
        val group1 = GroupProductDTO(1, "Group 1", 0, true)
        val group2 = GroupProductDTO(2, "Group 2", 1, true)
        val groups = listOf(group1, group2)
        `when`(groupProductService.findAllByOrderByNAsc()).thenReturn(groups)
        // When
        val result = controller.all()
        // Then
        assertEquals(2, result.size)
        assertEquals("Group 1", result[0].name)
        assertEquals("Group 2", result[1].name)
    }

    @Test
    fun `test all method when no groups found`() {
        val controller = GroupProductRest(groupProductService, productService)
        // Given
        `when`(groupProductService.findAllByOrderByNAsc()).thenReturn(emptyList())
        // When
        val exception = assertThrows<NotFoundException> { controller.all() }
        // Then
        assertEquals("No group products found.", exception.message)
    }

    @Test
    fun deleteByN() {
        val controller = GroupProductRest(groupProductService, productService)
        val N = 100L

        `when`(groupProductService.existsByN(N)).thenReturn(true)
        doNothing().`when`(groupProductService).deleteByN(N)

        controller.deleteByN(N)

        Mockito.verify(groupProductService, Mockito.times(1)).deleteByN(N)
    }

    @Test
    fun deleteByIdForNotExist() {
        val controller = GroupProductRest(groupProductService, productService)
        val N = 100L

        `when`(groupProductService.existsByN(N)).thenReturn(false)

        assertThrows<NotFoundException> { controller.deleteByN(N) }
        Mockito.verify(groupProductService, Mockito.times(0)).deleteByN(N)
    }

    @Test
    fun checkExceptionOnDeleteByIdForExistSubgroup() {
        val controller = GroupProductRest(groupProductService, productService)
        val N = 100L

        `when`(groupProductService.existsByN(N)).thenReturn(true)
        `when`(groupProductService.existProductsInGroup(N)).thenReturn(true)

        assertThrows<Exception> { controller.deleteByN(N) }
        Mockito.verify(groupProductService, Mockito.times(0)).deleteByN(N)
        Mockito.verify(groupProductService, Mockito.times(1)).existProductsInGroup(N)
    }

    @Test
    fun getByN() {
        val ID = 100L
        val NAME = "NAME"
        val PARENT_N = 10L
        val groupProductDTO = GroupProductDTO(ID, NAME, PARENT_N)
        `when`(groupProductService.getByN(ID)).thenReturn(groupProductDTO)

        val controller = GroupProductRest(groupProductService, productService)
        val receivedGroupProduct = controller.getById(ID)

        assertEquals(GroupProductDTO(ID, NAME, PARENT_N), receivedGroupProduct)

    }
}