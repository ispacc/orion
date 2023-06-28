package io.ispacc.orion.admin.module.admin.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
@RequiredArgsConstructor
public class TestController {

    private final RedisTemplate<Object, Object> redisTemplate;

    @PostMapping("/save")
    public void save(String key, String value) {
        redisTemplate.opsForValue().set(key, value);
    }

    @PostMapping("/get")
    public Object get(String key) {
        return redisTemplate.opsForValue().get(key);
    }

}
