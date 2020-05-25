package ru.samgtu.erp.service

import org.springframework.stereotype.Service
import redis.clients.jedis.JedisPool
import redis.clients.jedis.JedisPoolConfig

/**
 * Сервис для сохранения в Redis JWT токена. Требуется для управления JWT
 */
@Service
class RedisJWTService {
    private final val jedisPool = JedisPool(JedisPoolConfig(), "localhost")

    fun createValue(token: String, username: String) {
        val jedis = jedisPool.resource
        jedis.use { it.set(token, username) }
    }

    fun deleteValue(token: String) {
        val jedis = jedisPool.resource
        jedis.use { it.del(token) }
    }

    fun checkValue(token: String): Boolean {
        val jedis = jedisPool.resource
        jedis.use { return it.get(token) != null }
    }
}