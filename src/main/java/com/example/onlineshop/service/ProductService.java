package com.example.onlineshop.service;

import com.example.onlineshop.entity.Category;
import com.example.onlineshop.entity.Product;
import com.example.onlineshop.exceptionHandler.CategoryNotFoundException;
import com.example.onlineshop.exceptionHandler.ProductNotFoundException;
import com.example.onlineshop.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final CategoryService categoryService;

    public ProductService(ProductRepository productRepository, CategoryService categoryService) {
        this.productRepository = productRepository;
        this.categoryService = categoryService;
    }

    public List<Product> findAll(){
        return productRepository.findAll();
    }

    public Product findById(int id) throws ProductNotFoundException {
        Optional<Product> productOptional = productRepository.findById(id);
        if(productOptional.isPresent()){
            return productOptional.get();
        }else {
            throw new ProductNotFoundException();
        }
    }
    public Product save(Product product) throws CategoryNotFoundException {
        try{
           Category category = categoryService.findByName(product.getCategory().getName());
           product.setCategory(category);
            return productRepository.save(product);
        } catch (CategoryNotFoundException ex) {
            throw new CategoryNotFoundException();
        }

    }

    public void deleteById(int id){
        productRepository.deleteById(id);
    }

    public Product update(int id, Product product) throws ProductNotFoundException, CategoryNotFoundException {
        Optional<Product> productOptional = productRepository.findById(id);
        if(productOptional.isPresent()){
            Product productFromDb = productOptional.get();
            try{
                Category category = categoryService.findByName(product.getCategory().getName());
                productFromDb.setCategory(category);
            } catch (CategoryNotFoundException ex) {
                throw new CategoryNotFoundException();
            }
            productFromDb.setTitle(product.getTitle());
            productFromDb.setPrice(product.getPrice());
            productFromDb.setPhotoUrl(product.getPhotoUrl());
            productFromDb.setDescription(product.getDescription());
            productFromDb.setFeatured(product.isFeatured());
            productFromDb.setQuantity(product.getQuantity());
            productRepository.save(productFromDb);
            return productFromDb;
        }else {
            throw new ProductNotFoundException();
        }
    }
}
