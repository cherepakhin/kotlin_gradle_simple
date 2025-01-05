package ru.perm.v.shopkotlin.rest

import org.slf4j.LoggerFactory
import org.springframework.cache.annotation.CacheEvict
import org.springframework.cache.annotation.Cacheable
import org.springframework.web.bind.annotation.*
import ru.perm.v.shopkotlin.dto.GroupProductDTO
import ru.perm.v.shopkotlin.entity.NotFoundException
import ru.perm.v.shopkotlin.service.GroupProductService
import ru.perm.v.shopkotlin.service.ProductService

@RestController
@RequestMapping("/group_product")
//@Api(tags = ["group-product-api"])
/**
 * Определяет ТОЛЬКО интерфейсы доступа к сервису. Маппинг в DTO делается в сервисе
 * (уход от lazy проблем, независимость от способа получения самих DTO и т.п.).
 */
class GroupProductRest(val service: GroupProductService, val productService: ProductService) {

    private val logger = LoggerFactory.getLogger(this.javaClass.name)

    //TODO: delete group by id
    //TODO: create
    //TODO: get sub groups

    @GetMapping("/{n}")
    fun getById(@PathVariable n: Long): GroupProductDTO {
        return service.getByN(n)
    }

    /**
     * get all groups product
     */
    @GetMapping("/")
    @Cacheable("allGroupProductDTO")
//    @ApiOperation("Get all groups of product")
    fun all(): List<GroupProductDTO> {
        logger.info("GET all GroupProductDTO")
        val groups: List<GroupProductDTO> = service.findAllByOrderByNAsc()
        if (groups.isEmpty()) {
            throw NotFoundException("No group products found.")
        }
        return groups.map { entity ->
            GroupProductDTO(entity.n, entity.name, entity.parentN, entity.haveChilds)
        }
//        Error: .toList() лишний. Уже в list после map. Похоже особенности kotlin.
//        Нужно groups заколлектить в List и уже после как обычно маппить
//        взято отсюда https://www.baeldung.com/java-stream-immutable-collection.
//        Хрень в ".collect(toList())" он же вроде и так List
//        return groups.stream()
//            .map { entity ->
//                GroupProductDTO(entity.n, entity.name, entity.parentN, entity.haveChilds)
//            }.toList()
    }

    @GetMapping("/clear_cache")
    @CacheEvict("allGroupProductDTO", allEntries = true)
//    @ApiOperation("Clear cache application")
    fun clearCache(): String {
        return "cleared"
    }

    @GetMapping("/find")
//    @ApiOperation("Find groups by name")
    //TODO NO TEST!
    fun findByName(name: String): List<GroupProductDTO> {
        val groups = service.findByNameContaining(name).toList()
        if (groups.isEmpty()) {
            throw NotFoundException("No group products found.")
        }
        return groups.map { entity ->
            GroupProductDTO(entity.n, entity.name, entity.parentN, entity.haveChilds)
        }
    }

    @GetMapping("/{id}/subgroups")
    // сгенерировано BITO
    fun getSubGroups(id: Long): List<GroupProductDTO> {

//        val parentGroup = repository.findById(id)
//            .orElseThrow { NotFoundException("Group product not found with id: $id") }
//        val subGroups = repository.findAllByParentN(parentGroup.n)
//        return subGroups.map { entity ->
//            GroupProductDTO(entity.n, entity.name, entity.parentN, entity.haveChilds)
//        }
        return emptyList()
    }

    @DeleteMapping("/{n}")
    fun deleteByN(n: Long) {
        if (!service.existsByN(n)) {
            throw NotFoundException("Group product not found with id: $n")
        }
        if (service.existProductsInGroup(n)) {
            throw Exception("Group product with id: $n contains subgroups. Remove them first.")
        }
        if (productService.getByGroupProductN(n).isNotEmpty()) {
            throw Exception("Group product with n: $n contains products. Remove them to other group first.")
        }
        service.deleteByN(n)
    }

    @PostMapping
    fun create(@RequestBody groupProductDTO: GroupProductDTO) {
        service.create(groupProductDTO)
    }
}