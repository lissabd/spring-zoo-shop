package org.example.zooshop.service;

import org.example.zooshop.entity.CartItem;
import org.example.zooshop.entity.Product;
import org.example.zooshop.entity.User;
import org.example.zooshop.repository.CartItemRepository;
import org.example.zooshop.repository.ProductRepository;
import org.example.zooshop.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartServiceImpl implements CartService {

    private final CartItemRepository cartItemRepository;
    private final ProductRepository productRepository;


    @Autowired
    public CartServiceImpl(CartItemRepository cartItemRepository, ProductRepository productRepository) {
        this.cartItemRepository = cartItemRepository;
        this.productRepository = productRepository;

    }

    @Override
    public void addToCart(User user, Long productId, int quantity) {
        Optional<Product> productOptional = productRepository.findById(productId);
        if (productOptional.isPresent()) {
            Product product = productOptional.get();
            CartItem cartItem = cartItemRepository.findByUserAndProduct(user, product);
            if (cartItem != null) {
                cartItem.setQuantity(cartItem.getQuantity() + quantity);
            } else {
                cartItem = new CartItem();
                cartItem.setUser(user);
                cartItem.setProduct(product);
                cartItem.setQuantity(quantity);

            }
            cartItemRepository.save(cartItem);
        } else {
            throw new IllegalArgumentException("Product not found with id: " + productId);
        }
    }


    @Override
    public void removeFromCart(User user, Long cartItemId) {
        Optional<CartItem> cartItemOptional = cartItemRepository.findById(cartItemId);
        if (cartItemOptional.isPresent()) {
            CartItem cartItem = cartItemOptional.get();
            if (cartItem.getUser().equals(user)) {
                cartItemRepository.deleteById(cartItemId);
            } else {
                throw new IllegalArgumentException("CartItem does not belong to the current user");
            }
        } else {
            throw new IllegalArgumentException("CartItem not found with id: " + cartItemId);
        }
    }

    @Override
    public void updateCartItemQuantity(User user, Long cartItemId, int quantity) {
        Optional<CartItem> cartItemOptional = cartItemRepository.findById(cartItemId);
        if (cartItemOptional.isPresent()) {
            CartItem cartItem = cartItemOptional.get();
            if (cartItem.getUser().equals(user)) {
                cartItem.setQuantity(quantity);
                cartItemRepository.save(cartItem);
            } else {
                throw new IllegalArgumentException("CartItem does not belong to the current user");
            }
        } else {
            throw new IllegalArgumentException("CartItem not found with id: " + cartItemId);
        }
    }
    @Override
    public List<CartItem> getCartItemsForUser(User user) {
        return cartItemRepository.findByUser(user);
    }

    @Override
    public void clearCart(User user) {
        List<CartItem> cartItems = cartItemRepository.findByUser(user);
        cartItemRepository.deleteAll(cartItems);
    }

}
