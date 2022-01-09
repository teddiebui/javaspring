package com.example.demo10092021.category.controller;

import com.example.demo10092021.category.model.ProductCategory;
import com.example.demo10092021.category.repo.CategoryRepository;
import com.example.demo10092021.product.model.WebsiteProduct;
import com.example.demo10092021.product.repo.ProductRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
public class CategoryController {

    private final CategoryRepository cateRepo;
    private final ProductRepository productRepo;

    public CategoryController(CategoryRepository cateRepo, ProductRepository productRepo) {
        this.cateRepo = cateRepo;
        this.productRepo = productRepo;
    }

    @GetMapping("/category/get-all")
    public List<ProductCategory> getAllCategory() {
        //TODO: get all product category
        return cateRepo.findAll();
    }

    @GetMapping("/category/{id}")
    public ProductCategory getAllCategory(@PathVariable int id) {
        //TODO: get product category
        return cateRepo.findById(id);
    }

    @GetMapping("/category/{id}/update")
    public String updateCategory(
            @PathVariable int id,
            @RequestBody ProductCategory category
    ) {
        //TODO: get all product category
        try {
            ProductCategory _category = cateRepo.findById(id);
            _category.setName(_category.getName());
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("no");
        }
        return "yes";
    }

    @GetMapping("/category/{id}/delete")
    public String deleteCategory(
            @PathVariable int id,
            @RequestBody ProductCategory category
    ) {
        //TODO: get all product category
        return null;
    }

    @PostMapping("/category/add")
    public String getAllCategory(
            @RequestBody ProductCategory category
    ) {
        //TODO: add product category
        System.out.println(category);
        try {
            cateRepo.save(category);
        } catch (Exception e) {
            return "no";
        }
        return "yes";
    }

    @GetMapping("/category/{id}/all-product")
    public Set<WebsiteProduct> getAllProduct(
            @PathVariable int id
    ) {
        //TODO: get all product category

        ProductCategory _category = cateRepo.findById(id);
        return _category.getProducts();
    }
}
