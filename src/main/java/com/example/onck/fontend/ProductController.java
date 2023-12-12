package com.example.onck.fontend;

import com.example.onck.enums.ProductStatus;
import com.example.onck.models.Product;
import com.example.onck.models.ProductPrice;
import com.example.onck.repositories.ProductPriceRepository;
import com.example.onck.repositories.ProductRepository;
import com.example.onck.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequiredArgsConstructor

public class ProductController {
    @Autowired
    ProductRepository productRepository;
    @Autowired
    ProductPriceRepository productPriceRepository;

    @Autowired
    ProductService productService;
        // ty lam tiep
    @GetMapping("/product-paging")
    public String findAll(Model model,
                          @RequestParam("pageNumber") Optional<Integer> pageNumber,
                          @RequestParam("pageSize") Optional<Integer> pageSize){
        int page = pageNumber.orElse(0);
        int size = pageSize.orElse(10);
        Sort sort =Sort.by(Sort.Direction.fromString("desc"),"productId");
        Pageable pageable = PageRequest.of(page,size,sort);
        Page<Product> paging = productService.findALL(pageable);
        // cách 2
//        Page<Product> paging = productRepository.findAll(pageable);
        model.addAttribute("paging",paging);

        int totalPage = paging.getTotalPages();
        model.addAttribute("totalPage",totalPage);
        if(totalPage>0){
            List<Integer> pageNumbers = IntStream.rangeClosed(0,totalPage-1).boxed().collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }
        return "products";
    }

    @GetMapping("/detail/{id}")
    public String getDetail(Model model, @PathVariable("id" )long id){
        Product product = productService.findById(id).orElse(null);
        model.addAttribute("product",product);
        List<ProductPrice> productPrices = productService.findPriceById(id);
        model.addAttribute("productPrices",productPrices);
        return "product_detail";
    }
    // delete

    @GetMapping("/delete/{id}")
    public  String deleteID(Model model,@PathVariable("id")long id){
            Product product = productRepository.findById(id).orElse(null);
            product.setStatus(ProductStatus.INACTIVE);
            productRepository.save(product);
            return "redirect:/product-paging";

    }

    //add
    @GetMapping("/add")
    public String showADD(Model model){
        model.addAttribute("product",new Product());
        model.addAttribute("listStatus",ProductStatus.values());
        model.addAttribute("productPrice",new ProductPrice());
        return ("product_add");

    }

    @PostMapping("/add-product")
    public  String addProduct(Model model, @ModelAttribute Product product,
                              @ModelAttribute ProductPrice productPrice){
//        productRepository.save(product);
        //cạc 2
        productService.saveProduct(product);
        productPrice.setProduct(product);
        productPrice.setPriceDateTime(LocalDateTime.now());
        productPrice.setNote("NVV");
        //productPriceRepository.save(productPrice);
        productService.saveProductPrice(productPrice);
        return "redirect:/product-paging";


    }

    //

    @GetMapping("/search-by-name")
    public String searchByName(Model model, @RequestParam("pageNumber") Optional<Integer> pageNumber,
                               @RequestParam("pageSize") Optional<Integer> pageSize, @RequestParam("name") String name){
        int page = pageNumber.orElse(0);
        int size = pageSize.orElse(10);
        Sort sort = Sort.by(Sort.Direction.fromString("desc"),"productId");
        Pageable pageable = PageRequest.of(page,size,sort);
        Page<Product> paging = productService.findByName(pageable,name);
        model.addAttribute("paging", paging);

        int totalPage = paging.getTotalPages();
        model.addAttribute("totalPage",totalPage);
        if(totalPage>0){
            List<Integer> pageNumbers = IntStream.rangeClosed(0,totalPage-1).boxed().collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }

        model.addAttribute("nameSearch",name);
        return  "product_search";
    }

    @GetMapping("/search-by-status")
    public String searchByStatus(Model model, @RequestParam("pageNumber") Optional<Integer> pageNumber,
                               @RequestParam("pageSize") Optional<Integer> pageSize, @RequestParam("status") String status){
        int page = pageNumber.orElse(0);
        int size = pageSize.orElse(10);
        Sort sort = Sort.by(Sort.Direction.fromString("desc"),"productId");
        Pageable pageable = PageRequest.of(page,size,sort);
        Page<Product> paging = productService.findByStatus(pageable, ProductStatus.valueOf(status));
        model.addAttribute("paging", paging);

        int totalPage = paging.getTotalPages();
        model.addAttribute("totalPage",totalPage);
        if(totalPage>0){
            List<Integer> pageNumbers = IntStream.rangeClosed(0,totalPage-1).boxed().collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }

        model.addAttribute("statusSearch",status);
        return  "status_search";
    }







}
