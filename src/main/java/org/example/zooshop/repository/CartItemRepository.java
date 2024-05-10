package org.example.zooshop.repository;

import org.example.zooshop.entity.CartItem;
import org.example.zooshop.entity.Product;
import org.example.zooshop.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    CartItem findByUserAndProduct(User user, Product product);
    List<CartItem> findByUser(User user);

}
