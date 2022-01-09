package com.example.demo10092021.security.customFilter;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MyAuthorityFilter extends AbstractAuthenticationProcessingFilter {

    public MyAuthorityFilter(String defaultFilterProcessesUrl, AuthenticationManager authenticationManager) {
        super(defaultFilterProcessesUrl, authenticationManager);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        if (!this.requiresAuthentication((HttpServletRequest) request, (HttpServletResponse) response)) {
            // ko can filter end point hien tai
            chain.doFilter(request, response);
            return;
        }


        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null) {
            // chua xac minh danh tinh
            System.out.println("Unauthenticated");
            ((HttpServletResponse) response).sendRedirect("/");
            return;
        }

        System.out.println("DANG XAC MINH QUYEN CUA: " + authentication);
        for (GrantedAuthority a: authentication.getAuthorities()) {
            if (a.getAuthority().equalsIgnoreCase("admin")) {
                System.out.println("Authorized");
                chain.doFilter(request, response);
                return;
            }
        }

       System.out.println("Unauthorized");
       ((HttpServletResponse) response).sendRedirect("/unauthorized");

    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
        return null;
    }
}
