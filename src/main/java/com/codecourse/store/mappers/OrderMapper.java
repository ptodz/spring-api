package com.codecourse.store.mappers;

import com.codecourse.store.dtos.OrderDto;
import com.codecourse.store.entities.Order;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface OrderMapper {
    OrderDto toDto(Order order);
}
