package ru.perm.v.shopkotlin.dto

data class GroupProductDTO(
    val n: Long = -1,
    val name: String = "",
    val parentN: Long = -1,
    val haveChilds: Boolean = false
)