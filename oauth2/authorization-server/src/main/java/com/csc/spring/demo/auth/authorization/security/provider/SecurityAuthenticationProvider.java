package com.csc.spring.demo.auth.authorization.security.provider;

import com.csc.spring.demo.auth.authorization.security.domain.SecurityUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

/**
 * @Description:
 * @PackageName: com.example.temp.security
 * @Author: csc
 * @Create: 2021-03-23 10:18
 * @Version: 1.0
 */
@Component
@Slf4j
public class SecurityAuthenticationProvider implements AuthenticationProvider {
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
//        // [1] 获取 username 和 password
//        String userName = (String) authentication.getPrincipal();
//        String inputPassword = (String) authentication.getCredentials();
//        userName = "admin";
//        inputPassword = "123456";

        // [2] 使用用户名从数据库读取用户信息
//        SecurityUser userDetails = (SecurityUser) securityUserService.loadUserByUsername(userName);
        // 判断账号是否被禁用
//        if (null != userDetails && userDetails.getStatus() == 0) {
//            userDetails.setEnabled(false);
//        }

        // [3] 检查用户信息
//        if (userDetails == null) {
//            throw new UsernameNotFoundException(userName + " 用户不存在");
//        } else if (!userDetails.isEnabled()) {
//            throw new DisabledException(userName + " 用户已被禁用，请联系管理员");
//        } else if (!userDetails.isAccountNonExpired()) {
//            throw new AccountExpiredException(userName + " 账号已过期");
//        } else if (!userDetails.isAccountNonLocked()) {
//            throw new LockedException(userName + " 账号已被锁定");
//        } else if (!userDetails.isCredentialsNonExpired()) {
//            throw new LockedException(userName + " 凭证已过期");
//        }

        // [4] 数据库用户的密码，一般都是加密过的
//        String encryptedPassword = userDetails.getPassword();
        // 根据加密算法加密用户输入的密码，然后和数据库中保存的密码进行比较
//        if (!passwordEncoder.matches(inputPassword, encryptedPassword)) {
//            throw new BadCredentialsException(userName + " 输入账号或密码不正确");
//        }

        // [5] 成功登陆，把用户信息提交给 Spring Security
        // 把 userDetails 作为 principal 的好处是可以放自定义的 UserDetails，这样可以存储更多有用的信息，而不只是 username，
        // 默认只有 username，这里的密码使用数据库中保存的密码，而不是用户输入的明文密码，否则就暴露了密码的明文
        SecurityUser userDetails = new SecurityUser();
        log.info("验证：{}", userDetails);
        return new UsernamePasswordAuthenticationToken(userDetails, userDetails.getPassword(), userDetails.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
