package io.ispacc.orion.admin.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "Redis接口")
@Slf4j
@RequestMapping("redis")
public class RedisController {

    private final RedisTemplate<String, String> redisTemplate;

    public RedisController(RedisTemplate<String, String> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @PostMapping("save")
    public void save(String key, String value) {
        redisTemplate.opsForValue().set(key, value);
    }

    @GetMapping("get")
    public String get(String key) {
        return redisTemplate.opsForValue().get(key);
    }
}
