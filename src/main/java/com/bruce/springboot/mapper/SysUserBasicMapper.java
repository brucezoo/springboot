package com.bruce.springboot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bruce.springboot.model.entity.SysUserBasicEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author CI24871
 * @description 针对表【sys_user_basic(用户表)】的数据库操作Mapper
 * @createDate 2022-05-20 14:57:17
 * @Entity com.bruce.springboot.model.entity.SysUserBasicEntity
 */
@Mapper
public interface SysUserBasicMapper extends BaseMapper<SysUserBasicEntity> {


}
