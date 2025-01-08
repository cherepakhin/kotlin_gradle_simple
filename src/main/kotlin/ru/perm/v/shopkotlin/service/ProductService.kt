package ru.perm.v.shopkotlin.service

import ru.perm.v.shopkotlin.dto.ProductDTO

interface ProductService {
    fun getByGroupN(groupN: Long): List<ProductDTO>
}