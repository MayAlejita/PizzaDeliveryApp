package edu.miu.cs.cs489.pizzadeliveryapp.dto.response;

import java.time.LocalDateTime;

public record OrderLineResponse(
         Long orderLineId,
         Integer quantity,
         Double price,
         LocalDateTime deliveryDate,
         PizzaResponse pizza
) {
}
