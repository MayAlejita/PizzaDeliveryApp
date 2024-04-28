package edu.miu.cs.cs489.pizzadeliveryapp.dto.response;

import java.time.LocalDateTime;

public record OrderLineResponse2(
         Long orderLineId,
         Integer quantity,
         Double price,
         LocalDateTime deliveryDate
) {
}
