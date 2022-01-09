package com.example.demo10092021.user.controller;

import com.example.demo10092021.user.model.EndUser;
import com.example.demo10092021.user.dto.UserDTO;
import com.example.demo10092021.user.repo.AuthorityRepository;
import com.example.demo10092021.user.service.UserService;
import org.hibernate.cfg.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    private final UserService userService;
    private final AuthorityRepository authRepo;

    @Autowired
    public UserController(UserService userService, AuthorityRepository authRepo) {
        this.userService = userService;
        this.authRepo = authRepo;
    }

    @GetMapping("/user/get")
    public EndUser getAllUser(@RequestParam String username) {
        //TODO: get user with username
        System.out.println("USer: " + username);
        UserDTO user = userService.loadUserByUsername(username);
        if (user != null) {
            return user.getUser();
        }
        return null;
    }

    @GetMapping("/user/get-all")
    public List<EndUser> getAllUser() {
        //TODO: get all user
        return userService.findAll();
    }



    @PostMapping("/user/update")
    public String updateUser(
            @PathVariable int id,
            @RequestBody EndUser temp) {
        //TODO: update user with id

        try {
            //get current user in context, so after password changing, user in context will be auto updated
            Authentication token = SecurityContextHolder.getContext().getAuthentication();

            System.out.println("/user/update --- TESTING"+ (EndUser)token.getPrincipal());

            //update user attribute
            UserDTO updatedUser =  userService.update(((EndUser)token.getPrincipal()), temp);

            //create new token
            UsernamePasswordAuthenticationToken newToken = new UsernamePasswordAuthenticationToken(updatedUser.getUser(), updatedUser.getPassword(), updatedUser.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(newToken);

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("There are some error update user");
        }

        return "redirect:admin";
    }

    @PostMapping("/user/add")
    public String addUser(@RequestBody EndUser user) {
        //TODO: addUser
        System.out.println("/user/add CONTROLLER: " + user);
        try {
            if (userService.save(user) != null) {
                return "yes";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "no";
        }
        return "no";
    }
}
