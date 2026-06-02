package com.codewithmosh.store.dtos;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UpdateCardItemRequest {
    @NotNull(message = "Quantity must be provided.")
    @Min(1)
    @Max(100)
    private Integer quantity;
}
