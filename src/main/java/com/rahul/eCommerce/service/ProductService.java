package com.rahul.eCommerce.service;

import com.rahul.eCommerce.dao.ClientRepository;
import com.rahul.eCommerce.dao.ImageRepository;
import com.rahul.eCommerce.entities.Image;
import com.rahul.eCommerce.entities.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    ClientRepository clientRepository;

    @Autowired
    ImageRepository imageRepository;

    private static final String UPLOAD_DIRECTORY = "src/main/resources/static/images";


    public void saveProductWithImages(Product product, List<MultipartFile> files) throws IOException {
        Image image = new Image();

        for (int i=0;i<files.size();i++){
            image.setImage1(uploadFile(files.get(0)));
            image.setImage2(uploadFile(files.get(1)));
            image.setImage3(uploadFile(files.get(2)));
            image.setImage4(uploadFile(files.get(3)));
            image.setImage5(uploadFile(files.get(4)));
        }
        imageRepository.save(image);
        product.setImageSet(image);
        clientRepository.save(product);
    }

    private String uploadFile(MultipartFile file) throws IOException {
        String fileName = file.getOriginalFilename();
        Path path = Paths.get(UPLOAD_DIRECTORY+ File.separator+file.getOriginalFilename());
        Files.copy(file.getInputStream(),path, StandardCopyOption.REPLACE_EXISTING);
        return fileName;
    }


    public void getProducts(Model model) {
        List<Product> products = (List<Product>) clientRepository.findAll();
        model.addAttribute("products",products);
    }
}
