package org.example.zooshop.service;

import org.example.zooshop.entity.CartItem;
import org.example.zooshop.entity.User;
import java.util.List;

public interface CartService {
    void addToCart(User user, Long productId, int quantity);
    void removeFromCart(User user, Long cartItemId);
    void updateCartItemQuantity(User user, Long cartItemId, int quantity);
    List<CartItem> getCartItemsForUser(User user);
    void clearCart(User user);
}
