package com.example.demo10092021.user.dto;

import com.example.demo10092021.user.model.EndUser;
import com.example.demo10092021.user.model.UserAuthority;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;

public class UserDTO implements UserDetails {
    private EndUser user;

    public UserDTO(EndUser user) {
        this.user = user;
//        System.out.println("------------" + authorities);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        HashSet<GrantedAuthority> set = new HashSet<GrantedAuthority>();
        for (UserAuthority a : user.getAuthorities()) {
            set.add(new SimpleGrantedAuthority(a.getName()));
        }
        return set;
    }

    public EndUser getUser() {
        return user;
    }

    public void setUser(EndUser user) {
        this.user = user;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
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
