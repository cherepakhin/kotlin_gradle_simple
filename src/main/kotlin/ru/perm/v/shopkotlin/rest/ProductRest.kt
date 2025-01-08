package ru.perm.v.shopkotlin.rest

import io.swagger.annotations.ApiOperation
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import ru.perm.v.shopkotlin.dto.GroupProductDTO
import ru.perm.v.shopkotlin.dto.ProductDTO
import ru.perm.v.shopkotlin.repository.ProductRepository
import ru.perm.v.shopkotlin.service.ProductService

@RestController
@RequestMapping("/product")
class ProductRest(val productService: ProductService) {
    //TODO: add CRUD
    @GetMapping("/count_names")
    @ApiOperation("Get number of product NAMES")
    fun getNumberOfProductNames(): Long {
        //TODO: getNumberOfProductNames
        return 0
//        return repository.getNumberOfProductNames()
    }

    @GetMapping("/count_in_group_n/{groupN}")
    @ApiOperation("Get number of product in group")
    fun getProductsInGroup(@PathVariable("groupN") groupN: Long): List<ProductDTO> {
        return productService.getByGroupN(groupN)
    }
}