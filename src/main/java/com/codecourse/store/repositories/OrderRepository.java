package com.codecourse.store.repositories;

import com.codecourse.store.entities.Order;
import com.codecourse.store.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findAllByCustomer(User user);
}