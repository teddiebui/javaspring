package com.example.demo10092021.user.controller;

import com.example.demo10092021.user.model.EndUser;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
public class LoginController {

    @PostMapping("/login-handle")
    public LoginStatus loginhandle(HttpServletRequest request) {

        Authentication authResult = SecurityContextHolder.getContext().getAuthentication();
        LoginStatus status = new LoginStatus(authResult);

        return status;
    }

    private class LoginStatus {
        private boolean isLoggedIn;
        private String username;
        private Set<String> authorities = new HashSet<>();

        public LoginStatus(Authentication authResult) {
            this.username = ((EndUser)authResult.getPrincipal()).getUsername();
            this.isLoggedIn = authResult.isAuthenticated();
            authorities = authResult.getAuthorities().stream()
                    .map(GrantedAuthority::getAuthority)
                    .collect(Collectors.toSet());
        }

        public boolean isLoggedIn() {
            return isLoggedIn;
        }

        public void setLoggedIn(boolean loggedIn) {
            isLoggedIn = loggedIn;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public Set<String> getAuthorities() {
            return authorities;
        }

        public void setAuthorities(Set<String> authorities) {
            this.authorities = authorities;
        }

//        @Override
//        public String toString() {
//            return "LoginStatus{" +
//                    "isLoggedIn=" + isLoggedIn +
//                    ", username='" + username + '\'' +
//                    ", authorities=" + authorities +
//                    '}';
//        }
    }
}
