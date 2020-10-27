package com.example.demo.redis;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.DefaultStringRedisConnection;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.lang.NonNull;

//处理redis乱码
@Configuration
public class RedisCacheClient extends RedisTemplate<String, String> {

    private RedisCacheClient() {
        RedisSerializer<String> stringSerializer = new StringRedisSerializer();
        this.setKeySerializer(stringSerializer);
        this.setValueSerializer(stringSerializer);
        this.setHashKeySerializer(stringSerializer);
        this.setHashValueSerializer(stringSerializer);
    }

    public RedisCacheClient(final RedisConnectionFactory connectionFactory) {
        this();
        this.setConnectionFactory(connectionFactory);
        this.afterPropertiesSet();
    }

    @Override
    @NonNull
    protected RedisConnection preProcessConnection(@NonNull final RedisConnection connection, final boolean existingConnection) {
        return new DefaultStringRedisConnection(connection);
    }
}
