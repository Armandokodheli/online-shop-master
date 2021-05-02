package com.example.onlineshop.controller;

import com.example.onlineshop.entity.Category;
import com.example.onlineshop.exceptionHandler.CategoryNotFoundException;
import com.example.onlineshop.service.CategoryService;
import org.aspectj.bridge.MessageUtil;
import org.hibernate.ObjectNotFoundException;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/category")
public class CategoryController {
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/all")
    public List<Category> findAll() {
        return categoryService.categoryList();
    }

    @GetMapping("/{id}")
    public Category findById(@PathVariable("id") int id) {

        try {
            return categoryService.findCategoryById(id);

        } catch (CategoryNotFoundException c) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Category Not Found", c);
        }

    }

    @PostMapping("/new")
    public Category save(@RequestBody Category category) {
        return categoryService.saveCategory(category);
    }

    @DeleteMapping("/{id}/delete")
    public void deleteById(@PathVariable int id) {
        categoryService.deleteById(id);
    }

    @PutMapping("/{id}/edit")
    public Category updateCategoryById(@PathVariable int id, @RequestBody Category category) {
        try {
            return categoryService.update(id, category);
        } catch (CategoryNotFoundException ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Category Not Found", ex);
        }
    }

}
