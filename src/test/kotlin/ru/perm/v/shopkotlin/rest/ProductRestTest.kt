package ru.perm.v.shopkotlin.rest

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.jupiter.MockitoExtension
import ru.perm.v.shopkotlin.repository.ProductRepository

@ExtendWith(MockitoExtension::class)
internal class ProductRestTest {

    @Mock
    private lateinit var repository: ProductRepository

    @InjectMocks
    private lateinit var productRest: ProductRest

    @Test
    fun getNumberOfProductNames() {
        val count = 5L
        Mockito.`when`(repository.getNumberOfProductNames()).thenReturn(count)
        Assertions.assertEquals(5L, productRest.getNumberOfProductNames())
    }
}