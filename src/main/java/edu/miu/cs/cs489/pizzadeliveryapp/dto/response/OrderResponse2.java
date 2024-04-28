package edu.miu.cs.cs489.pizzadeliveryapp.dto.response;

import java.time.LocalDateTime;
import java.util.List;

public record OrderResponse2(
         Long orderNumber,
         LocalDateTime orderDate,
         String status,
         Double totalPrice,
         List<OrderLineResponse> orderLines
) {
}
