package com.example.demo10092021.product.controller;

import com.example.demo10092021.product.repo.RatingRepository;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RatingController {
    private final RatingRepository ratingRepo;

    public RatingController(RatingRepository ratingRepo) {
        this.ratingRepo = ratingRepo;
    }

    @PostMapping("/product/{id}/add-rating")
    public String addRating(
            @PathVariable int id
    ) {
        return null;
    }

    @PostMapping("/product/{id}/edit-rating")
    public String editRating(
            @PathVariable int id
    ) {
        return null;
    }
}
