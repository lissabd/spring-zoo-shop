package org.example.zooshop.service;

import org.example.zooshop.entity.User;

public interface OrderService {
    void placeOrder(User user, String firstName, String lastName, String phoneNumber, String address, String productIds);
}
