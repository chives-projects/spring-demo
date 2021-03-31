package com.csc.spring.demo.auth.resource.other;

import com.csc.spring.demo.auth.resource.domain.SecurityUser;
import com.csc.spring.demo.auth.resource.domain.UserGrantedAuthority;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Description:
 * @PackageName: com.example.temp.security
 * @Author: csc
 * @Create: 2021-03-23 10:22
 * @Version: 1.0
 */
@Service
public class SecurityUserService implements UserDetailsService {
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        UserDTO userDTO = userService.getRolesByUsername(username);
        // 默认用户ID为1的为管理员
//        if (null != userDTO){
//            if(1L == userDTO.getId()) {
//                this.getAdminPermission(userDTO);
//            }
//            SecurityUser securityUser = new SecurityUser(LoginUserDTO.user2LoginUserDTO(userDTO));
//            return securityUser;
//        } else {
//            throw new UsernameNotFoundException(username + " 用户不存在!");
//        }

        return new SecurityUser();
    }
}
