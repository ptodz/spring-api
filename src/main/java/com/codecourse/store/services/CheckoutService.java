package com.codecourse.store.services;

import com.codecourse.store.dtos.CheckoutRequest;
import com.codecourse.store.dtos.CheckoutResponse;
import com.codecourse.store.entities.Order;
import com.codecourse.store.exceptions.CartEmptyException;
import com.codecourse.store.exceptions.CartNotFoundException;
import com.codecourse.store.repositories.CartRepository;
import com.codecourse.store.repositories.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CheckoutService {

    private final OrderRepository orderRepository;
    private final CartRepository cartRepository;
    private final CartService cartService;
    private final AuthService authService;

    public CheckoutResponse checkout(CheckoutRequest checkoutRequest) {
        var cart = cartRepository.getCartWithItems(checkoutRequest.getCartId()).orElse(null);
        if (cart == null) {
            throw new CartNotFoundException();
        }

        if (cart.isEmpty()) {
            throw new CartEmptyException();
        }

        var order = Order.fromCart(cart, authService.getCurrentUser());
        orderRepository.save(order);
        cartService.clearCart(cart.getId());
        return new CheckoutResponse(order.getId());
    }
}
