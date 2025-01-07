package ru.perm.v.shopkotlin.rest

//import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiOperation
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import ru.perm.v.shopkotlin.repository.ProductRepository

@RestController
@RequestMapping("/product")
class ProductRest(val repository: ProductRepository) {
    //TODO: add CRUD
    @GetMapping("/count_names")
    @ApiOperation("Get number of product NAMES")
    fun getNumberOfProductNames(): Long {
        return repository.getNumberOfProductNames()
    }
}