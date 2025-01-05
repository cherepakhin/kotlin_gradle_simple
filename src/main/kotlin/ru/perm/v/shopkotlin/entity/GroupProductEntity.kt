package ru.perm.v.shopkotlin.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "group_product")
data class GroupProductEntity(
    @Id
    @Column(name = "n")
    // COLUMN NAME MUST IS NOT "ID", "ID" IS KEY WORD IN h2database
    val n: Long = -1,
    @Column(name = "name", nullable = false)
    val name: String = "",
    @Column(name = "parent_n", nullable = false)
    val parentN: Long = -1,
    @Column(name = "have_childs", nullable = false)
    val haveChilds: Boolean = false
)