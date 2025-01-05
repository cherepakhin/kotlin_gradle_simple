package ru.perm.v.shopkotlin.service

import ru.perm.v.shopkotlin.dto.ProductDTO

interface ProductService {
    fun getByGroupProductN(n: Long): List<ProductDTO>
}