package com.bruce.springboot.controller;

import com.bruce.springboot.response.ApiResponse;
import com.bruce.springboot.service.RedisService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping(value = "/redis")
public class RedisController {
    @Resource
    private RedisService redisService;

    @GetMapping("/testRedis")
    public Object testRedis() {
        redisService.testRedis();
        return ApiResponse.success();
    }
}
