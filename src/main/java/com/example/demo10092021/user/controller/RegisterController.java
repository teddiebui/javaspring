package com.example.demo10092021.user.controller;

import com.example.demo10092021.user.model.EndUser;
import com.example.demo10092021.user.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.View;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
public class RegisterController {

    private final UserService userService;

    public RegisterController(UserService service) {
        this.userService = service;
    }

    @PostMapping("/register")
    public String register(
            @Valid @RequestBody EndUser user,
            BindingResult result,
            HttpServletRequest request
    ) {
        System.out.println("DEBUG: --- THIS IS CONTROLLER REGISTER");

        if (result.hasErrors()) {

        }
        if (userService.save(user) == null) {
            System.out.println("ERROR SAVING USER...");
            return null;
        }
        // code nay cho phep redirect tu post sang post, giu nguyen headers and body
        request.setAttribute(View.RESPONSE_STATUS_ATTRIBUTE, HttpStatus.PERMANENT_REDIRECT);
        return "redirect:/login-handle";
    }

}
