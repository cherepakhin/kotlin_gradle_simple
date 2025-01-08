package ru.perm.v.shopkotlin.service.impl

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.junit.jupiter.MockitoExtension
import ru.perm.v.shopkotlin.dto.ProductDTO
import ru.perm.v.shopkotlin.entity.ProductEntity
import ru.perm.v.shopkotlin.repository.ProductRepository
import ru.perm.v.shopkotlin.service.GroupProductService
import kotlin.test.assertEquals

@ExtendWith(MockitoExtension::class)
class ProductServiceImplTest {

    @Mock
    private lateinit var repository: ProductRepository

    @Mock
    private lateinit var groupProductService: GroupProductService

    @Test
    fun getByN() {
        val N = 100L
        val GROUP_N = 1L

        val service = ProductServiceImpl(repository,groupProductService)
        val productEntity = ProductEntity(N, "NAME", GROUP_N)
        `when`(repository.existsById(N)).thenReturn(true)
        `when`(repository.getReferenceById(N)).thenReturn(productEntity)

        val productDTO = service.getProductByN(N)

        assertEquals(ProductDTO(N, "NAME", GROUP_N), productDTO)
    }

    @Test
    fun getByNotExistN() {
        val N = 100L

        val mockProductRepository = Mockito.mock(ProductRepository::class.java)
        val service = ProductServiceImpl(mockProductRepository, groupProductService)
        `when`(mockProductRepository.existsById(N)).thenReturn(false)
        val excpt = assertThrows<Exception> { service.getProductByN(N) }

        assertEquals("Not found product with n=100", excpt.message)
    }

    @Test
    fun getByNotExistGroupProduct() {
        val GROUP_N = 100L

        val mockProductRepository = Mockito.mock(ProductRepository::class.java)
        val mockGroupProductService = Mockito.mock(GroupProductServiceImpl::class.java)
        val productService = ProductServiceImpl(mockProductRepository, mockGroupProductService)
        `when`(mockGroupProductService.existsByN(GROUP_N)).thenReturn(false)
        val excpt = assertThrows<Exception> { productService.getProductsByGroupN(GROUP_N) }

        assertEquals("Group of product 100 not exist.", excpt.message)
    }
}