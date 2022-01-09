package com.example.demo10092021.product.repo;

import com.example.demo10092021.FilterCriteria;
import com.example.demo10092021.product.model.WebsiteProduct;

import java.util.List;

public interface CustomizeProductRepo {
    List<WebsiteProduct> customQuery(List<FilterCriteria> criterias);
}
