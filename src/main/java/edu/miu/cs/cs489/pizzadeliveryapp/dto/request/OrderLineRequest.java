package edu.miu.cs.cs489.pizzadeliveryapp.dto.request;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record OrderLineRequest(
        Long orderLineId,
        @NotNull(message = "quantity shouldn't be null")
        Integer quantity,
        @NotNull(message = "price shouldn't be null")
        Double price,
        @NotNull(message = "deliveryDate shouldn't be null")
        LocalDateTime deliveryDate,
        @NotNull(message = "pizza shouldn't be null")
        PizzaRequest2 pizza
) {
}
