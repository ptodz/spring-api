package com.codecourse.store.services;

import com.codecourse.store.dtos.OrderDto;
import com.codecourse.store.mappers.OrderMapper;
import com.codecourse.store.repositories.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class OrderService {

    private final AuthService authService;
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;

    public List<OrderDto> getAllOrders() {
        var user = authService.getCurrentUser();
        var orders = orderRepository.findAllByCustomer(user);
        return orders.stream().map(orderMapper::toDto).toList();
    }
}
