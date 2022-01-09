package com.example.demo10092021.security.customFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.RequestMatcher;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

public class MyLoginHandleFilter extends AbstractAuthenticationProcessingFilter {


    protected MyLoginHandleFilter(String defaultFilterProcessesUrl) {
        super(defaultFilterProcessesUrl);
    }

    protected MyLoginHandleFilter(RequestMatcher requiresAuthenticationRequestMatcher) {
        super(requiresAuthenticationRequestMatcher);
    }

    public MyLoginHandleFilter(String defaultFilterProcessesUrl, AuthenticationManager authenticationManager) {
        super(defaultFilterProcessesUrl, authenticationManager);
        System.out.println("Im Login Handle Filter now");
    }

    protected MyLoginHandleFilter(RequestMatcher requiresAuthenticationRequestMatcher, AuthenticationManager authenticationManager) {
        super(requiresAuthenticationRequestMatcher, authenticationManager);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
        System.out.println("---------DEBUG: attempt authentication");

        byte[] inputStream = request.getInputStream().readAllBytes();

        Map<String, Object> map = new ObjectMapper().readValue(inputStream, Map.class);

        String   username = (String)map.get("username");
        String   password = (String)map.get("password");

        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                username, password, null);

        token.setDetails(request);
        return this.getAuthenticationManager().authenticate(token);
    }


    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        System.out.println("success login");
        SecurityContextHolder.getContext().setAuthentication(authResult);

        //cai nay dung cho RestAPI
        request.setAttribute("authResult", authResult);

        chain.doFilter(request, response);
//        super.successfulAuthentication(request, response, chain, authResult);
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        System.out.println("failed login");
        super.unsuccessfulAuthentication(request, response, failed);
    }


}
