package com.csc.spring.demo.auth.resource.domain;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Data
public class SecurityUser implements UserDetails, Serializable {
    /**
     * 主键ID
     */
    private Long id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 启用状态(0-禁用，1-启用)
     */
    private Integer status;

    /**
     * 角色
     */
    private List<Role> roles;

    /**
     * 权限菜单
     */
    private List<MenuRight> menus;
    /**
     isEnabled 账户是否启用
     isAccountNonExpired 账户没有过期
     isCredentialsNonExpired 身份认证是否是有效的
     isAccountNonLocked 账户没有被锁定
     */
    private Boolean accountNonExpired = true;
    private Boolean accountNonLocked = true;
    private Boolean credentialsNonExpired = true;
    private Boolean enabled = true;

    public SecurityUser() {
        this.id = 123L;
        this.username = "csc";
        this.password = "123456";
        this.roles = new ArrayList<Role>() {{
            add(Role.admin());
            Role.user();
        }};
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        List<Role> userRoles = this.getRoles();
        if (userRoles != null) {
            for (Role role : userRoles) {
                SimpleGrantedAuthority authority = new SimpleGrantedAuthority("ROLE_" + role.getName());
                authorities.add(authority);
            }
        }
        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return this.accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return this.credentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return this.enabled;
    }
}
