package com.example.onlineshop.controller;

import com.example.onlineshop.entity.Product;
import com.example.onlineshop.exceptionHandler.CategoryNotFoundException;
import com.example.onlineshop.exceptionHandler.ProductNotFoundException;
import com.example.onlineshop.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/product")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }
    @GetMapping("/all")
    public List<Product> findAll() {
        return productService.findAll();
    }
    @PostMapping("/new")
    public Product save(@RequestBody Product product) {
        try {
            return productService.save(product);
        } catch (CategoryNotFoundException ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Category Not Found", ex);
        }
    }
    @GetMapping("/{id}")
    public Product findById(@PathVariable int id) {
        try {
            return productService.findById(id);
        }catch (ProductNotFoundException ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product Not Found");
        }
    }
    @DeleteMapping("/{id}/delete")
    private void delete(@PathVariable int id) {
        productService.deleteById(id);
    }
    @PutMapping("/{id}/edit")
    private Product update(@PathVariable int id, @RequestBody Product product) {
        try {
            return productService.update(id, product);
        } catch (ProductNotFoundException | CategoryNotFoundException ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product or Category Not found", ex);
        }
    }
}
