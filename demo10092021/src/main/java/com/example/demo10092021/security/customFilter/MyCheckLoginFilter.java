package com.example.demo10092021.security.customFilter;

import org.springframework.security.core.context.SecurityContextHolder;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class MyCheckLoginFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("hello im checking login before passing you: " + SecurityContextHolder.getContext().getAuthentication());
        System.out.println("referer" + ((HttpServletRequest)request).getHeader("referer"));
        chain.doFilter(request,response);
    }
}
