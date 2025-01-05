package ru.perm.v.shopkotlin.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional
import ru.perm.v.shopkotlin.entity.ProductEntity

@Repository
@Transactional
interface ProductRepository : JpaRepository<ProductEntity, Long> {

    fun findAllByOrderByNAsc(): List<ProductEntity>
    fun findAllByGroupProductNOrderByNAsc(groupProductN:Long): List<ProductEntity>

    @Query(value = "select max(p.n)+1 from product p", nativeQuery = true)
    fun getNextN(): Long

//    fun findByNameContaining(name: String)

    @Query(
        "select count(*) from ProductEntity"
    )
    fun getNumberOfProductNames(): Long
}