package com.example.onck.pk;

import com.example.onck.models.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductPricePK implements Serializable {
    private Product product;
    private LocalDateTime priceDateTime;
}
