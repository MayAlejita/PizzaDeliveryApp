package edu.miu.cs.cs489.pizzadeliveryapp.dto.response;

import java.time.LocalDateTime;

public record OrderResponse(
         Long orderNumber,
         LocalDateTime orderDate,
         String status,
         Double totalPrice
) {
}
