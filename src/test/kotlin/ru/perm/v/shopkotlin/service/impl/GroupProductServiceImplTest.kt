package ru.perm.v.shopkotlin.service.impl

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations
import ru.perm.v.shopkotlin.dto.GroupProductDTO
import ru.perm.v.shopkotlin.entity.GroupProductEntity
import ru.perm.v.shopkotlin.repository.GroupProductRepository

internal class GroupProductServiceImplTest {

    @Mock
    private lateinit var repository: GroupProductRepository

    @InjectMocks
    // @InjectMocks САМ создает экземпляр service c "инъектированным" MOCK repository
    private lateinit var service: GroupProductServiceImpl

    @BeforeEach
    fun setUp() {
        MockitoAnnotations.openMocks(this)
    }

    @Test
    fun getByN() {
        val N = 100L
        val group = GroupProductEntity(N, "NAME", -100, true)
        `when`(repository.getByN(N)).thenReturn(group)
//        так по старому, без @InjectMocks
//        val service = GroupProductServiceImpl(repository)

        val ret = service.getByN(N)

        assertEquals(GroupProductDTO(N, "NAME", -100, true), ret)
        verify(repository, times(1)).getByN(N)
    }

    @Test
    fun getSubGroups() {
        val N = 100L
        val SUB_N_101 = 101L
        val SUB_N_102 = 102L
        val subGroup101 = GroupProductEntity(SUB_N_101, "NAME_101", N, false)
        val subGroup102 = GroupProductEntity(SUB_N_102, "NAME_102", N, false)
        `when`(repository.findAllByParentN(N)).thenReturn(listOf(subGroup101, subGroup102))

        val subGroups = service.getSubGroups(N)

        assertEquals(2, subGroups.size)
        assertEquals(GroupProductDTO(SUB_N_101, "NAME_101", N, false), subGroups[0])
        assertEquals(GroupProductDTO(SUB_N_102, "NAME_102", N, false), subGroups[1])
    }

    @Test
    fun create() {
        val PARENT_N = 100L
        val groupDtoForSave = GroupProductDTO(-1L, "NAME", PARENT_N, true)

        val NEXT_N = 1L
        val groupProductEntityCreated = GroupProductEntity(NEXT_N, "NAME", PARENT_N, true)

        `when`(repository.save(groupProductEntityCreated)).thenReturn(groupProductEntityCreated)

        `when`(repository.getNextN()).thenReturn(NEXT_N)

        val savedGroup = service.create(groupDtoForSave)

        assertEquals(GroupProductDTO(1L, "NAME", PARENT_N, true), savedGroup)
    }

    @Test
    fun update() {
        val PARENT_N = 100L
        val groupDtoForSave = GroupProductDTO(101L, "NAME_101", PARENT_N, true)
        val groupProductEntityForMock = GroupProductEntity(101L, "NAME_101", PARENT_N, true)
        val groupProductEntitySaved = GroupProductEntity(101L, "NAME_101", PARENT_N, true)

        `when`(repository.existsByN(101L)).thenReturn(true)
        `when`(repository.save(groupProductEntityForMock)).thenReturn(groupProductEntitySaved)

        val savedGroup = service.update(groupDtoForSave)

        assertEquals(GroupProductDTO(101L, "NAME_101", PARENT_N, true), savedGroup)
    }

    @Test
    fun updateNotExistGroupProduct() {
        val PARENT_N = 100L
        val groupDtoForSave = GroupProductDTO(101L, "NAME_101", PARENT_N, true)

        `when`(repository.existsByN(101L)).thenReturn(false)

        val excpt = assertThrows<Exception> { service.update(groupDtoForSave) }

        assertEquals("GroupProduct with n=101 not exist", excpt.message)
    }

    @Test
    fun existsByN() {
        val N = 100L

        `when`(repository.existsByN(N)).thenReturn(true)

        assertTrue(service.existsByN(N))
    }

    @Test
    fun deleteByN() {
        val N = 100L
        `when`(repository.existsByN(N)).thenReturn(true)

        service.deleteByN(N)

        verify(repository, times(1)).deleteByN(N)
    }

    @Test
    fun findAllByOrderByNAsc() {
        val PARENT_ID = 0L
        val group101 = GroupProductEntity(101L, "NAME_101", PARENT_ID, true)
        val group102 = GroupProductEntity(102L, "NAME_102", PARENT_ID, true)
        `when`(repository.findAllByOrderByNAsc()).thenReturn(listOf(group101, group102))

        val dtos = service.findAllByOrderByNAsc()

        assertEquals(2, dtos.size)
        assertEquals(GroupProductDTO(101L, "NAME_101", PARENT_ID, true), dtos[0])
        assertEquals(GroupProductDTO(102L, "NAME_102", PARENT_ID, true), dtos[1])

        verify(repository, times(1)).findAllByOrderByNAsc()
    }

    @Test
    fun findByNameContaining() {
        val PARENT_ID = 0L
        val group101 = GroupProductEntity(101L, "NAME_101", PARENT_ID, true)
        val group102 = GroupProductEntity(102L, "NAME_102", PARENT_ID, true)
        `when`(repository.findByNameContaining("NAME")).thenReturn(listOf(group101, group102))

        val dtos = service.findByNameContaining("NAME")

        assertEquals(2, dtos.size)
        assertEquals(GroupProductDTO(101L, "NAME_101", PARENT_ID, true), dtos[0])
        assertEquals(GroupProductDTO(102L, "NAME_102", PARENT_ID, true), dtos[1])

        verify(repository, times(1)).findByNameContaining("NAME")
    }

    @Test
    fun existProductsInGroup() {
        val PARENT_ID = 100L
        val group101 = GroupProductEntity(101L, "NAME_101", PARENT_ID, true)
        val group102 = GroupProductEntity(102L, "NAME_102", PARENT_ID, true)
        //TODO: Проверить ПРОДУКТЫ в подгруппах, не сами подгруппы
        `when`(repository.findAllByParentN(PARENT_ID)).thenReturn(listOf(group101, group102))
        assertTrue(service.existProductsInGroup(PARENT_ID))
    }

    @Test
    fun notExistProductsInGroup() {
        val PARENT_ID = 100L
        `when`(repository.findAllByParentN(PARENT_ID)).thenReturn(listOf())
        assertFalse(service.existProductsInGroup(PARENT_ID))
    }

    @Test
    fun deleteNotExistGroupProduct() {
        val ID = 100L
        `when`(repository.existsByN(ID)).thenReturn(false)

        val error = assertThrows<Exception> {
            service.deleteByN(ID)
        }

        assertEquals("Not exist GroupProduct with n=100", error.message)
    }

}