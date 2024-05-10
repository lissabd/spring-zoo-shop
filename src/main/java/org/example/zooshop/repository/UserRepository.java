package org.example.zooshop.repository;

import org.example.zooshop.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findUserByName(String username);

    User getUserByName(String username);

    boolean existsByName(String username);

    List<User> findByRole(String role);
}
