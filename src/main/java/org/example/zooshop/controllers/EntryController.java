package org.example.zooshop.controllers;

import org.example.zooshop.entity.*;
import org.example.zooshop.service.EntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

@Controller
public class EntryController {

    @Autowired
    EntryService entryService;

    @GetMapping("/register")
    public String regGet(Model model) {
        model.addAttribute("user", new UserDTO());
        return "register";
    }



    @PostMapping("/register")
    public String regPost(@ModelAttribute("user") UserDTO newUser, HttpServletRequest request) throws ServletException {
        if (newUser.getPassword() == null) {
            return "redirect:/register?error";
        }

        if (entryService.addUser(newUser)) {
            try {
                request.login(newUser.getName(), newUser.getPassword());
            } catch (ServletException e) {
                // Handle login exception
                return "redirect:/login?error";
            }
            return "redirect:/home";
        }

        return "redirect:/register?exists";
    }

    @GetMapping("/admin_panel")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String adminPanel() {
        return "admin_panel";
    }


    @GetMapping("/")
    public String index() {
        return "home";
    }


    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @PostMapping("/login")
    public String loginPost() {
        return "redirect:/home";
    }
}
