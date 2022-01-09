package com.example.demo10092021.product.repo;

import com.example.demo10092021.product.model.ProductRating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RatingRepository extends JpaRepository<ProductRating, Integer> {
}
