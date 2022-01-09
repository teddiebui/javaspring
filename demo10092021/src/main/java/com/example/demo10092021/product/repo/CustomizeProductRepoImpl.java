package com.example.demo10092021.product.repo;

import com.example.demo10092021.FilterCriteria;
import com.example.demo10092021.product.model.WebsiteProduct;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class CustomizeProductRepoImpl implements CustomizeProductRepo{

    private final EntityManager entityManager;

    @Autowired
    public CustomizeProductRepoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<WebsiteProduct> customQuery(List<FilterCriteria> criterias) {

        String queryString = "SELECT p FROM Product p WHERE " + convertToSQL(criterias);

        //still work, let's try another way
        TypedQuery<WebsiteProduct> query = entityManager.createQuery(queryString, WebsiteProduct.class);

        for (FilterCriteria f: criterias) {
            query.setParameter(f.getIdentity(), f.getValue());
        }

        System.out.println("------------debug:");
        System.out.println(queryString);
        System.out.println("----------------------");
        return query.getResultList();
//        return null;
    }

    private static String convertToSQL(List<FilterCriteria> filterCriterias) {

        StringBuilder ans = new StringBuilder("1 = 1 ");
        int index = 0;
        for (FilterCriteria f: filterCriterias) {
            index++;
            String identity = f.getField() + index;
            f.setIdentity(identity);


            ans.append("AND ");
            ans.append(f.getField()).append(" ")
                .append(f.getOperator()).append(" ")
                .append(":").append(f.getIdentity()).append(" ")
            ;
        }

//        System.out.println("---debug:" + ans.toString());
//        System.out.println("------------");
        return ans.toString();
    }
}
