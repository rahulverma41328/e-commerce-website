package com.rahul.eCommerce.controller;

import com.rahul.eCommerce.dao.ClientRepository;
import com.rahul.eCommerce.dao.ImageRepository;
import com.rahul.eCommerce.entities.Image;
import com.rahul.eCommerce.entities.Product;
import com.rahul.eCommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ClientController {

    @Autowired
    private ProductService service;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private ImageRepository imageRepository;

    @GetMapping("/")
    public String home(Model model){
        service.getProducts(model);
        return "index";
    }

    @GetMapping("/product-details/{id}")
    public String detailPage(@PathVariable int id,Model model){
        Product product = clientRepository.findById(id).orElseThrow();
        System.out.println(product);
        model.addAttribute(product);
        return "product-details";
    }

    @GetMapping("/store-data")
    public String storeData(){
        return "store-data";
    }

    @GetMapping("/account")
    public String account(){
        return "account";
    }


    @PostMapping("/save-data")
    public String saveData(@RequestParam("productName") String productName,
                           @RequestParam("productPrice") String productPrice,
                           @RequestParam("productCategory") String productCategory,
                           @RequestParam("productDescription") String productDescription,
                           @RequestParam("image")List<MultipartFile> images, Model model) throws IOException {

        List<String> imagesName = new ArrayList<>();

        for (MultipartFile image: images){
            imagesName.add(image.getOriginalFilename());
        }
       // System.out.println(imagesName.toString());

        Product product = new Product(productName,productPrice,productDescription,productCategory);
       // System.out.println(product);

        service.saveProductWithImages(product,images);

        return "store-data";
    }


    @GetMapping("/client-product")
    public String clientProduct(Model model){
        return "client-products";
    }
}
