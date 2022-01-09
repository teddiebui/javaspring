package com.example.demo10092021;

import com.example.demo10092021.category.model.ProductCategory;
import com.example.demo10092021.user.dto.UserDTO;
import com.example.demo10092021.user.model.EndUser;
import com.example.demo10092021.user.model.UserAuthority;
import com.example.demo10092021.user.repo.AuthorityRepository;
import com.example.demo10092021.order.model.UserOrder;
import com.example.demo10092021.product.model.WebsiteProduct;
import com.example.demo10092021.category.repo.CategoryRepository;
import com.example.demo10092021.order.repo.OrderRepository;
import com.example.demo10092021.product.repo.ProductRepository;
import com.example.demo10092021.user.service.UserService;
import com.example.demo10092021.product.model.ProductRating;
import com.example.demo10092021.product.model.ProductReview;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.*;

@Controller
public class NormalController {



    @GetMapping("/")
    public ModelAndView index(ModelAndView mav) {
        Authentication token = SecurityContextHolder.getContext().getAuthentication();
        System.out.println(token);
        System.out.println(token.getPrincipal());
        if (token.getPrincipal().equals("anonymousUser") || token.getPrincipal() == null || token == null) {
            mav.addObject("myUser", null);
        } else {
            EndUser user = (EndUser) token.getPrincipal();
        }

        mav.setViewName("index");
        return mav;
    }


    @GetMapping("/error")
    public String admin() {
        return "error";
    }


    @GetMapping("/admin")
    public ModelAndView admin(ModelAndView mav) {
        System.out.println("Controller - admin");

        Authentication token = SecurityContextHolder.getContext().getAuthentication();
        System.out.println("check again authentication: " + token);
        EndUser user = (EndUser)token.getPrincipal();

        mav.addObject("user", user);
        mav.setViewName("admin");
        return mav;
    }

    @GetMapping("/unauthorized")
    public String unauthorized() {
        System.out.println("CONTROLLER: /unauthorized");
            return "unauthorized";
    }

