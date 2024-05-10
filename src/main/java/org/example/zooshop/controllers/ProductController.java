package org.example.zooshop.controllers;

import org.example.zooshop.entity.Product;
import org.example.zooshop.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Controller
public class ProductController {

    @Value("${upload.path}")
    private String uploadPath;

    private final ProductRepository productRepository;

    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping("/products")
    public String products(Model model) {
        List<Product> products = productRepository.findAll();
        model.addAttribute("products", products);
        return "products";
    }
    @PostMapping("/add_product")
    public String addProduct(@RequestParam String title,
                             @RequestParam String category,
                             @RequestParam double cost,
                             @RequestParam("file") MultipartFile file) throws IOException {
        if (file != null && !file.isEmpty()) {
            String fileName = title + ".jpg";
            Path uploadDir = Paths.get(uploadPath);
            String relativeFilePath = "images/" + fileName;
            Path filePath = uploadDir.resolve(fileName);
            Files.copy(file.getInputStream(), filePath);
            Product product = new Product(title, category, cost, relativeFilePath);
            productRepository.save(product);
        } else {
            Product product = new Product(title, category, cost);
            productRepository.save(product);
        }
        return "redirect:/admin_panel";

    }


    @PostMapping("/delete_product")
    public String deleteProduct(@RequestParam Long productId) {
        productRepository.deleteById(productId);
        return "redirect:/admin_panel";
    }



}
