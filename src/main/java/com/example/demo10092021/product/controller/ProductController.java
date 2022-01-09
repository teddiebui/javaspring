package com.example.demo10092021.product.controller;

import com.example.demo10092021.product.model.ProductReview;
import com.example.demo10092021.product.model.WebsiteProduct;
import com.example.demo10092021.product.repo.ProductRepository;
import com.example.demo10092021.product.repo.ReviewRepository;
import com.example.demo10092021.user.model.EndUser;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProductController {

    private final ProductRepository productRepo;
    private final ReviewRepository reviewRepo;

    public ProductController(ProductRepository productRepo, ReviewRepository reviewRepo) {
        this.productRepo = productRepo;
        this.reviewRepo = reviewRepo;
    }

    @GetMapping("/product/get-all")
    public List<WebsiteProduct> getAllProduct() {
        //TODO: get all product
        return productRepo.findAll();
    }

    @PostMapping("/product/add")
    public String addProduct(
            @RequestBody WebsiteProduct product
    ) {
        //TODO: add
        try {
            productRepo.save(product);
        } catch (Exception e) {
            e.printStackTrace();
            return "no";
        }
        return "yes";
    }

    @GetMapping("/product/{id}")
    public WebsiteProduct getProduct(@PathVariable int id) {
        //TODO: get product by id
        return productRepo.findById(id);
    }


    @PostMapping("/product/{id}/delete")
    public String deleteProduct(
            @PathVariable int id,
            @RequestBody WebsiteProduct product
            ) {
        //TODO: delete
        try {
            product.setId(id);
            productRepo.delete(product);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("no");
        }
        return "yes";
    }

    @PostMapping("/product/{id}/update")
    public String updateProduct(
            @PathVariable int id,
            @RequestBody WebsiteProduct product) {
        //TODO: update product
        System.out.println(product);
        try {
            WebsiteProduct _product = productRepo.findById(id);
            _product.setPrice(product.getPrice());
            _product.setName(product.getName());
            _product.setCategories(product.getCategories());
            productRepo.save(_product);
        } catch (Exception e) {
            e.printStackTrace();
            return "no";
        }
        return "yes";
    }





}
