package com.example.demo10092021.product.repo;


import com.example.demo10092021.product.model.WebsiteProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface ProductRepository extends JpaRepository<WebsiteProduct, Integer>, CustomizeProductRepo {

    WebsiteProduct findById(int id);


}
