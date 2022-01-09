package com.example.demo10092021.order.repo;

import com.example.demo10092021.order.model.UserOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<UserOrder, Integer> {
    public UserOrder findById(int id);
}
