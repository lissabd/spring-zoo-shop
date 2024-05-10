package org.example.zooshop.controllers;

import org.example.zooshop.entity.CartItem;

import org.example.zooshop.entity.User;
import org.example.zooshop.service.CartService;
import org.example.zooshop.service.ProductService;
import org.example.zooshop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/cart")
public class CartController {

    private final CartService cartService;
    private final ProductService productService;
    private final UserService userService;

    @Autowired
    public CartController(CartService cartService, ProductService productService, UserService userService) {
        this.cartService = cartService;
        this.productService = productService;
        this.userService = userService;
    }
    @GetMapping
    @PreAuthorize("isAuthenticated()")
    public String viewCart(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        User user = userService.getUserByName(currentPrincipalName);

        List<CartItem> cartItems = cartService.getCartItemsForUser(user);
        model.addAttribute("cartItems", cartItems);
        return "cart";
    }

    @PostMapping("/add")
    @PreAuthorize("isAuthenticated()")
    public String addToCart(@RequestParam("productId") Long productId,
                            @RequestParam("quantity") int quantity,
                            RedirectAttributes redirectAttributes) {
        // Получение текущего аутентифицированного пользователя
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
            // Пользователь не аутентифицирован, перенаправляем его на страницу входа
            return "redirect:/login";
        }
        String currentPrincipalName = authentication.getName();
        User user = userService.getUserByName(currentPrincipalName);
        cartService.addToCart(user, productId, quantity);
        redirectAttributes.addFlashAttribute("message", "Product added to cart successfully");
        return "redirect:/products";
    }


    @PostMapping("/remove")
    public String removeFromCart(@AuthenticationPrincipal User user,
                                 @RequestParam Long cartItemId) {
        cartService.removeFromCart(user, cartItemId);
        return "redirect:/cart";
    }

    @PostMapping("/update")
    public String updateCartItemQuantity(@AuthenticationPrincipal User user,
                                         @RequestParam Long cartItemId,
                                         @RequestParam int quantity) {
        cartService.updateCartItemQuantity(user, cartItemId, quantity);
        return "redirect:/cart";
    }

}
