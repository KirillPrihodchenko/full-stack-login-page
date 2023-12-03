package com.auth.fullstackloginpage.auth.util;

import com.auth.fullstackloginpage.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.UUID;

public class UserDetailsImp implements UserDetails {

    private UUID id;
    private String email;
    private String password;
    private Collection<? extends GrantedAuthority> authorities;

    public UserDetailsImp(UUID id, String email, String password, String role) {
        this.id = id;
        this.email = email;
        this.password = password;
    }

    public static UserDetailsImp build(User user) {
        return new UserDetailsImp(
                user.getId(),
                user.getEmail(),
                user.getPassword(),
                user.getRole()
        );
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
        this.authorities = authorities;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
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
}