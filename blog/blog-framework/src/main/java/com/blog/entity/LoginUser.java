package com.blog.entity;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "登录用户信息")
public class LoginUser implements UserDetails {
    private User user;

    private List<String> permissions;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    };

    @Override
    public String getPassword() {
        
        return user.getPassword();
    };

    @Override
    public String getUsername() {
        return user.getUserName();
    };

    @Override
    public boolean isAccountNonExpired() {
        return true;
    };

    @Override
    public boolean isAccountNonLocked() {
        return true;
    };

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    };

    @Override
    public boolean isEnabled() {
        return true;
    };
}
