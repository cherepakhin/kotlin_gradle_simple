package ru.perm.v.shopkotlin.service

import ru.perm.v.shopkotlin.dto.GroupProductDTO

interface GroupProductService {
    fun create(groupProductDTO: GroupProductDTO): GroupProductDTO;
    fun update(groupProductDTO: GroupProductDTO): GroupProductDTO;
    fun getByN(n:Long): GroupProductDTO;
    fun findAllByOrderByNAsc(): List<GroupProductDTO>
    fun findByNameContaining(name: String): List<GroupProductDTO>
    fun getSubGroups(n:Long): List<GroupProductDTO>;
    fun deleteByN(n:Long);
    fun existsByN(n: Long): Boolean;
    fun existProductsInGroup(n: Long): Boolean
}