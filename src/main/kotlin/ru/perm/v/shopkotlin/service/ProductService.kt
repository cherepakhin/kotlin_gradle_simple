package ru.perm.v.shopkotlin.service

import ru.perm.v.shopkotlin.dto.ProductDTO

interface ProductService {
    fun getProductsByGroupN(groupN: Long): List<ProductDTO>
    fun getProductByN(productN: Long): ProductDTO
}