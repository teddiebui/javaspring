package com.example.demo10092021.product.controller;


import com.example.demo10092021.product.model.ProductRating;
import com.example.demo10092021.product.model.ProductReview;
import com.example.demo10092021.product.repo.ReviewRepository;
import com.example.demo10092021.user.model.EndUser;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;


@RestController
public class ReviewController {

    private final ReviewRepository reviewRepo;

    public ReviewController(ReviewRepository reviewRepo) {
        this.reviewRepo = reviewRepo;
    }

    @PostMapping("/product/{id}/review/add")
    public ProductReview addReview(
            @PathVariable("id") int id,
            @RequestBody ProductReview review
    ) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        review.setProductId(id);
        review.setUserId(((EndUser)auth.getPrincipal()).getId());
        try {
            reviewRepo.save(review);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return review;
    }



    @PostMapping("/product/{id}/review/{review_id}/edit")
    public String editRating(
            @PathVariable("id") int id,
            @PathVariable("review_id") int reviewId,
            @RequestBody ProductReview review
    ) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        review.setProductId(id);
        review.setUserId(((EndUser)auth.getPrincipal()).getId());
        try {
            ProductReview _review = reviewRepo.findById(reviewId);
            _review.setContent(review.getContent());
            reviewRepo.save(review);

        } catch (Exception e) {
            e.printStackTrace();
            return "no";
        }
        return "yes";
    }

    @PostMapping("/product/{id}/review/delete")
    public String deleteRating(
            @PathVariable int id
    ) {
        return null;
    }
}
