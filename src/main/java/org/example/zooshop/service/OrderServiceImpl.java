package org.example.zooshop.service;

import org.example.zooshop.entity.Order;
import org.example.zooshop.entity.User;
import org.example.zooshop.repository.OrderRepository;
import org.example.zooshop.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public void placeOrder(User user, String firstName, String lastName, String phoneNumber, String address, String productIds) {
        Order order = new Order();
        order.setUser(user);
        order.setFirstName(firstName);
        order.setLastName(lastName);
        order.setPhoneNumber(phoneNumber);
        order.setAddress(address);
        order.setProductIds(productIds);
        orderRepository.save(order);
    }
}
