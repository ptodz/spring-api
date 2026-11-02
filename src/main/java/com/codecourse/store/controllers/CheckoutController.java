package com.codecourse.store.controllers;

import com.codecourse.store.dtos.CheckoutRequest;
import com.codecourse.store.dtos.ErrorDto;
import com.codecourse.store.repositories.CartRepository;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/checkout")
public class CheckoutController {

    private final CartRepository cartRepository;

    @PostMapping
    public ResponseEntity<?> checkout(@Valid @RequestBody CheckoutRequest request) {
        var cart = cartRepository.getCartWithItems(request.getCartId()).orElse(null);
        if (cart == null) {
            return ResponseEntity.badRequest().body(
                    new ErrorDto("Cart not found.")
            );
        }

        if (cart.getItems().isEmpty()) {
            return ResponseEntity.badRequest().body(
                    new ErrorDto("cart is empty")
            );
        }
        return null;
    }
}
