package com.example.onlineshop.service;

import com.example.onlineshop.entity.Category;
import com.example.onlineshop.exceptionHandler.CategoryNotFoundException;
import com.example.onlineshop.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<Category> categoryList() {
        return categoryRepository.findAll();
    }

    public Category findCategoryById(int id) throws CategoryNotFoundException {
        Optional<Category> categoryOptional = categoryRepository.findById(id);
        if(categoryOptional.isPresent()){
            return categoryOptional.get();
        }else{
       throw new CategoryNotFoundException();}
    }

    public Category saveCategory(Category category) {
        return categoryRepository.save(category);
    }

    public void deleteById(int id) {
        categoryRepository.deleteById(id);
    }
    public Category update(int id, Category category) throws CategoryNotFoundException {
        Optional<Category> categoryOptionalFromDb = categoryRepository.findById(id);
        if(categoryOptionalFromDb.isPresent()){
        Category categoryFromDb = categoryOptionalFromDb.get();
        categoryFromDb.setName(category.getName());
        categoryFromDb.setPhotoUrl(category.getPhotoUrl());
        categoryRepository.save(categoryFromDb);
        return categoryFromDb;
        }else{
            throw new CategoryNotFoundException();}
    }

    public Category findByName(String name) throws CategoryNotFoundException {
        Optional<Category> categoryOptional = categoryRepository.findByName(name);
        if(categoryOptional.isPresent()){
            return categoryOptional.get();
        }else{
            throw new CategoryNotFoundException();}
    }
    }


