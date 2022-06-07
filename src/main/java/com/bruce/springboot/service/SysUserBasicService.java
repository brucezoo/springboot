package com.bruce.springboot.service;

import com.bruce.springboot.mapper.SysUserBasicMapper;
import com.bruce.springboot.model.entity.SysUserBasicEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author CI24871
 * @description 针对表【sys_user_basic(用户表)】的数据库操作Service
 * @createDate 2022-05-20 14:57:17
 */
@Service
@Slf4j
public class SysUserBasicService {
    @Resource
    private SysUserBasicMapper userBasicMapper;

    public SysUserBasicEntity getUserBasic(Integer userId) {
        return userBasicMapper.selectById(userId);
    }
}
