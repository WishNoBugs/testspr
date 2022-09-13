package com.example.testspr.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.Collection;
import java.util.stream.Collector;

/**
 * 
 * @TableName user
 */
@Data
public class User implements UserDetails {
    /**
     * 
     */
    private Long id;

    /**
     * 
     */
    private String username;

    /**
     * 
     */
    private String password;

    /**
     * 
     */
    private String role;

    private Integer status;

    /**
     * 
     */
    private Integer isDeleted;

    @TableField(exist = false)
    private Collection <? extends GrantedAuthority> authorities;

    private static final long serialVersionUID = 1L;

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        if (this.status ==0) {
            return false;
        }
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
}