package edu.miu.cs.cs489.pizzadeliveryapp.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.List;

public record OrderRequest(
        Long orderNumber,
        @NotNull(message = "orderDate shouldn't be null")
        LocalDateTime orderDate,
        @NotBlank(message = "status shouldn't be blank")
        String status,
        @NotNull(message = "totalPrice shouldn't be null")
        Double totalPrice,
        @NotNull(message = "orderLine shouldn't be null")
        List<OrderLineRequest> orderLines
) {
}
