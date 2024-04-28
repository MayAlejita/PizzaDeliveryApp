package edu.miu.cs.cs489.pizzadeliveryapp.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record PizzaRequest2(
        @NotNull(message = "pizzaId shouldn't be null")
        Integer pizzaId,
        @NotBlank(message = "name shouldn't be blank")
        String name,
        @NotBlank(message = "type shouldn't be blank")
        String type,
        @NotBlank(message = "size shouldn't be blank")
        String size,
        @NotNull(message = "price shouldn't be null")
        Double price,
        String additionalDetails
) {
}
