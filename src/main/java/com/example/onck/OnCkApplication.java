package com.example.onck;

import com.example.onck.enums.ProductStatus;
import com.example.onck.models.Product;
import com.example.onck.models.ProductPrice;
import com.example.onck.repositories.ProductPriceRepository;
import com.example.onck.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import net.datafaker.Faker;
import net.datafaker.providers.base.Device;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;
import java.util.Random;

@SpringBootApplication
@RequiredArgsConstructor
public class OnCkApplication {

    public static void main(String[] args) {
        SpringApplication.run(OnCkApplication.class, args);
    }

    @Autowired
    ProductRepository productRepository;
    @Autowired
    ProductPriceRepository productPriceRepository;

//    @Bean
//    CommandLineRunner init(){
//        return args -> {
//            Faker faker = new Faker();
//            Device device = faker.device();
//            Random random = new Random();
//
//            for (int i = 0; i < 100; i++) {
//                Product product = new Product(device.modelName(),device.platform(),device.platform(),device.manufacturer(), ProductStatus.ACTIVE);
//                int ran = random.nextInt(2);
//                if(ran==0) product.setStatus(ProductStatus.ACTIVE);
//                if(ran==1)product.setStatus(ProductStatus.INACTIVE);
//                productRepository.save(product);
//                for (int j = 0; j < 3; j++) {
//                    ProductPrice productPrice = new ProductPrice(product, LocalDateTime.now().plusDays(j),random.nextDouble(200),"Viet Ne");
//                    productPriceRepository.save(productPrice);
//                }
//
//
//            }
//        };
//    }

}
