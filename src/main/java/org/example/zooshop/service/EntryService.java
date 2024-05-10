package org.example.zooshop.service;

import org.example.zooshop.entity.UserDTO;
import org.example.zooshop.entity.User;
import org.example.zooshop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EntryService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder encoder;

    public boolean addUser(UserDTO newUser) {
        System.out.println("Adding new user: " + newUser.getName());
        try {
            if (!userRepository.existsByName(newUser.getName())) {
                User user = new User();
                user.setName(newUser.getName());
                user.setEmail(newUser.getEmail());
                user.setPassword(encoder.encode(newUser.getPassword()));

                // Устанавливаем роль. Если не указана, то устанавливаем роль по умолчанию USER
                if(newUser.getRole() == null || newUser.getRole().isEmpty()) {
                    user.setRole("USER"); // Установка роли по умолчанию
                } else {
                    user.setRole(newUser.getRole()); // Установка указанной роли
                }

                userRepository.save(user);
                System.out.println("User added successfully: " + newUser.getName());
                return true;
            }
        } catch (Exception e) {
            System.out.println("Error adding user: " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }

    public Optional<User> findUserByName(String username) {
        return userRepository.findUserByName(username);
    }
}
