package com.bruce.springboot.controller;

import com.bruce.springboot.model.entity.SysUserBasicEntity;
import com.bruce.springboot.response.ApiResponse;
import com.bruce.springboot.service.SysUserBasicService;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/mysql")
public class MysqlController {
    @Resource
    private SysUserBasicService userBasicService;

    @Resource
    private JdbcTemplate jdbcTemplate;

    @GetMapping("/user/info")
    public ApiResponse<SysUserBasicEntity> getUserBasic(@RequestParam("userId") Integer userId) {
        SysUserBasicEntity response = userBasicService.getUserBasic(userId);
        return ApiResponse.success(response);
    }

    @GetMapping("/jdbc")
    public ApiResponse<SysUserBasicEntity> getUserBasicByJdbc(@RequestParam("userId") Integer userId) {
        jdbcTemplate.update("update gb_user_config set `config_item_value` = 1 where id = 3");
        return ApiResponse.success();
    }
}
