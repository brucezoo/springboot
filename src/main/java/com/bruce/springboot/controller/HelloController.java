package com.bruce.springboot.controller;

import com.bruce.springboot.model.dto.request.PostBodyReq;
import com.bruce.springboot.model.dto.response.AutoCompletionRes;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
@Slf4j
@RequestMapping("/hello")
public class HelloController {
    @Resource
    private RestTemplate restTemplate;

    @GetMapping("/helloWorld")
    @CrossOrigin(origins = "http://192.168.163.207:8081")
    public String helloWorld() {
        System.out.println("=== success ===");
        return "Hello,World";
    }

    @GetMapping("/get")
    public String get(@RequestParam("param") String param) {
        log.info("Get Request Query Param:{}", param);
        return param;
    }

    @PostMapping("/post")
    public Object post(@RequestBody PostBodyReq postBodyReq) {
        log.info("Post Request Body Param:{}", postBodyReq);
        return postBodyReq;
    }

    @GetMapping("/restTemplate")
    public void restTemplate() {
        String url = "http://m-api.laravel.local/api/searches/auto-completion?keyword=医药&client=mini";

        AutoCompletionRes autoCompletionRes = restTemplate.getForObject(url, AutoCompletionRes.class);
        System.out.println(autoCompletionRes);

        ResponseEntity<AutoCompletionRes> resResponseEntity = restTemplate.getForEntity(url, AutoCompletionRes.class);
        System.out.println(resResponseEntity);
        System.out.println(resResponseEntity.getStatusCode());
        System.out.println(resResponseEntity.getStatusCodeValue());
        System.out.println(resResponseEntity.getBody());
        System.out.println(resResponseEntity.getHeaders());
    }
}
