package ru.perm.v.shopkotlin.config

import lombok.extern.slf4j.Slf4j
import org.springframework.cache.CacheManager
import org.springframework.cache.annotation.CachingConfigurerSupport
import org.springframework.cache.annotation.EnableCaching
import org.springframework.cache.concurrent.ConcurrentMapCacheManager
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@EnableCaching
@Configuration
@Slf4j
class CacheConfig : CachingConfigurerSupport() {
    @Bean
    override fun cacheManager(): CacheManager {
        return ConcurrentMapCacheManager("allGroupProductDTO")
    }
}