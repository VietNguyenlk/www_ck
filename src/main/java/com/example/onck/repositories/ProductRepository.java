package com.example.onck.repositories;

import com.example.onck.enums.ProductStatus;
import com.example.onck.models.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Page<Product> findAllByNameContainingIgnoreCase(Pageable pageable, String name);
    // search = status
    Page<Product> findAllByStatus(Pageable pageable, ProductStatus status);
}