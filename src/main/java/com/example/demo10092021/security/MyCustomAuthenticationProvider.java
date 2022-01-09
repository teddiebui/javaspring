package com.example.demo10092021.security;

import com.example.demo10092021.user.model.EndUser;
import com.example.demo10092021.user.dto.UserDTO;
import com.example.demo10092021.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;


@Component
public class MyCustomAuthenticationProvider implements AuthenticationProvider {


    private final UserService service;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public MyCustomAuthenticationProvider(UserService service, PasswordEncoder passwordEncoder) {
        this.service = service;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        System.out.println("-----DEBUG: inside MyCustomAuthenticationProvider, tring to authenticate...");

        UserDTO user = service.loadUserByUsername(authentication.getName());

        if (user == null) {
            System.out.println("--------- user not found");
            return null;
        }
        if (!passwordEncoder.matches((String) authentication.getCredentials(),user.getPassword())) {
            System.out.println("--------- password not match");
            return null;
        }
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(user.getUser(), user.getPassword(), user.getAuthorities());
        System.out.println(user.getUser());
        System.out.println("token generated: " + token);
        token.setDetails(authentication.getDetails());
        return token;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return true;
    }

    public class MyWebAuthenticationDetails extends WebAuthenticationDetails {
        /**
         * Records the remote address and will also set the session Id if a session already
         * exists (it won't create one).
         *
         * @param request that the authentication request was received from
         */
        private EndUser user;
        public MyWebAuthenticationDetails(HttpServletRequest request, EndUser user) {
            super(request);
            this.user = user;
        }

        public EndUser getUser() {
            return user;
        }

        public void setUser(EndUser user) {
            this.user = user;
        }
    }

}
