package com.csc.spring.oauth.server.service;

import com.csc.spring.oauth.server.service.dto.UserFormDto;
import com.csc.spring.oauth.server.service.dto.UserJsonDto;
import com.csc.spring.oauth.server.service.dto.UserOverviewDto;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * @author Shengzhao Li
 */
public interface UserService extends UserDetailsService {

    UserJsonDto loadCurrentUserJsonDto();

    UserOverviewDto loadUserOverviewDto(UserOverviewDto overviewDto);

    boolean isExistedUsername(String username);

    String saveUser(UserFormDto formDto);
}