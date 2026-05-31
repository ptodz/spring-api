package com.codewithmosh.store.dtos;

import lombok.*;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class ProductDto {
    Long  id;
    String name;
    String description;
    BigDecimal price;
    Byte categoryId;
}
