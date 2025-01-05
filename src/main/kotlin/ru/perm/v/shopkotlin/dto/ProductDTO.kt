package ru.perm.v.shopkotlin.dto

class ProductDTO {
    //TODO change to UUID
    var n: Long = -1
    var name: String = ""
    var groupDtoN: Long = -1

    constructor()
    constructor(n: Long, name: String, groupDtoN: Long) {
        this.n = n
        this.name = name
        this.groupDtoN = groupDtoN
    }

    // equals and hash as in java
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as ProductDTO

        if (n != other.n) return false
        if (name != other.name) return false
        if (groupDtoN != other.groupDtoN) return false

        return true
    }

    override fun hashCode(): Int {
        var result = n.hashCode()
        result = 31 * result + name.hashCode()
        result = 31 * result + groupDtoN.hashCode()
        return result
    }

}