package com.csc.spring.demo.mybatisplus.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.csc.spring.demo.mybatisplus.domain.User;
import com.csc.spring.demo.mybatisplus.mapper.UserMapper;
import com.csc.spring.demo.mybatisplus.service.UserService;
import org.springframework.stereotype.Service;

/**
 * @description:
 * @author csc
 * @since 2020-06-05
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
