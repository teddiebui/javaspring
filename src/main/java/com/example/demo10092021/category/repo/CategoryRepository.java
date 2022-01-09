package com.example.demo10092021.category.repo;

import com.example.demo10092021.category.model.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<ProductCategory, Integer> {

    ProductCategory findById(int id);
}
