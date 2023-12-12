package com.example.onck.repositories;

import com.example.onck.models.ProductPrice;

import com.example.onck.pk.ProductPricePK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductPriceRepository extends JpaRepository<ProductPrice, ProductPricePK> {

            @Query("select pp from ProductPrice  pp where pp.product.productId =:id")
            List<ProductPrice> findProductPriceByProduct(@Param("id")long id);




}