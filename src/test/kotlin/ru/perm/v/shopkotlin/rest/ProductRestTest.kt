package ru.perm.v.shopkotlin.rest

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.jupiter.MockitoExtension
import ru.perm.v.shopkotlin.dto.ProductDTO
import ru.perm.v.shopkotlin.entity.NotFoundException
import ru.perm.v.shopkotlin.service.ProductService
import kotlin.test.assertTrue

@ExtendWith(MockitoExtension::class)
internal class ProductRestTest {

    @Mock
    private lateinit var service: ProductService

    @InjectMocks
    private lateinit var productRest: ProductRest

    @Test
    fun getCountProductInGroup() {
        val GROUP_N = 5L
        val products = listOf(
            ProductDTO(1L, "NAME_1", GROUP_N),
            ProductDTO(2L, "NAME_2", GROUP_N),
        )
        Mockito.`when`(service.getProductsByGroupN(GROUP_N)).thenReturn(products)

        val receivedProducts = productRest.getProductsByGroupN(GROUP_N)

        assertEquals(2, receivedProducts.size)
        assertEquals(ProductDTO(1L, "NAME_1", GROUP_N), receivedProducts.get(0))
        assertEquals(ProductDTO(2L, "NAME_2", GROUP_N), receivedProducts.get(1))
    }

    @Test
    fun getCountProductForNotExistGroup() {
        val GROUP_N = 5L
        Mockito.`when`(service.getProductsByGroupN(GROUP_N)).thenReturn(listOf())

        val products = productRest.getProductByN(GROUP_N)
        assertTrue(products.isEmpty())
    }
}