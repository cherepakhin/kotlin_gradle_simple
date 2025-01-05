package ru.perm.v.shopkotlin.service.impl

import org.springframework.stereotype.Service
import ru.perm.v.shopkotlin.dto.ProductDTO
import ru.perm.v.shopkotlin.repository.ProductRepository
import ru.perm.v.shopkotlin.service.ProductService

@Service
class ProductServiceImpl(val repository: ProductRepository) : ProductService {
    override fun getByGroupProductN(n: Long): List<ProductDTO> {
        return repository.findAllByGroupProductNOrderByNAsc(n).stream()
            .map { ProductDTO(it.n, it.name, it.groupProductN) }.toList()
    }
}