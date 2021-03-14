package com.csc.spring.demo.mybatisplus.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.csc.spring.demo.mybatisplus.domain.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @description:
 * @author csc
 * @since 2020-06-05
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

}
