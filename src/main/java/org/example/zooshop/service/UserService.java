package org.example.zooshop.service;

import org.example.zooshop.entity.CartItem;
import org.example.zooshop.entity.User;
import org.example.zooshop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;
import java.util.Optional;

public interface UserService extends UserDetailsService {
    void save(User user);

    User getUserById(Long id);
    User getUserByName(String username);
    List<CartItem> getCartItemsForUser(User user);
    void clearCart(User user);






}
