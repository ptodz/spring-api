package com.codecourse.store.repositories;

import com.codecourse.store.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> getProductsByCategoryId(@Param("categoryId") Byte categoryId);
}