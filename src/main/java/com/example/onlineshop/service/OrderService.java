package com.example.onlineshop.service;

import com.example.onlineshop.entity.Order;
import com.example.onlineshop.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public List<Order> findAll(){
        return orderRepository.findAll();
    }
    public Order findById(int id){
        return orderRepository.findById(id).orElseThrow(() ->new RuntimeException("not found"));
    }
    public Order save(Order order){
        return orderRepository.save(order);
    }
    public void deleteById(int id){
        orderRepository.deleteById(id);
    }
}
