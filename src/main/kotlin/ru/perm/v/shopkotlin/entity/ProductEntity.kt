package ru.perm.v.shopkotlin.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table


@Entity
@Table(name = "product")
data class ProductEntity(
    //TODO change to UUID
    @Id
    @Column(name = "n", nullable = false)
    // column name must is not "id", "id" is key word
    var n: Long = -1,
    @Column(name = "name", nullable = false)
    var name: String = "",
    @Column(name = "group_product_n", nullable = false)
    var groupProductN: Long = -1
)
