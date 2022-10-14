package rider11.hellospringboot.controller;

import java.time.Duration;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import rider11.hellospringboot.entity.User;

@RestController
@RequestMapping("/redis")
public class RedisController {
    private final RedisTemplate<String, Object> redisTemplate;

    public RedisController(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @GetMapping("set")
    public String set(String key, String value, Long seconds) {
        this.redisTemplate.opsForValue().set(key, value, Duration.ofSeconds(seconds));
        return "ok";
    }

    @GetMapping("set_user")
    public String set(String key, User value, Long seconds) {
        this.redisTemplate.opsForValue().set(key, value, Duration.ofSeconds(seconds));
        return "ok";
    }

    @GetMapping("get")
    public Object get(String key) {
        return this.redisTemplate.opsForValue().get(key);
    }
}
