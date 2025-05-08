package com.tshirtmart.trilo.cache;

import java.time.Duration;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
@EnableCaching
public class RedisConfig {

    // Redis connection factory bean
    @Bean
    public RedisConnectionFactory redisConnectionFactory() {
        return new LettuceConnectionFactory();  // Default Lettuce connection
    }

    // RedisTemplate configuration for object serialization/deserialization
    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisFactory) {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisFactory);

        // Key and Hash key serializers to handle string type keys
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());

        // Value and Hash value serializers to handle UserDTO objects
        redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        redisTemplate.setHashValueSerializer(new GenericJackson2JsonRedisSerializer());

        // Initialize the RedisTemplate
        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }

    // CacheManager configuration with default TTL and custom serialization
    @Bean
    public CacheManager cacheManager(RedisConnectionFactory redisFactory) {
        // Create the default cache configuration
        RedisCacheConfiguration config = RedisCacheConfiguration.defaultCacheConfig();

        // Set TTL (Time-To-Live) to 1 hour
        config.entryTtl(Duration.ofHours(1));

        // Serialization settings for keys and values
        config.serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(new StringRedisSerializer()));
        config.serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(new GenericJackson2JsonRedisSerializer()));

        // Return a RedisCacheManager with the given configuration
        return RedisCacheManager.builder(redisFactory)
                                .cacheDefaults(config)
                                .build();
    }
}