    @GetMapping("/accountinfo")
    public ModelAndView accountInfo(ModelAndView mav) {

        Authentication auth =SecurityContextHolder.getContext().getAuthentication();

        mav.addObject("myUser", (EndUser) auth.getPrincipal());
        mav.setViewName("accountinfo");
        return mav;
    }




//    @GetMapping("/testing")
//    public String testing() {
//        System.out.println("--------DO THE TEST");
//
//        UserAuthority a1 = new UserAuthority();
//        a1.setName("user");
//
//        UserAuthority a2 = new UserAuthority();
//        a2.setName("admin");
//
//        authRepo.save(a1);
//        authRepo.save(a2);
//
//        System.out.println("save all authorities");
//        List<UserAuthority> authorities = authRepo.findAll();
//
//        EndUser u1 = new EndUser();
//        u1.setUsername("test1");
//        u1.setPassword(passwordEncoder.encode("123456"));
//        u1.setEnabled(true);
//        u1.setEmail("test@gmail.com");
//        u1.addAuthority(authorities.get(0));
//        u1.addAuthority(authorities.get(1));
//
//        EndUser u2 = new EndUser();
//        u2.setUsername("test2");
//        u2.setPassword(passwordEncoder.encode("123456"));
//        u2.setEnabled(true);
//        u2.setEmail("test@gmail.com");
//        u2.addAuthority(authorities.get(0));
//        u2.addAuthority(authorities.get(1));
//
//        EndUser u3 = new EndUser();
//        u3.setUsername("test3");
//        u3.setPassword(passwordEncoder.encode("123456"));
//        u3.setEnabled(true);
//        u3.setEmail("test@gmail.com");
//        u3.addAuthority(authorities.get(0));
//        u3.addAuthority(authorities.get(1));
//
//        System.out.println("----- savig user");
//
//        userService.save(u1);
//        userService.save(u2);
//        userService.save(u3);
//
//
//        ProductCategory ca1 = new ProductCategory();
//        ca1.setName("Ao");
//        ProductCategory ca2 = new ProductCategory();
//        ca2.setName("Quan");
//        ProductCategory ca3 = new ProductCategory();
//        ca3.setName("Do gia dung");
//
//        cateRepo.save(ca1);
//        cateRepo.save(ca2);
//        cateRepo.save(ca3);
//
////        ------
//        List<ProductCategory> categories = cateRepo.findAll();
//        List<EndUser> users = userService.findAll();
////        System.out.println(categories);
//
//        WebsiteProduct p1 = new WebsiteProduct();
//        p1.setName("Ao thun");
//        p1.setPrice(499);
//        p1.setUserId(users.get(2).getId());
//        categories.get(0).addProduct(p1);
//
//
//        WebsiteProduct p2 = new WebsiteProduct();
//        p2.setName("Ao Somi");
//        p2.setPrice(899);
//        p2.setUserId(users.get(1).getId());
//        p2.addCategory(categories.get(0));
//
//        WebsiteProduct p3 = new WebsiteProduct();
//        p3.setName("Quan tay");
//        p3.setPrice(1399);
//        p3.setUserId(users.get(1).getId());
//        p3.addCategory(categories.get(1));
////
//        System.out.println("----- savig product");
//        productRepo.save(p1);
//        productRepo.save(p2);
//        productRepo.save(p3);
//
//        List<ProductCategory> _categories = cateRepo.findAll();
////        System.out.println(_categories);
////
//        List<WebsiteProduct> products = productRepo.findAll();
//
//        ProductReview r1 = new ProductReview();
//        r1.setContent("san pham rat dep");
//        r1.setUserId(users.get(0).getId());
//
//        ProductReview r2 = new ProductReview();
//        r2.setContent("san pham rat hay");
//        r2.setUserId(users.get(0).getId());
//
//        ProductReview r3 = new ProductReview();
//        r3.setContent("san pham rat cong dung");
//        r3.setUserId(users.get(1).getId());
//
//        products.get(1).addReview(r1);
//        products.get(1).addReview(r2);
//        products.get(1).addReview(r3);
//        productRepo.save(products.get(1));
//
//
//        ProductRating ra1 = new ProductRating();
//        ra1.setRating(4);
//        ra1.setUserId(users.get(0).getId());
//
//        ProductRating ra2 = new ProductRating();
//        ra2.setRating(3);
//        ra2.setUserId(users.get(1).getId());
//
//        ProductRating ra3 = new ProductRating();
//        ra3.setRating(1);
//        ra3.setUserId(users.get(2).getId());
//
//        products.get(1).addRating(ra1);
//        products.get(1).addRating(ra2);
//        products.get(1).addRating(ra3);
//        productRepo.save(products.get(1));
//
////        System.out.println(products);
//
//        //testing creation time stamp and upate time stamp
//        users.get(0).setEmail("teddie@gmail.com");
//        userService.save(users.get(0));
//        System.out.println("updated user");
//
//        System.out.println("--------FINISHED TESTING");
//
//
//        UserOrder o1 = new UserOrder();
//        o1.addProduct(products);
//        o1.setUserId(users.get(0).getId());
//        orderRepo.save(o1);
//
//
//        List<UserAuthority> authoritiesList = authRepo.findAll();
//        authoritiesList.isEmpty();
//
//        return null;
//    }
//
//    @GetMapping("/testing2")
//    public String testing2() {
//
//        List<EndUser> users = userService.findAll();
//
//        System.out.println("///////////////////////////////////////////");
//        UserDTO userDTO = userService.loadUserByUsername("test40");
//        System.out.println("///////////////////////////////////////////");
//        Set<UserAuthority> authorities = userDTO.getUser().getAuthorities();
//
//
//        return null;
//    }


}
