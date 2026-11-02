package com.codecourse.store.controllers;

import com.codecourse.store.dtos.CheckoutRequest;
import com.codecourse.store.dtos.CheckoutResponse;
import com.codecourse.store.dtos.ErrorDto;
import com.codecourse.store.exceptions.CartEmptyException;
import com.codecourse.store.exceptions.CartNotFoundException;
import com.codecourse.store.services.CheckoutService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/checkout")
public class CheckoutController {

    private final CheckoutService checkoutService;

    @PostMapping
    public CheckoutResponse checkout(@Valid @RequestBody CheckoutRequest request) {
        return checkoutService.checkout(request);
    }

    @ExceptionHandler({CartEmptyException.class, CartNotFoundException.class})
    public ResponseEntity<ErrorDto> handleExceptions(Exception ex) {
        return ResponseEntity.badRequest().body(new ErrorDto(ex.getMessage()));
     }
}
