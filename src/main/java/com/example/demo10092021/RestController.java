package com.example.demo10092021;

import com.example.demo10092021.user.dto.UserDTO;
import com.example.demo10092021.user.model.EndUser;
import com.example.demo10092021.user.model.UserAuthority;
import com.example.demo10092021.user.repo.AuthorityRepository;
import com.example.demo10092021.product.repo.ProductRepository;
import com.example.demo10092021.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.*;


@org.springframework.web.bind.annotation.RestController
public class RestController {

    private final ProductRepository productRepository;
    private final AuthorityRepository authRepo;
    private final PasswordEncoder passwordEncoder;
    private final UserService userService;



    @Autowired
    public RestController(ProductRepository productRepository, AuthorityRepository authRepo, PasswordEncoder passwordEncoder, UserService userService) {
        this.productRepository = productRepository;
        this.authRepo = authRepo;
        this.passwordEncoder = passwordEncoder;
        this.userService = userService;
    }


    @PostMapping("/updates")
    public Map<String, Object> updateUser(
//            @RequestBody HashMap<String, Object> data,
            @RequestBody EndUser user
    ){
        System.out.println("USer: " + user);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDTO updatedUser = userService.update(((EndUser)auth.getPrincipal()), user);

        //set authentication
        UsernamePasswordAuthenticationToken newToken = new UsernamePasswordAuthenticationToken(updatedUser.getUser(), updatedUser.getPassword(), updatedUser.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(newToken);

        Map<String, Object> map = new HashMap<>();
        map.put("isUpdated", true);
        map.put("data", updatedUser.getUser());

        return map;
    }

    @GetMapping("/roles")
    public Set<UserAuthority> getRoles() {
        Set<UserAuthority> set = new HashSet<>(authRepo.findAll());
        return set;
    }




    @PostMapping("/update-password")
    public Map<String, String> updatePassword(@RequestBody Map<String, String> password) {

        Map<String, String> map = new HashMap<>();
        System.out.println("CONTROLLER: /update-password data: " + password);

        try {

            //get current user in context, so after password changing, user in context will be auto updated
            Authentication token = SecurityContextHolder.getContext().getAuthentication();
            EndUser currentUser = (EndUser) token.getPrincipal();

            //check if password is confirmed
            String oldPassword = password.get("old-pw");
            String newPassword = password.get("new-pw");
            System.out.println("current pw:"+  currentUser.getPassword());
            System.out.println("current user:"+  currentUser.getUsername());
            if (passwordEncoder.matches(oldPassword, currentUser.getPassword())) {
                // update password
                UserDTO updatedUser = userService.updatePassword(currentUser, newPassword);

                //set new security token
                Authentication newToken = new UsernamePasswordAuthenticationToken(updatedUser.getUser(), updatedUser.getPassword(), updatedUser.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(newToken);

                map.put("data", "true");
            } else {
                map.put("data", "false");
                map.put("error", "unmatch");
            }




        } catch (Exception e) {
            e.printStackTrace();
        }

        return map;
    }




}
