package com.example.demo10092021.product.repo;

import com.example.demo10092021.product.model.ProductRating;
import com.example.demo10092021.product.model.ProductReview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends JpaRepository<ProductReview, Integer> {

    ProductReview findById(int id);
}
