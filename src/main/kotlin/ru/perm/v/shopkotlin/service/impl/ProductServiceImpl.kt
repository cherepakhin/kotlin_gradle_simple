package ru.perm.v.shopkotlin.service.impl

import org.springframework.stereotype.Service
import ru.perm.v.shopkotlin.dto.ProductDTO
import ru.perm.v.shopkotlin.repository.ProductRepository
import ru.perm.v.shopkotlin.service.ProductService

@Service
class ProductServiceImpl(val repository: ProductRepository) : ProductService {
    override fun getProductsByGroupN(groupN: Long): List<ProductDTO> {
        return repository.findAllByGroupProductNOrderByNAsc(groupN).stream()
            .map { ProductDTO(it.n, it.name, it.groupProductN) }.toList()
    }

    override fun getProductByN(productN: Long): ProductDTO {
        if (repository.existsById(productN)) {
            val productEntity = repository.getReferenceById(productN)
            return ProductDTO(productEntity.n, productEntity.name, productEntity.groupProductN)
        } else {
            throw Exception("Not found product with n=${productN}")
        }
    }

}