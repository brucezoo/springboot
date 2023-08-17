package com.bruce.springboot.service;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

@SpringBootTest
class AuthenticationTest {
    @Test
    public void httpBasicAuthentication() {
        String username = "admin";
        String password = "123456";

        // 用户名、密码 用 : 连接
        String src = username + ":" + password;
        // Base64编码
        String srcEncoded = Base64.getEncoder().encodeToString(src.getBytes(StandardCharsets.UTF_8));
        System.out.println(srcEncoded);
    }
}