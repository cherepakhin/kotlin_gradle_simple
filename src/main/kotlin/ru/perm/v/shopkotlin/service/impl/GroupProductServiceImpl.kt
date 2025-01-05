package ru.perm.v.shopkotlin.service.impl

import org.springframework.stereotype.Service
import ru.perm.v.shopkotlin.dto.GroupProductDTO
import ru.perm.v.shopkotlin.entity.GroupProductEntity
import ru.perm.v.shopkotlin.repository.GroupProductRepository
import ru.perm.v.shopkotlin.service.GroupProductService

/**
 * Service будет возвращать DTO. Для ухода от lazy.
 */
@Service
class GroupProductServiceImpl(val repository: GroupProductRepository) : GroupProductService {

    override fun getByN(n: Long): GroupProductDTO {
        //NOTE: В kotlin можно покороче. через '?'.
        val groupProductEntity = repository.getByN(n)
//            ?: throw Exception(String.format("GroupProduct with n=%s not exist ", n))
// В java так
//        if (groupProductEntity == null) {
//            throw Exception(String.format("GroupProduct with n=%s not exist ", n))
//        }
        return GroupProductDTO(
            groupProductEntity.n,
            groupProductEntity.name,
            groupProductEntity.parentN,
            groupProductEntity.haveChilds
        )
    }

    override fun create(groupProductDTO: GroupProductDTO): GroupProductDTO {
        val n = repository.getNextN()
        val groupProductEntity = GroupProductEntity(
            n, groupProductDTO.name, groupProductDTO.parentN, groupProductDTO.haveChilds
        )
        val newGroup = repository.save(groupProductEntity)
        return GroupProductDTO(
            newGroup.n,
            newGroup.name,
            newGroup.parentN,
            newGroup.haveChilds
        )
    }

    // Можно совместить с create() или даже сделать один save(). Но сделал именно так.
    override fun update(groupProductDTO: GroupProductDTO): GroupProductDTO {
        if (!existsByN(groupProductDTO.n)) {
            throw Exception(String.format("GroupProduct with n=%s not exist ", groupProductDTO.n))
        }
        val groupProductEntity = GroupProductEntity(
            groupProductDTO.n, groupProductDTO.name, groupProductDTO.parentN, groupProductDTO.haveChilds
        )
        val newGroup = repository.save(groupProductEntity)
        return GroupProductDTO(
            newGroup.n,
            newGroup.name,
            newGroup.parentN,
            newGroup.haveChilds
        )
    }

    override fun getSubGroups(n: Long): List<GroupProductDTO> {
        val subGroups = repository.findAllByParentN(n)
        val ret = ArrayList<GroupProductDTO>()
        for (subGroup in subGroups) {
            ret.add(GroupProductDTO(subGroup.n, subGroup.name, subGroup.parentN, subGroup.haveChilds))
        }
        return ret
    }

    override fun deleteByN(n: Long) {
        if (existsByN(n)) {
            repository.deleteByN(n)
        }
    }

    override fun findAllByOrderByNAsc(): List<GroupProductDTO> {
        val groups = repository.findAllByOrderByNAsc()
            .map { g -> GroupProductDTO(g.n, g.name, g.parentN, g.haveChilds) }.toList()
        return groups;
//        return repository.findAllByOrderByNAsc().stream()
//            .map { g -> GroupProductDTO(g.n, g.name, g.parentN, g.haveChilds) }
    }

    override fun findByNameContaining(name: String): List<GroupProductDTO> {
        val groups = repository.findByNameContaining(name)
            .map { g -> GroupProductDTO(g.n, g.name, g.parentN, g.haveChilds) }.toList()
        return groups;
//        return repository.findByNameContaining(name).stream()
//            .map { g -> GroupProductDTO(g.n, g.name, g.parentN, g.haveChilds) }
    }

    override fun existsByN(n: Long): Boolean {
        return repository.existsByN(n)
    }

    override fun existProductsInGroup(n: Long): Boolean {
        return getSubGroups(n).size > 0
    }
}