package org.example.zooshop.controllers;

import org.example.zooshop.entity.CartItem;
import org.example.zooshop.entity.User;
import org.example.zooshop.service.CartService;
import org.example.zooshop.service.OrderService;
import org.example.zooshop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/placeOrder")
public class OrderController {

    private final OrderService orderService;
    private final UserService userService;

    private  final CartService cartService;

    @Autowired
    public OrderController(OrderService orderService, UserService userService, CartService cartService) {
        this.orderService = orderService;
        this.userService = userService;
        this.cartService = cartService;
    }

    @PostMapping
    public String placeOrder(String firstName, String lastName, String phoneNumber, String address, String productIds) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserName = authentication.getName();
        User currentUser = userService.getUserByName(currentUserName);


        List<CartItem> cartItems = cartService.getCartItemsForUser(currentUser);
        List<String> productIdsList = cartItems.stream().map(cartItem -> cartItem.getProduct().getId().toString()).collect(Collectors.toList());


        orderService.placeOrder(currentUser, firstName, lastName, phoneNumber, address, productIds.join(" ", productIdsList));

        cartService.clearCart(currentUser);


        return "redirect:/home";
    }

}
