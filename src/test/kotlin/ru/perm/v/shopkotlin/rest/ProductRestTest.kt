package ru.perm.v.shopkotlin.rest

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.jupiter.MockitoExtension
import ru.perm.v.shopkotlin.dto.ProductDTO
import ru.perm.v.shopkotlin.service.ProductService

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
        Mockito.`when`(service.getByGroupN(GROUP_N)).thenReturn(products)
        Assertions.assertEquals(2, productRest.getProductsInGroup(GROUP_N).size)
    }
}