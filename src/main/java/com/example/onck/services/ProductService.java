package com.example.onck.services;

import com.example.onck.enums.ProductStatus;
import com.example.onck.models.Product;
import com.example.onck.models.ProductPrice;
import com.example.onck.repositories.ProductPriceRepository;
import com.example.onck.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {
    @Autowired
    ProductRepository productRepository;
    @Autowired
    ProductPriceRepository productPriceRepository;


    // phan trang
    public Page<Product> findALL(Pageable pageable){
        return  productRepository.findAll(pageable);
    }

    public Optional<Product> findById(long id){
        return productRepository.findById(id);
    }

    public List<ProductPrice> findPriceById(long id){
        return productPriceRepository.findProductPriceByProduct(id);
    }

    public Product saveProduct(Product product){
        return productRepository.save(product);
    }

    public ProductPrice saveProductPrice(ProductPrice productPrice){
        return  productPriceRepository.save(productPrice);
    }
    public Page<Product> findByName(Pageable pageable, String name) {
        return productRepository.findAllByNameContainingIgnoreCase(pageable,name);
    }

    public Page<Product> findByStatus(Pageable pageable, ProductStatus status) {
        return productRepository.findAllByStatus(pageable,status);
    }

}
