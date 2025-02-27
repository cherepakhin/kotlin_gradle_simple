package ru.perm.v.shopkotlin

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cache.annotation.EnableCaching

@SpringBootApplication
//    (exclude = arrayOf(org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration::class))
@EnableCaching
class ShopKotlinApplication

fun main(args: Array<String>) {
    runApplication<ShopKotlinApplication>(*args)
}
