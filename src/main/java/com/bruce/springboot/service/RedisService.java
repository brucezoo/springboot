package com.bruce.springboot.service;

import com.chinacareer.ymtd.redis.IRedisCache;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

@Service
@Slf4j
public class RedisService {
    @Resource
    private IRedisCache iRedisCache;

    public void testRedis() {
        Map<Object, Object> map = iRedisCache.hashGet("chat:card_code");
        System.out.println(map);
    }
}
