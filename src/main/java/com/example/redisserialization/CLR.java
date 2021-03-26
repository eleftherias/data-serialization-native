package com.example.redisserialization;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class CLR implements CommandLineRunner {

    @Autowired
    LettuceConnectionFactory connectionFactory;

    @Override
    public void run(String... args) throws Exception {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        redisTemplate.setConnectionFactory(this.connectionFactory);
        redisTemplate.afterPropertiesSet();
        {
            BoundHashOperations<String, Object, Object> session = redisTemplate.boundHashOps("session");
            Map<String, Object> map = new HashMap<>();
            map.put("user", 1);
            session.putAll(map);
            BoundHashOperations<String, Object, Object> updatedSession = redisTemplate.boundHashOps("session");
            Object user = updatedSession.get("user");
            System.out.println("user is " + user);
        }
    }

}
