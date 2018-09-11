package com.lvbin.auth;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class SysUser implements UserDetails{
    private static final Logger log = LoggerFactory.getLogger(ApiController.class);

    private Integer id;
    private String username;
    private String password;
    private Integer role;

    public Integer getId() {
        return id;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        log.warn("role:"+role);
        List<String> roles = new ArrayList<>();
        if ((role & 8) == 8)
            roles.add("ROLE_admin");
        if ((role & 4) == 4)
            roles.add("ROLE_user");
        if ((role & 2) == 2)
            roles.add("ROLE_visit");
        if ((role & 1) == 1)
            roles.add("ROLE_lock");

        StringBuffer roleStr = new StringBuffer();
        for (String role:roles)
        {
            roleStr.append(role).append(",");
        }
        roleStr = new StringBuffer(roleStr.substring(0, roleStr.length() - 1));
        log.warn(roleStr.toString());
        return AuthorityUtils.commaSeparatedStringToAuthorityList(roleStr.toString());
    }


    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getRole() {
        return role;
    }
}
