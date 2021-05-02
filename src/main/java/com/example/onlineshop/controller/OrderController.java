package com.example.onlineshop.controller;

import com.example.onlineshop.entity.Order;
import com.example.onlineshop.exceptionHandler.CategoryNotFoundException;
import com.example.onlineshop.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/order")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/all")
    public List<Order> findAll() {
        return orderService.findAll();
    }

    @GetMapping("/{id}")
    public Order findById(int id) {
        try {
            return orderService.findById(id);
        } catch (CategoryNotFoundException c) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Order not found", c);

        }

    }
    @PostMapping("/new")
    public Order save(@RequestBody Order order){
        return orderService.save(order);
    }

    @DeleteMapping("/{id}delete")
    public void  deleteById(int id){
        orderService.deleteById(id);
    }
}
